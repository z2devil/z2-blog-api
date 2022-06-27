package com.z2devil.blog_api.api.entity.bo;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z2devil.blog_api.exception.BaseException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: blog_api
 * @description: 分页页面数据
 * @author: z2devil
 * @create: 2021-05-21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "分页BO")
public class PageBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页")
    @Min(value = 1, message = "页码不能小于1")
    private Integer current;

    @ApiModelProperty(value = "当前页显示数据的条数")
    @Max(value = 100, message = "单页数据超出最大数量")
    @Min(value = 1, message = "单页数据不能小于1")
    private Integer size;

    /**
     * 获取排序信息，排序的字段和正反序
     */
    @ApiModelProperty(value = "排序方式。(默认【创建时间倒序】:[{'column': 'create_time','asc': false}])。",
            notes = "例子：[{'column': 'create_time','asc': false},{'column':'name','asc': true}]"
    )
    private String orders;

    public PageBO(Integer current, Integer size) {
        this.current = current;
        this.size = size;
    }

    /**
     * 当前页默认值为1
     */
    public Integer getCurrent() {
        return current = (current == null || current <= 0) ? 1 : current;
    }

    /**
     * 每页大小默认为10
     */
    public Integer getSize() {
        return size = (size == null || size == 0) ? 10 : size;
    }

    /**
     * description:将orders（json数组字符串）转为List
     *
     * @return /
     * @author RenShiWei、z2devil
     * Date: 2020/11/22 16:43
     */
    public List<OrderItem> generateOrderList() {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(OrderItem.desc("post_date"));
        if (getOrders() != null) {
            try {
                orderItemList = JSONArray.parseArray(orders, OrderItem.class);
            } catch (Exception e) {
                throw new BaseException("分页排序参数orders不合法，请传正确的参数格式——['column':'','asc':'true/false']");
            }
        }
        return orderItemList;
    }

    /**
     * description:根据pageVO构建分页查询IPage
     *
     * @return IPage查询条件
     * @author RenShiWei
     * Date: 2020/11/22 17:19
     */
    public <K> Page<K> buildPage() {
        Page<K> page = new Page<>(getCurrent(), getSize());
        page.setOrders(generateOrderList());
        return page;
    }

}
