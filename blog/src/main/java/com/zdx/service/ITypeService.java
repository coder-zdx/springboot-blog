package com.zdx.service;

import com.zdx.domain.Type;
import java.util.List;


/**
 * @author zdx
 * @function Type分类service层接口
 * @date 2020/5/18 21:23
 */
public interface ITypeService {

    /**
     * 保存标签
     * @return
     */
    void saveType(Type type);

    /**
     * 根据id查询单个type
     * @param id
     * @return
     */
    Type getType(Integer id);

    /**
     * 分页查询
     * @param pageNum  当前页码
     * @param pageSize 每页显示条数
     * @return
     */
    List<Type> pageType(int pageNum, int pageSize);

    /**
     * 更改type
     * @param id
     * @param type
     * @return
     */
    void updateType(Integer id, Type type);

    /**
     * 删除type
     * @param id
     */
    void deleteType(Integer id);

    /**
     * 根据name查询是否存在该数据，或是存在多个数据，为了测试保存是否成功
     * @param name
     * @return
     */
    Type getTypeByName(String name);

    /**
     * 查询所有Type
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
