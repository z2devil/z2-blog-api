package com.z2devil.blog_api.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z2devil.blog_api.api.entity.Article;
import com.z2devil.blog_api.api.entity.Resource;
import com.z2devil.blog_api.api.entity.User;
import com.z2devil.blog_api.api.entity.bo.AddArticleBO;
import com.z2devil.blog_api.api.entity.bo.ModArticleBO;
import com.z2devil.blog_api.api.entity.bo.PageBO;
import com.z2devil.blog_api.api.entity.vo.ArticleDetailVO;
import com.z2devil.blog_api.api.entity.vo.ArticleListVO;
import com.z2devil.blog_api.api.mapper.ArticleMapper;
import com.z2devil.blog_api.api.mapper.ResourceMapper;
import com.z2devil.blog_api.api.mapper.TagMapper;
import com.z2devil.blog_api.api.service.IArticleService;
import com.z2devil.blog_api.api.service.IArticleTagService;
import com.z2devil.blog_api.api.service.IResourceService;
import com.z2devil.blog_api.api.service.IUserService;
import com.z2devil.blog_api.api.service.mapStruct.AddArticleConverter;
import com.z2devil.blog_api.api.service.mapStruct.ModArticleConverter;
import com.z2devil.blog_api.exception.AccessException;
import com.z2devil.blog_api.exception.BaseException;
import com.z2devil.blog_api.utils.SignUserIdUtils;
import com.z2devil.blog_api.utils.enums.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>
 * 专栏文章表 服务实现类
 * </p>
 *
 * @author z2devil
 * @since 2021-05-19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    private final ArticleMapper articleMapper;

    private final TagMapper tagMapper;

    private final IArticleTagService articleTagService;

    private final ResourceMapper resourceMapper;

    private final IResourceService resourceService;

    private final IUserService userService;

    private final AddArticleConverter addArticleConverter;

    private final ModArticleConverter modArticleConverter;

    @Override
    public IPage<ArticleListVO> getArticles(String keyword, Integer[] tags, PageBO pageBO) {
        IPage<ArticleListVO> result = articleMapper.getArticles(keyword, tags, pageBO.buildPage());
        return result;
    }

    @Override
    public ArticleListVO getArticle(Integer id) {
        ArticleListVO result = articleMapper.getArticle(id);
        return result;
    }

    @Override
    public ArticleDetailVO getArticleDetail(Integer id) {
        ArticleDetailVO articleDetail = articleMapper.getArticleDetail(id);
        System.out.println(articleDetail);
        return articleDetail;
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public void addArticle(AddArticleBO addArticleBO) {
        Article article = addArticleConverter.toEntity(addArticleBO);
        article.setUId(SignUserIdUtils.get());
        articleMapper.insert(article);
        List<Integer> tags = addArticleBO.getTags();
        for (Integer tag : tags) {
            if (!ObjectUtils.isEmpty(tagMapper.selectById(tag))) {
                tagMapper.insertArticleTag(article.getId(), tag);
            }
        }
        Integer[] resources = addArticleBO.getResources();
        if (resources != null) {
            for (Integer resource : resources) {
                resourceMapper.insert(new Resource(2, article.getId(), resource));
            }
        }
    }

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public void modArticle(ModArticleBO modArticleBO) {
        Article article = modArticleConverter.toEntity(modArticleBO);
        // 删除原有tags
        articleTagService.remove(modArticleBO.getId());
        // 添加新tags
        List<Integer> tags = modArticleBO.getTags();
        for (Integer tag : tags) {
            if (!ObjectUtils.isEmpty(tagMapper.selectById(tag))) {
                tagMapper.insertArticleTag(article.getId(), tag);
            }
        }
        // 删除原有files
        resourceService.remove(2, modArticleBO.getId());
        // 添加新files
        Integer[] resources = modArticleBO.getResources();
        if (resources != null) {
            for (Integer resource : resources) {
                resourceMapper.insert(new Resource(2, article.getId(), resource));
            }
        }
        articleMapper.updateById(article);
    }

    @Override
    public void removeArticle(Integer id) {
        Article article = articleMapper.selectById(id);
        User signedUser = userService.getById(SignUserIdUtils.get());
        if (!ObjectUtils.isEmpty(article)) {
            if (signedUser.getLv() < AccessLevel.MASTER.getLv() && !signedUser.getId().equals(article.getUId())) {
                throw new AccessException("权限异常");
            }
            removeById(id);
        } else {
            throw new BaseException("删除失败");
        }
    }

    @SneakyThrows
    @Override
    public void exportArticle(Integer id, HttpServletResponse response) {
        ArticleDetailVO result = articleMapper.getArticleDetail(id);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        //设置响应的内容类型
        response.setHeader("Content-Type", "application/octet-stream");
        //设置文件的名称和格式
        response.addHeader("Content-Disposition", "attachment;filename="
                // 设置名称格式，没有这个中文名称无法显示
                + URLEncoder.encode(result.getTitle(), "utf-8")
                + ".md");
        BufferedOutputStream buff = null;
        ServletOutputStream outStr = null;
        try {
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
            buff.write(result.getContent().getBytes(StandardCharsets.UTF_8));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            log.error("导出文件文件出错: " + e);
        } finally {
            try {
                if (buff != null) {
                    buff.close();
                }
                if (outStr != null) {
                    outStr.close();
                }
            } catch (Exception e) {
                log.error("关闭流对象出错: " + e);
            }
        }
    }
}
