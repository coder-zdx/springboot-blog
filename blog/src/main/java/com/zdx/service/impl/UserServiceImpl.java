package com.zdx.service.impl;

import com.zdx.dao.IUserDao;
import com.zdx.domain.User;
import com.zdx.service.IUserService;
import com.zdx.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author zdx
 * @function 功能
 * @date 2020/5/18 14:38
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.checkUser(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public User findUserBlogByBlogId(Integer id) {
        return userDao.findUserBlogByBlogId(id);
    }
}
