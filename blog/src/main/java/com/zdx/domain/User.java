package com.zdx.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zdx
 * @function 用户实体类
 * @date 2020/5/18 10:38
 */
public class User implements Serializable {
    private Integer id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    //用户类型，使用Integer类型，分管理员或是用户
    private Integer type;
    private String avatar;
    private Date createTime;
    private Date updateTime;

    //User类对于Blog类是一对多
    private List<Blog> user_blogs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Blog> getUser_blogs() {
        return user_blogs;
    }

    public void setUser_blogs(List<Blog> user_blogs) {
        this.user_blogs = user_blogs;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
