package com.z2devil.blog_api.aspect;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.z2devil.blog_api.annotation.Column;
import com.z2devil.blog_api.annotation.Queries;
import com.z2devil.blog_api.annotation.Query;
import com.z2devil.blog_api.utils.SpringContextHolder;
import com.z2devil.blog_api.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;


/**
 * @program: blog_api
 * @description: 关联查询的实现
 * @author: z2devil
 * @create: 2021-10-13
 **/
@Aspect
@Component
public class QueryAspect {

    /**
     * Query注解配置切面织入点
     */
    @Pointcut("@annotation(com.z2devil.blog_api.annotation.Query)")
    public void queryPointcut() {
    }

    /**
     * Queries注解配置切面织入点
     */
    @Pointcut("@annotation(com.z2devil.blog_api.annotation.Queries)")
    public void queriesPointcut() {
    }

    @AfterReturning(value = "queryPointcut()", returning = "result")
    public void handleQuery(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Query query = method.getAnnotation(Query.class);
        //如果目标方法返回类型为 Collection的实现类，如List,则通过迭代器遍历
        //如果目标方法返回类型为 IPage，则先获取getRecords()的List，再通过迭代器遍历
        if (result instanceof Collection || result instanceof IPage) {
            Iterator iter;
            if (result instanceof Collection) {
                iter = ((Collection) result).iterator();
            } else {
                iter = ((IPage) result).getRecords().iterator();
            }
            while (iter.hasNext()) {
                execute(query, iter.next());
            }
        } else { //如果目标方法返回类型即为实体类
            execute(query, result);
        }
    }

    @AfterReturning(value = "queriesPointcut()", returning = "result")
    public void handleQueries(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method signatureMethod = signature.getMethod();
        Queries queries = signatureMethod.getAnnotation(Queries.class);
        if (result instanceof Collection || result instanceof IPage) {
            Iterator iter;
            if (result instanceof Collection) {
                iter = ((Collection) result).iterator();
            } else {
                iter = ((IPage) result).getRecords().iterator();
            }
            while (iter.hasNext()) {
                Object o = iter.next();
                //针对每个实体处理多个@query
                for (Query query : queries.value()) {
                    execute(query, o);
                }
            }
        } else {
            for (Query query : queries.value()) {
                execute(query, result);
            }
        }
    }

    /**
     * @param query  注解query
     * @param result 目标方法返回值
     * @author Wangmingcan
     * Date : 2021-01-11 15:38
     */
    private void execute(Query query, Object result) {
        //获取需要被赋值的属性
        String property = query.property();
        //获取需要执行的方法
        String select = query.select();
        //获取关联的列，并转驼峰
        String column = query.column().contains("_") ?
                StringUtils.toCamelCase(query.column()) : query.column();
        Column[] columns = query.column().equals("") ?
                query.columns() : null;
        if (columns == null) {
            executeByParam(property, column, select, result);
        } else {
            executeByParams(property, columns, select, result);
        }
    }

