package com.zdx.service.impl;

import com.github.pagehelper.PageHelper;
import com.zdx.dao.ITypeDao;
import com.zdx.domain.Type;
import com.zdx.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/18 21:51
 */
@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private ITypeDao typeDao;

    @Transactional()
    @Override
    public void saveType(Type type) {

        typeDao.saveType(type);
    }

    @Override
    public Type getType(Integer id) {

        return typeDao.getType(id);
    }

    @Override
    public List<Type> pageType(int pageNum, int pageSize) {
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Type> types = typeDao.pageType();
        return types;
    }

    @Override
    @Transactional
    public void updateType(Integer id, Type type) {
        typeDao.updateType(id, type);
    }

    @Override
    @Transactional
    public void deleteType(Integer id) {
        typeDao.deleteType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public List<Type> listAllType() {
        return typeDao.listAllType();
    }

    @Override
    public List<Type> findAllType_Blog() {
        return typeDao.findAllType_Blog();
    }

    @Override
    public Type findType_BlogById(Integer id) {
        return typeDao.findType_BlogById(id);
    }

    public void typeIndexpage(){
        String orderBy = "id desc";
        PageHelper.startPage(1, 10, orderBy);

    }
}
