package org.sang.dao;


import org.apache.ibatis.annotations.Param;
import org.sang.entity.Article;
import org.sang.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sang on 17-3-10.
 */
@Repository
public interface ArticleDao {
    public Article getArticleById(@Param("id") Long id);

    public List<Article> getFirst10Article();

    public List<Article> getArticlesByCategoryName(@Param("categoryId")Long categoryId);

    public List<Category> getCategories();

    public void writeBlog(Article article);

    public Long getCategoryIdByName(@Param("name")String name);

    public void deleteArticleById(@Param("id")Long id);

    public void updateArticleById(Article article);

    public Category getCategoryById(@Param("id")Long id);
}
