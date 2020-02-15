package cn.chay.mapper;


import cn.chay.base.mapper.BaseMapper;
import cn.chay.entity.Article;
import cn.chay.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by sang on 17-3-10.
 */
public interface ArticleMapper extends BaseMapper<Article> {
    Article getArticleById(@Param("id") Long id);

    List<Article> getFirst10Article();

    List<Article> getArticlesByCategoryName(Long categoryId);

    List<Category> getCategories();

    void writeBlog(Article article);

    Long getCategoryIdByName(String name);

    void deleteArticleById(Long id);

    void updateArticleById(Article article);

    Category getCategoryById(Long id);
}
