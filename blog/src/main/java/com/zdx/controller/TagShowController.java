package com.zdx.controller;

import com.github.pagehelper.PageInfo;
import com.zdx.domain.Blog;
import com.zdx.domain.Tag;
import com.zdx.service.IBlogService;
import com.zdx.service.ITagService;
import com.zdx.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/23 21:44
 */
@Controller
public class TagShowController {

    @Autowired
    private ITagService tagService;

    @Autowired
    private IBlogService blogService;

    @RequestMapping("/tags/{id}/click")
    public String tags(@PathVariable int id,
                       @RequestParam(defaultValue = "1")int page,
                       @RequestParam(defaultValue = "6") int size,
                       Model model) {
        List<Tag> tags = tagService.findAllTag_Blog();
        if (id == -1) {
            id = tags.get(0).getId();
        }

        List<Blog> blog = blogService.findAllBlog_Tag(page,size,id);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blog);

        model.addAttribute("page", pageInfo);
        model.addAttribute("pageNum", pageInfo.getPageNum());
        model.addAttribute("blogsize", blogService.findPublishedBlog());
        model.addAttribute("tags", tags);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
