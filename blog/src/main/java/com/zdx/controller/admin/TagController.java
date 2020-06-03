package com.zdx.controller.admin;

import com.github.pagehelper.PageInfo;
import com.zdx.domain.Tag;
import com.zdx.domain.Type;
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

import java.util.List;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/19 15:12
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private ITagService tagService;

    //分页
    @RequestMapping(value = "/tags")
    public String tags(@RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "6") int size, Model model){
        List<Tag> tags = tagService.pageTag(page, size);
        //PageInfo对象获取分页信息
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(tags);
        //当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //pageInfo对象，也就是一个分页Bean，使用其内部list集合即可访问内容
        model.addAttribute("pageInfo", pageInfo);

        return "admin/tags";
    }

    //非修改添加
    @RequestMapping("/tags/input")
    public String tagInput(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    //修改添加
    @RequestMapping("/tags/{id}/input")
    public String tagInput(@PathVariable int id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    //提交
    @RequestMapping(value = "/tags/submit", method = RequestMethod.POST)
    public String tagSubmit(Tag tag, RedirectAttributes redirectAttributes){
        if(tag.getName() == null || tag.getName() == ""){
            redirectAttributes.addFlashAttribute("empty_message", "请输入分类名");
            return "admin/tags-input";
        }else {
            Tag t = tagService.getTagByName(tag.getName());
            if(t == null){
                tagService.saveTag(tag);
                redirectAttributes.addFlashAttribute("message", "操作成功");
                return "redirect:/admin/tags";
            }else {
                redirectAttributes.addFlashAttribute("double_message", "分类名重复,请输入其他分类名");
                return "admin/tags-input";
            }
        }
    }

    //删除
    @RequestMapping("/tags/{id}/delete")
    public String tagDelete(@PathVariable int id, RedirectAttributes redirectAttributes){
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

    //修改
    @RequestMapping("/tags/{id}/update")
    public String tagUpdate(@PathVariable int id, RedirectAttributes redirectAttributes, Tag tag){
        if(tag.getName() == null || tag.getName() == ""){
            redirectAttributes.addFlashAttribute("empty_message", "请输入分类名");
            return "admin/tags-input";
        }else {
            Tag t = tagService.getTagByName(tag.getName());
            if(t == null){
                tagService.updateTag(id,tag);
                redirectAttributes.addFlashAttribute("message", "修改成功");
                return "redirect:/admin/tags";
            }else {
                redirectAttributes.addFlashAttribute("double_message", "分类名重复,请输入其他分类名");
                return "admin/tags-input";
            }
        }
    }


}
