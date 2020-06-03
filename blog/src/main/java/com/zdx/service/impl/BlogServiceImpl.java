package com.zdx.service.impl;

import com.github.pagehelper.PageHelper;
import com.zdx.NotFoundException;
import com.zdx.dao.IBlogDao;
import com.zdx.domain.Blog;
import com.zdx.domain.Comment;
import com.zdx.domain.Tag;
import com.zdx.service.IBlogService;
import com.zdx.util.MarkdownUtils;
import com.zdx.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/20 16:28
 */
@Service
public class BlogServiceImpl implements IBlogService {

    @Autowired
    private IBlogDao blogDao;

    //保存博客
    @Transactional
    @Override
    public void saveBlog(Blog blog) {

        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);

        blogDao.saveBlog(blog);
    }

    //删除博客
    @Override
    @Transactional
    public void deleteBlog(Integer id) {
        blogDao.deleteBlog(id);
    }

    //修改博客
    @Override
    @Transactional
    public void updateBlog(Integer id, Blog blog) {

        blog.setUpdateTime(new Date());
        blogDao.updateBlog(id, blog);
    }


    //无条件分页查询博客
    @Override
    public List<Blog> pageBlog(int pageNum, int pageSize) {
        String orderBy = "updateTime desc";

        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Blog> blog = blogDao.pageBlog();
        return blog;
    }

    //搜索分页查询博客
    @Override
    public List<Blog> searchPageBlog(String titleName, Integer typeId, Boolean recommend) {

        return blogDao.searchPageBlog(titleName, typeId, recommend);
    }

    /**
     * 通过id获取Blog，其他不获取
     * @param id
     * @return
     */
    @Override
    public Blog getBlogById(Integer id){
        return blogDao.getBlogById(id);
    }

    //通过id获取blog
    @Override
    public Blog getBlog_TypeById(Integer id) {
        return blogDao.getBlog_TypeById(id);
    }

    //保存blog与tag外键
    @Override
    @Transactional
    public void saveBlog_Tag(Map<String, Object> map) {
        blogDao.saveBlog_Tag(map);
    }

    //删除blog与tag外键
    @Override
    @Transactional
    public void deleteBlog_Tag(Integer blog_forid) {
        blogDao.deleteBlog_Tag(blog_forid);
    }

    @Override
    public List<Blog> pageBlogOutPublished(int pageNum, int pageSize) {
        String orderBy = "updateTime desc";

        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Blog> blog = blogDao.pageBlogOutPublished();
        return blog;
    }

    @Override
    public Integer findPublishedBlog() {
        return blogDao.findPublishedBlog();
    }

    @Override
    public List<Blog> findRecommendBlog() {
        return blogDao.findRecommendBlog();
    }

    @Override
    public List<Blog> findSearchResult(int pageNum, int pageSize,String searchtext) {
        String orderBy = "updateTime desc";

        PageHelper.startPage(pageNum, pageSize, orderBy);
        return blogDao.findSearchResult(searchtext);
    }

    @Override
    public List<Blog> getAllBlog_Type() {
        return blogDao.pageBlogOutPublished();
    }

    @Override
    public Blog getBlog_TagById(Integer id) {

        Blog blog =  blogDao.getBlog_TagById(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        blogDao.updateBlogViews(id);
        return b;
    }


    @Override
    public List<Blog> findAllBlog_Tag(int pageNum, int pageSize,Integer id) {
        String orderBy = "updateTime desc";

        PageHelper.startPage(pageNum, pageSize, orderBy);
        return blogDao.findAllBlog_Tag(id);
    }

    @Override
    public List<Blog> findAllBlog_Type(int pageNum, int pageSize, Integer id) {
        String orderBy = "updateTime desc";

        PageHelper.startPage(pageNum, pageSize, orderBy);
        return blogDao.findAllBlog_Type(id);
    }

    @Override
    public Map<String, List<Blog>> BlogMapByYears() {
        List<String> years = blogDao.findBlogYears();
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogDao.findBlogByYears(year));
        }
        return map;
    }

    @Override
    public List<Blog> findRecommendBlogTopThree() {
        return blogDao.findRecommendBlogTopThree();
    }

    @Override
    public List<Comment> findBlog_CommentById(Integer id) {
        return blogDao.findBlog_CommentById(id);
    }

}
