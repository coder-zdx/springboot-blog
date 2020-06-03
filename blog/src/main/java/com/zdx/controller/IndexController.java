package com.zdx.controller;

import com.github.pagehelper.PageInfo;
import com.zdx.domain.Blog;
import com.zdx.domain.Tag;
import com.zdx.domain.Type;
import com.zdx.service.IBlogService;
import com.zdx.service.ITagService;
import com.zdx.service.ITypeService;
import com.zdx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/17 15:10
 */
@Controller
public class IndexController {

    @Autowired
    private IBlogService blogService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private ITypeService typeService;
    @Autowired
    private IUserService userService;

    @RequestMapping("/index")
    public String index(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "6") int size,
                        Model model){
        //blog
        List<Blog> blog = blogService.pageBlogOutPublished(page, size);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blog);

        List<Blog> recommendBlogs = blogService.findRecommendBlog();
        model.addAttribute("page", pageInfo);
        model.addAttribute("pageNum", pageInfo.getPageNum());
        model.addAttribute("blogsize", blogService.findPublishedBlog());
        model.addAttribute("recommendBlogs", recommendBlogs);

        //tag,type
        List<Type> types = typeService.findAllType_Blog();
        if(types.size() < 6){
            model.addAttribute("types", types);
        }else{
            types = types.subList(0,5);
            model.addAttribute("types", types);
        }

        List<Tag> tags = tagService.findAllTag_Blog();
        if(tags.size() < 10){
            model.addAttribute("tags", tags);
        }else{
            tags = tags.subList(0,9);
            model.addAttribute("tags", tags);
        }
        return "index";
    }


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam(defaultValue = "1")int page,
                         @RequestParam(defaultValue = "6") int size,
                         @RequestParam String searchtext,
                         Model model) {
        List<Blog> blog = blogService.findSearchResult(page, size,searchtext);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blog);
        model.addAttribute("page", pageInfo);
        model.addAttribute("pageNum", pageInfo.getPageNum());
        model.addAttribute("blogsize", blog.size());

        model.addAttribute("searchtext", searchtext);

        return "search";
    }

    @RequestMapping("/blog/{id}/detail")
    public String blog(@PathVariable int id,Model model){
        Blog blog = blogService.getBlog_TagById(id);
        blog.setBlogs_type(typeService.findType_BlogById(id));
        blog.setBlog_comments(blogService.findBlog_CommentById(id));
        blog.setBlogs_user(userService.findUserBlogByBlogId(id));
        model.addAttribute("blog", blog);
        return "blog";
    }

    @RequestMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.findRecommendBlogTopThree());
        return "_fragments :: newblogList";
    }
}
