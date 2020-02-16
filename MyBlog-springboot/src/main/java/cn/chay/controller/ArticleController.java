package cn.chay.controller;

import cn.chay.base.response.AjaxResult;
import cn.chay.entity.Article;
import cn.chay.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.tautua.markdownpapers.Markdown;
import org.tautua.markdownpapers.parser.ParseException;

import javax.annotation.Resource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by chay on 2020-2-16.
 */
@RestController
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
    @Resource
    private ArticleService articleService;

    /**
     * 获取首页10篇文章
     *
     * @return
     */
    @GetMapping
    public AjaxResult index() {
        List<Article> articles = articleService.getFirst10Article();
        return AjaxResult.success(articles);
    }

    /***
     * 获取某个分类下的文章
     *
     * @param category
     * @param displayName
     * @return
     */
    @GetMapping("/column/{displayName}/{category}")
    public AjaxResult column(@PathVariable("category") String category, @PathVariable("displayName") String displayName) {
        return AjaxResult.success(articleService.getArticlesByCategoryName(category));
    }

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}/{category}")
    public AjaxResult detail(@PathVariable("id") Long id) {
        Article article = articleService.getArticleById(id);
        Markdown markdown = new Markdown();
        try {
            StringWriter out = new StringWriter();
            markdown.transform(new StringReader(article.getContent()), out);
            out.flush();
            article.setContent(out.toString());
            log.info(article.getContent());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return AjaxResult.success(article);
    }

    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        articleService.deleteArticleById(id);
        return AjaxResult.success();
    }

    @PostMapping
    public AjaxResult createOrUpdate(@RequestBody Article bean) {
        if (bean.getId() == null) {
            articleService.writeBlog(bean);
        } else {

            articleService.updateBlog(bean);
        }
        return AjaxResult.success();
    }
}
