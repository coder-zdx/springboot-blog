package com.zdx.service.impl;

import com.zdx.dao.ICommentDao;
import com.zdx.domain.Comment;
import com.zdx.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/25 11:58
 */
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentDao commentDao;

    @Override
    public List<Comment> listCommentByBlogId(Integer blogId) {
        return commentDao.listCommentByBlogId(blogId);
    }

    @Transactional
    @Override
    public void saveComment(Comment comment) {

//        Integer parentCommentId = comment.getParentComment().getId();
//        if (parentCommentId != -1) {
//            comment.setParentComment(commentDao.findByParentIdNullNoBlogId(parentCommentId));
//        } else {
//            comment.setParentComment(null);
//        }
        comment.setCreateTime(new Date());
        commentDao.saveComment(comment);
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * @Description: 查询评论
     * @Auther: ONESTAR
     * @Date: 17:26 2020/4/14
     * @Param:
     * @Return: 评论消息
     */
    @Override
    public List<Comment> listComment(Integer blogId) {
        //查询出父节点
        List<Comment> comments = commentDao.findByParentIdNull(Integer.parseInt("-1"),blogId);
        for(Comment comment : comments){
            Integer id = comment.getId();
            String parentNickname1 = comment.getNickname();
            List<Comment> childComments = commentDao.findByParentIdNotNull(id, blogId);
            //查询出子评论
            combineChildren(childComments, parentNickname1, blogId);
            comment.setReplyComments(tempReplys);

            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    /**
     * @Description: 查询出子评论
     * @Auther: ONESTAR
     * @Date: 17:31 2020/4/14
     * @Param: childComments：所有子评论
     * @Param: parentNickname1：父评论的姓名
     * @Return:
     */
    private void combineChildren(List<Comment> childComments, String parentNickname1, Integer blogId) {
        //判断是否有一级子回复
        if(childComments.size() > 0){
            //循环找出子评论的id
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Integer childId = childComment.getId();
                //查询二级以及所有子集回复
                recursively(childId, parentNickname, blogId);
            }
        }
    }

    /**
     * @Description: 循环迭代找出子集回复
     * @Auther: ONESTAR
     * @Date: 17:33 2020/4/14
     * @Param: childId：子评论的id
     * @Param: parentNickname1：子评论的姓名
     * @Return:
     */
    private void recursively(Integer childId, String parentNickname1, Integer blogId) {
        //根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentDao.findByReplayId(childId, blogId);

        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Integer replayId = replayComment.getId();
                tempReplys.add(replayComment);
                //循环迭代找出子集回复
                recursively(replayId,parentNickname, blogId);
            }
        }
    }

}
