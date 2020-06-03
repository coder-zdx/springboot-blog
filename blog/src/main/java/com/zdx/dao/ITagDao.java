package com.zdx.dao;

import com.zdx.domain.Blog;
import com.zdx.domain.Tag;
import com.zdx.domain.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/18 21:55
 */
@Repository
public interface ITagDao {

    void saveTag(Tag tag);

    Tag getTag(Integer id);

    /**
     * 分页查询
     * @return
     */
    List<Tag> pageTag();

    void updateTag(Integer id, Tag tag);

    void deleteTag(Integer id);

    /**
     * 通过名字查询type
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
