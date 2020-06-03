package com.zdx.controller;

import com.zdx.domain.Blog;
import com.zdx.domain.Comment;
import com.zdx.domain.User;
import com.zdx.service.IBlogService;
import com.zdx.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/25 11:56
 */
@Controller
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IBlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @RequestMapping("/comments/{blogId}")
    public String comments(@PathVariable Integer blogId, Model model){
        model.addAttribute("comments",commentService.listComment(blogId));
        return "blog :: commentList";
    }

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    public String post(Comment comment, HttpSession session){
        comment.setAvatar(avatar);
        Integer blogId = comment.getComments_blog().getId();
        comment.setComments_blog(blogService.getBlogById(blogId));

        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
        } else {
            comment.setAvatar(avatar);
        }

        if (comment.getParentComment().getId() != null) {
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        commentService.saveComment(comment);

        return "redirect:/comments/"  + blogId;
    }

}
