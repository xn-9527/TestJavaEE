package cn.chay.service;

import cn.chay.mapper.ArticleMapper;
import cn.chay.entity.Article;
import cn.chay.entity.Category;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sang on 17-3-10.
 */
@Service
public class ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public Article getArticleById(Long id) {
        Article article = articleMapper.getArticleById(id);
        article.setCategory(articleMapper.getCategoryById(article.getCategoryId()).getDisplayName());
        return article;
    }

    public List<Article> getFirst10Article() {
        return articleMapper.getFirst10Article();
    }

    public List<Category> getCategories() {
        return articleMapper.getCategories();
    }

    public void writeBlog(Article article) {
        Long categoryId = articleMapper.getCategoryIdByName(article.getCategory());
        article.setCategoryId(categoryId);
        article.setDate(sdf.format(new Date()));
        if (article.getSummary() == null || "".equals(article.getSummary())) {
            if (article.getContent().length() > 20) {
                article.setSummary(article.getContent().substring(0, 20));
            } else {
                article.setSummary(article.getContent().substring(0, article.getContent().length()));
            }
        }
        articleMapper.writeBlog(article);
    }

    public void deleteArticleById(Long id) {
        articleMapper.deleteArticleById(id);
    }

    public void updateBlog(Article article) {
        article.setDate(sdf.format(new Date()));
        if (article.getSummary() == null || "".equals(article.getSummary())) {
            if (article.getContent().length() > 20) {
                article.setSummary(article.getContent().substring(0, 20));
            } else {
                article.setSummary(article.getContent().substring(0, article.getContent().length()));
            }
        }
        articleMapper.updateArticleById(article);
    }

    public List<Article> getArticlesByCategoryName(String name) {
        Long categoryId = articleMapper.getCategoryIdByName(name);
        List<Article> articles = articleMapper.getArticlesByCategoryName(categoryId);
        return articles;
    }
}
