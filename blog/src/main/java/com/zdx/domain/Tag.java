package com.zdx.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author zdx
 * @function 标签实体类
 * @date 2020/5/18 10:23
 */
public class Tag implements Serializable {
    private Integer id;
    private String name;

    //Tag类与Blog类是多对多
    private List<Blog> tags_blogs;

    private List<Integer> ids;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getTags_blogs() {
        return tags_blogs;
    }

    public void setTags_blogs(List<Blog> tags_blogs) {
        this.tags_blogs = tags_blogs;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
