package com.igeekhome.egobuycontentservice.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeekhome.egobuy.content.pojo.TbContent;
import com.igeekhome.egobuy.util.CustomPageInfo;
import com.igeekhome.egobuycontentservice.mapper.ContentMapper;
import com.igeekhome.egobuycontentservice.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * (TbContent)表服务实现类
 *
 * @author makejava
 * @since 2020-03-04 11:00:56
 */
@Service("tbContentService")
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    ContentMapper contentMapper;

    @Override
    public TbContent queryById(Long id) {
        return null;
    }

    @Override
    public CustomPageInfo<TbContent> queryByParentId(Long parentId, Integer page, Integer limit) {
        Example example = new Example(TbContent.class);
        example.createCriteria().andEqualTo("categoryId",parentId);
        //查询数据
        List<TbContent> contents = contentMapper.selectByExample(example);
        //进行分页
        PageHelper.startPage(page,limit);
        PageInfo<TbContent> pageInfo = new PageInfo(contents);
        //获取总数
        long total = pageInfo.getTotal();
        //返回分页数据
        return new CustomPageInfo<TbContent>(contents,total);
    }

    @Override
    public List<TbContent> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public TbContent insert(TbContent tbContent) {
        //设置创建时间
        tbContent.setCreated(new Date());
        //设置更新时间
        tbContent.setUpdated(tbContent.getCreated());
        contentMapper.insertSelective(tbContent);
        return null;
    }

    @Override
    public TbContent update(TbContent tbContent) {
        //更新时间
        tbContent.setUpdated(new Date());
        contentMapper.updateByPrimaryKey(tbContent);
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        contentMapper.deleteByPrimaryKey(id);
        return true;
    }
}