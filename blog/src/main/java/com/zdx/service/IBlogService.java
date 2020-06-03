package com.zdx.service;

import com.zdx.domain.Blog;
import com.zdx.domain.Comment;
import com.zdx.domain.Tag;
import com.zdx.vo.BlogQuery;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Map;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/20 16:23
 */
public interface IBlogService {
    /**
     * 添加博客
     * @param blog
     */
    void saveBlog(Blog blog);

    /**
     * 删除博客
     * @param id
     */
    void deleteBlog(Integer id);

    /**
     * 修改博客
     * @param id
     * @param blog
     */
    void updateBlog(Integer id, Blog blog);

    /**
     * 分页查询博客
     * @param pageNum 当前页码
     * @param pageSize 每页查询条数
     * @return
     */
    List<Blog> pageBlog(int pageNum, int pageSize);

    /**
     * 通过标题模糊查询分页博客
     * @param titleName
     * @return
     */
    List<Blog> searchPageBlog(String titleName, Integer typeId, Boolean recommend);

    /**
     * 通过id获取Blog，其他不获取
     * @param id
     * @return
     */
    Blog getBlogById(Integer id);

    /**
     * 通过id查询Blog
     * @return
     */
    Blog getBlog_TypeById(Integer id);

    /**
     * 添加blog与tag中间表
     * @return
     */
    void saveBlog_Tag(Map<String,Object> map);

    /**
     * 删除中间表关系
     * @param blog_forid
     */
    void deleteBlog_Tag(Integer blog_forid);


    /**
     * 分页查询博客，要求是已发布状态
     * @return
     */
    List<Blog> pageBlogOutPublished(int pageNum, int pageSize);

    /**
     * 查询发布状态博客总数
     * @return
     */
    Integer findPublishedBlog();

    /**
     * 查询所有推荐博客
     * @return
     */
    List<Blog> findRecommendBlog();

    /**
     * 查询搜索后的博客结果
     * @return
     */
    List<Blog> findSearchResult(int pageNum, int pageSize,String searchtext);

    /**
     * 查询所有blog下的type，不分页
     * @return
     */
    public List<Blog> getAllBlog_Type();

    /**
     * 通过id查询blog并查询与之关联tags
     * @param id
     * @return
     */
    Blog getBlog_TagById(Integer id);

    /**
     * 查询所有博客并查询其tag
     * @return
     */
    List<Blog> findAllBlog_Tag(int pageNum, int pageSize, Integer id);

    /**
     * 查询所有博客并查询其tag
     * @return
     */
    List<Blog> findAllBlog_Type(int pageNum, int pageSize,Integer id);

    /**
     * 将博客，年限数与年限数对应博客封装到map集合
     * @return
     */
    Map<String, List<Blog>> BlogMapByYears();

    /**
     * 查询推荐并发布博客，最近更新的三个
     * @return
     */
    List<Blog> findRecommendBlogTopThree();

    /**
     * 将一个博客评论封装到blog中
     * @param id
     * @return
     */
    List<Comment> findBlog_CommentById(Integer id);

}
