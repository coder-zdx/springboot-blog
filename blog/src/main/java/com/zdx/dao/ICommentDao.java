package com.zdx.dao;

import com.zdx.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/25 11:57
 */
@Repository
public interface ICommentDao {
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
     * 查询所有父级评论
     * @return
     * @param ParentId
     */
    List<Comment> findByParentIdNull(Integer ParentId, Integer blogId);

    /**
     * 查询一级回复
     * @return
     */
    List<Comment> findByParentIdNotNull(Integer id, Integer blogId);

    /**
     * 查询二级和所有子集回复
     * @return
     */
    List<Comment> findByReplayId(Integer ChildId, Integer blogId);



}
