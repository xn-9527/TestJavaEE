package cn.chay.controller;

import cn.chay.base.response.AjaxResult;
import cn.chay.entity.Article;
import cn.chay.entity.Category;
import cn.chay.entity.User;
import cn.chay.service.ArticleService;
import cn.chay.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tautua.markdownpapers.Markdown;
import org.tautua.markdownpapers.parser.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by chay on 2020-2-16.
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class LoginController {
    @Resource
    private UserService userService;
    /**
     * 登录接口
     *
     * @return
     */
    @PostMapping("/admin/login")
    public AjaxResult login(HttpServletRequest request, User user) {
        if (userService.login(user.getUsername(), user.getPassword())) {
            request.getSession().setAttribute("user", user);
            return AjaxResult.success("登录成功");
        } else {
            return AjaxResult.failed("用户名或密码错误");
        }
    }

}
