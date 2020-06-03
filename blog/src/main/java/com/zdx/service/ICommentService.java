package com.zdx.service;

import com.zdx.domain.Comment;

import java.util.List;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/25 11:57
 */
public interface ICommentService {

    /**
     * 通过blog的id获取评论
     * @param blogId
     * @return
     */
    List<Comment> listCommentByBlogId(Integer blogId);

    /**
     * 保存评论
     * @param comment
     * @return
     */
    void saveComment(Comment comment);

    /**
     * 查询所有评论
     * @return
     */
    List<Comment> listComment(Integer blogId);
}
