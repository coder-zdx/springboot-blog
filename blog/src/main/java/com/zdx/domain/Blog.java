package com.zdx.domain;

import java.awt.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zdx
 * @function 博客实体类
 * @date 2020/5/18 10:16
 */
public class Blog implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    //是否开启赞赏、版权、评论、发布状态、推荐
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentable;
    private boolean published;
    private boolean recommend;
    private Date createTime;
    private Date updateTime;
    private String description;


    //Blog类与Type类是多对一
    private Type blogs_type;

    //Blog类与Tag类是多对多
    private List<Tag> blogs_tags;

    //Blog类与User类是多对一
    private User blogs_user;

    //Blog类与Comment类是一对多
    private List<Comment> blog_comments;

    //获取tag的id字符串
    private String tagIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentable() {
        return commentable;
    }

    public void setCommentable(boolean commentable) {
        this.commentable = commentable;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
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

    public Type getBlogs_type() {
        return blogs_type;
    }

    public void setBlogs_type(Type blogs_type) {
        this.blogs_type = blogs_type;
    }

    public List<Tag> getBlogs_tags() {
        return blogs_tags;
    }

    public void setBlogs_tags(List<Tag> blogs_tags) {
        this.blogs_tags = blogs_tags;
    }

    public User getBlogs_user() {
        return blogs_user;
    }

    public void setBlogs_user(User blogs_user) {
        this.blogs_user = blogs_user;
    }

    public List<Comment> getBlog_comments() {
        return blog_comments;
    }

    public void setBlog_comments(List<Comment> blog_comments) {
        this.blog_comments = blog_comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    //下面两个方法为了编辑博客时能获取到tag标签
    public void init() {
        this.tagIds = tagsToIds(this.getBlogs_tags());
    }

    //1,2,3
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentable=" + commentable +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
