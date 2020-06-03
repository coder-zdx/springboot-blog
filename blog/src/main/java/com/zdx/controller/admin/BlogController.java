package com.zdx.controller.admin;

import com.github.pagehelper.PageInfo;
import com.zdx.domain.Blog;
import com.zdx.domain.Tag;
import com.zdx.domain.Type;
import com.zdx.domain.User;
import com.zdx.service.IBlogService;
import com.zdx.service.ITagService;
import com.zdx.service.ITypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zdx
 * @function 博客控制器
 * @date 2020/5/18 17:18
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private IBlogService blogService;
    @Autowired
    private ITypeService typeService;
    @Autowired
    private ITagService tagService;

    //非查询分页
    @RequestMapping(value = "/blogs")
    public String blogs(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "6") int size, Model model){
        model.addAttribute("types", typeService.listAllType());
        List<Blog> blog = blogService.pageBlog(page, size);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blog);

        model.addAttribute("page", pageInfo);
        model.addAttribute("pageNum", pageInfo.getPageNum());

        return "admin/blogs";
    }

    //查询分页
    @RequestMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "6") int size,Blog blog, Integer typeId,Boolean recommend, Model model) {

        String titleName = blog.getTitle();
        List<Blog> titleBlog = blogService.searchPageBlog(titleName, typeId, recommend);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(titleBlog);

        model.addAttribute("page", pageInfo);
        model.addAttribute("pageNum", pageInfo.getPageNum());
        return "admin/blogs :: blogList";
    }


    //添加
    @RequestMapping("/blogs/input")
    public String tagInput(Model model){
        model.addAttribute("blog", new Blog());
        model.addAttribute("types", typeService.listAllType());
        model.addAttribute("tags", tagService.listAllTag());

        return "admin/blogs-input";
    }

    //修改添加
    @RequestMapping("/blogs/{id}/input")
    public String tagInput(@PathVariable int id, Model model){
        //model.addAttribute("blog", new Blog());
        model.addAttribute("types", typeService.listAllType());
        model.addAttribute("tags", tagService.listAllTag());
        Blog blog = blogService.getBlog_TypeById(id);
        //blog.setBlogs_tags(tagService.findBlog_TagAll(id));
//        if(blog.getBlogs_type() != null){
//            blog.init();
//        }
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }

    //删除
    @RequestMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

    //新增提交
    @RequestMapping(value = "/blogs/submit" ,method = RequestMethod.POST)
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setBlogs_user((User) session.getAttribute("user"));
        blog.setBlogs_type(typeService.getType(blog.getBlogs_type().getId()));

        blogService.saveBlog(blog);

        Map<String,Object> map = new HashMap<>();
        map.put("blogId",blog.getId());
        map.put("tagIds",tagService.listTag(blog.getTagIds()));

        blogService.saveBlog_Tag(map);
        blog.setBlogs_tags(tagService.findBlog_TagAll(blog.getId()));

        attributes.addFlashAttribute("message", "操作成功");

        return "redirect:/admin/blogs";
    }

    //修改提交
    @RequestMapping(value = "/blogs/{id}/update", method = RequestMethod.POST)
    public String tagUpdate(@PathVariable int id, RedirectAttributes redirectAttributes,Blog blog){

        blog.setBlogs_type(typeService.getType(blog.getBlogs_type().getId()));
        //blog.setBlogs_tags(tagService.findBlog_TagAll(id));

        blogService.deleteBlog_Tag(blog.getId());

        blogService.updateBlog(id,blog);

        Map<String,Object> map = new HashMap<>();
        map.put("blogId",blog.getId());
        map.put("tagIds",tagService.listTag(blog.getTagIds()));
        blogService.saveBlog_Tag(map);

        blog.setBlogs_tags(tagService.findBlog_TagAll(blog.getId()));
        redirectAttributes.addFlashAttribute("message", "操作成功");

        return "redirect:/admin/blogs";
    }

}
