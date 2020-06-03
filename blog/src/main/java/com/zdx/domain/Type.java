package com.zdx.domain;

import com.sun.istack.internal.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * @author zdx
 * @function 分类实体类
 * @date 2020/5/18 10:24
 */
public class Type implements Serializable {
    private Integer id;

    private String name;

    //Type类对于Blog类是一对多
    private List<Blog> type_blogs;

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

    public List<Blog> getType_blogs() {
        return type_blogs;
    }

    public void setType_blogs(List<Blog> type_blogs) {
        this.type_blogs = type_blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
