package com.zdx.service.impl;

import com.github.pagehelper.PageHelper;
import com.zdx.dao.ITagDao;
import com.zdx.domain.Tag;
import com.zdx.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zdx
 * @function 功能
 * @date 2020/5/18 21:51
 */
@Service
public class TagServiceImpl implements ITagService {

    @Autowired
    private ITagDao tagDao;

    @Transactional()
    @Override
    public void saveTag(Tag tag) {
        tagDao.saveTag(tag);
    }

    @Override
    public Tag getTag(Integer id) {

        return tagDao.getTag(id);
    }

    @Override
    public List<Tag> pageTag(int pageNum, int pageSize) {
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Tag> tags = tagDao.pageTag();
        return tags;
    }

    @Override
    @Transactional
    public void updateTag(Integer id, Tag tag) {
        tagDao.updateTag(id, tag);
    }

    @Override
    @Transactional
    public void deleteTag(Integer id) {
        tagDao.deleteTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Override
    public List<Tag> listAllTag() {
        return tagDao.listAllTag();
    }

    //将tagids的字符串转换成list
    private List<Integer> convertToList(String ids) {
        List<Integer> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Integer(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public List<Integer> listTag(String ids) { //1,2,3

        return convertToList(ids);
    }

    @Override
    public List<Tag> findBlog_TagAll(Integer id) {
        return tagDao.findBlog_TagAll(id);
    }

    @Override
    public List<Tag> findAllTag_Blog() {
        return tagDao.findAllTag_Blog();
    }
}
