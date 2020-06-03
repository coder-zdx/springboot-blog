package com.zdx.controller.admin;

import com.github.pagehelper.PageInfo;
import com.zdx.domain.Type;
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
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @RequestMapping(value = "/types")
    public String types(@RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "10") int size, Model model){
        List<Type> types = typeService.pageType(page, size);
        //PageInfo对象获取分页信息
        PageInfo<Type> pageInfo = new PageInfo<Type>(types);
        //当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //pageInfo对象，也就是一个分页Bean，使用其内部list集合即可访问内容
        model.addAttribute("pageInfo", pageInfo);

        return "admin/types";
    }

    @RequestMapping("/types/input")
    public String typeInput(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @RequestMapping("/types/{id}/input")
    public String typeInput(@PathVariable int id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    @RequestMapping(value = "/types/submit", method = RequestMethod.POST)
    public String typeSubmit(Type type, RedirectAttributes redirectAttributes){
        if(type.getName() == null || type.getName() == ""){
            redirectAttributes.addFlashAttribute("empty_message", "请输入分类名");
            return "redirect:/admin/types/input";
        }else {
            Type t = typeService.getTypeByName(type.getName());
            if(t == null){
                typeService.saveType(type);
                redirectAttributes.addFlashAttribute("message", "操作成功");
                return "redirect:/admin/types";
            }else {
                redirectAttributes.addFlashAttribute("double_message", "分类名重复,请输入其他分类名");
                return "redirect:/admin/types/input";
            }
        }
    }

    @RequestMapping("/types/{id}/delete")
    public String typeDelete(@PathVariable int id, RedirectAttributes redirectAttributes){
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

    @RequestMapping("/types/{id}/update")
    public String typeUpdate(@PathVariable int id, RedirectAttributes redirectAttributes, Type type){
        if(type.getName() == null || type.getName() == ""){
            redirectAttributes.addFlashAttribute("empty_message", "请输入分类名");
            return "redirect:/admin/types/input";
        }else {
            Type t = typeService.getTypeByName(type.getName());
            if(t == null){
                typeService.updateType(id,type);
                redirectAttributes.addFlashAttribute("message", "修改成功");
                return "redirect:/admin/types";
            }else {
                redirectAttributes.addFlashAttribute("double_message", "分类名重复,请输入其他分类名");
                return "redirect:/admin/types/input";
            }
        }
    }


}
