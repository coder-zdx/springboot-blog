package com.zdx.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/18 10:25
 */
public class Comment implements Serializable {
    private Integer id;
    private String nickname;
    private String email;
    private String avatar;
    private String content;
    private Date createTime;
    private Integer parentCommentId;
    private String ParentNickname;


    //Comment类与Blog类是多对一
    private Blog comments_blog;

    //Comment类自关联，是个难点
    private List<Comment> replyComments;

    private Comment parentComment;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Blog getComments_blog() {
        return comments_blog;
    }

    public void setComments_blog(Blog comments_blog) {
        this.comments_blog = comments_blog;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getParentNickname() {
        return ParentNickname;
    }

    public void setParentNickname(String parentNickname) {
        ParentNickname = parentNickname;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", content='" + content + '\'' +
                ", updateTime=" + createTime +
                '}';
    }
}
