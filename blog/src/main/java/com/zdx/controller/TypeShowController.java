package com.zdx.controller;

import com.github.pagehelper.PageInfo;
import com.zdx.domain.Blog;
import com.zdx.domain.Tag;
import com.zdx.domain.Type;
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
 * @date 2020/5/24 10:26
 */
@Controller
public class TypeShowController {

    @Autowired
    private ITypeService typeService;

    @Autowired
    private IBlogService blogService;

    @RequestMapping("/types/{id}/click")
    public String types(@PathVariable Integer id,
                       @RequestParam(defaultValue = "1")int page,
                       @RequestParam(defaultValue = "6") int size,
                       Model model) {
        List<Type> types = typeService.findAllType_Blog();
        if (id == -1) {
            id = types.get(0).getId();
        }

        List<Blog> blog = blogService.findAllBlog_Type(page,size,id);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blog);

        model.addAttribute("page", pageInfo);
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //model.addAttribute("blogsize", blogService.findPublishedBlog());
        model.addAttribute("types", types);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
