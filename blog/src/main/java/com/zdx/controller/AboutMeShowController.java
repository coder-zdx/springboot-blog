package com.zdx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/24 21:49
 */
@Controller
public class AboutMeShowController {

    @RequestMapping("/about")
    public String about() {
        return "aboutme";
    }
}
