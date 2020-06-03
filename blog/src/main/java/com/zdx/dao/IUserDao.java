package com.zdx.dao;

import com.zdx.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zdx
 * @function 用户登录dao接口
 * @date 2020/5/18 14:43
 */
@Repository
public interface IUserDao {
    User checkUser(String username, String password);

    /**
     * 通过blogid查询user
     * @param id
     * @return
     */
    User findUserBlogByBlogId(Integer id);
}
