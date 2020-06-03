package com.zdx.dao;

import com.zdx.domain.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/18 21:55
 */
@Repository
public interface ITypeDao {

    /**
     * 添加分类
     * @param type
     */
    void saveType(Type type);

    /**
     * 通过id获取分类
     * @param id
     * @return
     */
    Type getType(Integer id);

    /**
     * 分页查询
     * @return
     */
    List<Type> pageType();

    /**
     * 修改分类
     * @param id
     * @param type
     */
    void updateType(Integer id, Type type);

    /**
     * 删除分类
     * @param id
     */
    void deleteType(Integer id);

    /**
     * 通过名字查询type
     * @param name
     * @return
     */
    Type getTypeByName(String name);

    /**
     * 查询所有Tag
     * @return
     */
    List<Type> listAllType();

    /**
     * 查询所有分类，分类中包含博客
     * @return
     */
    List<Type> findAllType_Blog();

    /**
     * 通过blogid查询所包含的type，并封装到type中
     * @return
     */
    Type findType_BlogById(Integer id);
}
