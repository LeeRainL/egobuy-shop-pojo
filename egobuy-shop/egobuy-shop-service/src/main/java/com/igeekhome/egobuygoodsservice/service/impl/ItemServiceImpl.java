package com.igeekhome.egobuygoodsservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeekhome.egobuy.util.CustomPageInfo;
import com.igeekhome.egobuygoodsservice.mapper.ItemMapper;
import com.igeekhome.egobuygoodsservice.service.IItemService;
import com.igeekhome.egobuygoodsservice.vo.ItemQueryVo;
import com.igeekhome.shop.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author yadonghe
 * @date 2020-02-27 15:17
 */
@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private ItemMapper itemMapper;


    @Override
    public CustomPageInfo<TbItem> select(Integer page, Integer limit, ItemQueryVo query) {
        //设置分页参数
        PageHelper.startPage(page, limit);
         /*
        //创建查询对象
        Example example = new Example(TbItem.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(query.getId())) {
            criteria.andEqualTo("id", query.getId());
        }
        if (!StringUtils.isEmpty(query.getTitle())) {
            criteria.andLike("title", "%" + query.getTitle() + "%");
        }
        if (!StringUtils.isEmpty(query.getSellPoint())) {
            criteria.andLike("sellPoint", "%" + query.getSellPoint() + "%");
        }
        if (!StringUtils.isEmpty(query.getMinPrice())) {
            criteria.andGreaterThanOrEqualTo("price", query.getMinPrice());
        }
        if (!StringUtils.isEmpty(query.getMaxPrice())) {
            criteria.andLessThanOrEqualTo("price", query.getMaxPrice());
        }
        //...
       
        List<TbItem> items = itemMapper.selectByExample(example);
        for (TbItem item : items) {
            //item.getCid(); 通过分类id调用分类的根据id查询详情的方法

            item.setCName();
        }
        */

        List<TbItem> items = itemMapper.selectByQuery(query);

        //分页对象
        PageInfo<TbItem> pageInfo = new PageInfo<>(items);
        //总条数
        long total = pageInfo.getTotal();
        return new CustomPageInfo<>(items, total);
    }

    @Override
    public void update(TbItem tbItem) {
        itemMapper.updateByPrimaryKeySelective(tbItem);
    }
}
