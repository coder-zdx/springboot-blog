package com.zdx.service;

import com.zdx.domain.User;

/**
 * @author zdx
 * @function 用户登录
 * @date 2020/5/18 14:36
 */
public interface IUserService {

    /**
     * 根据用户名和密码检查用户
     * @param username  用户名
     * @param password  密码
     * @return
     */
    User checkUser(String username, String password);

    /**
     * 通过blogid查询user
     * @param id
     * @return
     */
    User findUserBlogByBlogId(Integer id);
}