    private void executeByParam(String property, String column, String select, Object result) {
        //关联属性的类型（也为select的参数类型）
        Class<?> columnType;
        //关联列的值
        Serializable columnValue;
        //select方法返回值类型
        Class<?> selectMethodReturnType;
        //select方法返回结果
        Object selectResult;
        try {
            //目标方法返回值类型class
            Class<?> resultClass = result.getClass();
            //通过反射获取get方法并调用来获取关联列的值
            try {
                Method getMethod = resultClass.getMethod("get" +
                        column.substring(0, 1).toUpperCase() + column.substring(1));
                columnValue = (Serializable) getMethod.invoke(result);
                columnType = getMethod.getReturnType();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("找不到get方法 : " + e.getMessage() +
                        "，请检查column参数" + column + "是否填写正确", e);
            }
            //通过反射获取该select方法所在的类
            int pointIndex = select.lastIndexOf(".");
            Class<?> selectMethodClass = Class.forName(select.substring(0, pointIndex));
            //通过反射获取需要调用的select方法
            try {
                Method selectMethod = selectMethodClass.getMethod(select.substring(pointIndex + 1), columnType);
                //调用并获取select方法的结果
                selectResult = selectMethod.invoke(SpringContextHolder.getBean(selectMethodClass),
                        columnValue);
                selectMethodReturnType = selectMethod.getReturnType();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("找不到select方法 : " + e.getMessage() +
                        "，请检查select方法的名称和参数类型", e);
            }
            //通过反射获取set方法并调用来完成赋值
            try {
                Method setMethod = resultClass.getMethod("set" +
                        property.substring(0, 1).toUpperCase() + property.substring(1), selectMethodReturnType);
                setMethod.invoke(result, selectResult);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("找不到set方法 : " + e.getMessage() +
                        "，请检查" + property + "的类型与方法" + select + "的返回值是否一致", e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 自定义多参数子查询
     * @params [property, columns, select, result]
     * @return void
     * @author z2devil
     * @date 2021/11/23
     */
    private void executeByParams(String property, Column[] columns, String select, Object result) {
        //关联列的值
        Serializable columnValue;
        //select方法返回值类型
        Class<?> selectMethodReturnType;
        //select方法返回结果
        Object selectResult;
        try {
            //目标方法返回值类型class
            Class<?> resultClass;
            List<Class<?>> parameterTypes = new ArrayList<>();
            List<Serializable> parameterValues = new ArrayList<>();
            List<Column> columnsList = Arrays.asList(columns);

            for (Column column : columnsList) {
                Method method = null;
                try {
                    if (column.customMethod()) {
                        resultClass = column.type().getClazz();
                        method = resultClass.getMethod(column.type().getMethodStr(), String.class);
                        columnValue = (Serializable) method.invoke(null, column.value());

//                        if (column.value().equals("")) {
//                            columnValue = (Serializable) method.invoke(null);
//                        }else {
//                            columnValue = (Serializable) method.invoke(null, column.value());
//                        }
                    }else {
                        if (column.value().equals("")) {
                            resultClass = result.getClass();
                            method = resultClass.getMethod("get" +
                                    column.name().substring(0, 1).toUpperCase() + column.name().substring(1));
                            columnValue = (Serializable) method.invoke(result);
                        } else {
                            resultClass = column.type().getClazz();
                            if (column.type().getMethodStr().equals("")) {
                                columnValue = column.value();
                            }else {
                                method = resultClass.getMethod(column.type().getMethodStr(), String.class);
                                columnValue = (Serializable) method.invoke(null, column.value());
                            }

                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage(), e);
                }

                parameterTypes.add((Class<?>) method.getAnnotatedReturnType().getType());
//                parameterTypes.add(column.type().getClazz());
                parameterValues.add(columnValue);
            }
            //通过反射获取该select方法所在的类
            int pointIndex = select.lastIndexOf(".");
            Class<?> selectMethodClass = Class.forName(select.substring(0, pointIndex));
            //通过反射获取需要调用的select方法
            try {
                Method selectMethod = selectMethodClass.getMethod(select.substring(pointIndex + 1),
                        parameterTypes.toArray(new Class<?>[]{}));
                //调用并获取select方法的结果
                selectResult = selectMethod.invoke(SpringContextHolder.getBean(selectMethodClass),
                        parameterValues.toArray(new Object[]{}));
                selectMethodReturnType = selectMethod.getReturnType();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("找不到select方法 : " + e.getMessage() +
                        "，请检查select方法的名称和参数类型", e);
            }
            //通过反射获取set方法并调用来完成赋值
            try {
                Method setMethod = result.getClass().getMethod("set" +
                        property.substring(0, 1).toUpperCase() + property.substring(1), selectMethodReturnType);
                setMethod.invoke(result, selectResult);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("找不到set方法 : " + e.getMessage() +
                        "，请检查" + property + "的类型与方法" + select + "的返回值是否一致", e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
