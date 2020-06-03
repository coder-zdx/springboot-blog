package com.zdx.service;

import com.zdx.domain.Blog;
import com.zdx.domain.Tag;
import com.zdx.domain.Type;

import java.util.List;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/20 15:19
 */
public interface ITagService {

    /**
     * 保存标签
     * @return
     */
    void saveTag(Tag tag);

    /**
     * 根据id查询单个type
     * @param id
     * @return
     */
    Tag getTag(Integer id);

    /**
     * 分页查询
     * @param pageNum  当前页码
     * @param pageSize 每页显示条数
     * @return
     */
    List<Tag> pageTag(int pageNum, int pageSize);

    /**
     * 更改type
     * @param id
     * @param tag
     * @return
     */
    void updateTag(Integer id, Tag tag);

    /**
     * 删除type
     * @param id
     */
    void deleteTag(Integer id);

    /**
     * 根据name查询是否存在该数据，或是存在多个数据，为了测试保存是否成功
     * @param name
     * @return
     */
    Tag getTagByName(String name);

    /**
     * 查询所有Tag
     * @return
     */
    List<Tag> listAllTag();

    /**
     * 通过获取ids字符串获取tag的list集合
     * @param ids
     * @return
     */
    public List<Integer> listTag(String ids);

    /**
     * 通过中间表查询一个Blog中所有Tag
     * @return
     */
    List<Tag> findBlog_TagAll(Integer id);

    /**
     * 查询一个标签所有博客
     * @return
     */
    List<Tag> findAllTag_Blog();
}
