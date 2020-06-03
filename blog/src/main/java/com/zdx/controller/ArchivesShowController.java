package com.zdx.controller;

import com.zdx.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/24 11:30
 */
@Controller
public class ArchivesShowController {

    @Autowired
    private IBlogService blogService;

    @RequestMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogService.BlogMapByYears());
        model.addAttribute("blogCount", blogService.findPublishedBlog());
        return "archives";
    }
}
