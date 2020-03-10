package com.igeekhome.egobuygoodsservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeekhome.egobuy.util.CustomPageInfo;
import com.igeekhome.egobuy.util.IDUtil;
import com.igeekhome.egobuygoodsservice.mapper.ItemDescMapper;
import com.igeekhome.egobuygoodsservice.mapper.ItemMapper;
import com.igeekhome.egobuygoodsservice.service.IItemService;
import com.igeekhome.egobuygoodsservice.vo.ItemAddVo;
import com.igeekhome.egobuygoodsservice.vo.ItemQueryVo;
import com.igeekhome.shop.pojo.TbItem;
import com.igeekhome.shop.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author yadonghe
 * @date 2020-02-27 15:17
 */
@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Autowired
    private TemplateEngine engine;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "itemAddTopic")
    private Destination topic;


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

    @Override
    @Transactional //对该方法开启事务支持
    public void addItemAndDesc(ItemAddVo item) {
        Long id = IDUtil.generateId();
        TbItem tbItem = new TbItem();
        tbItem.setId(id);
        tbItem.setTitle(item.getTitle());
        tbItem.setSellPoint(item.getSellPoint());
        tbItem.setPrice(item.getPrice());
        tbItem.setBarcode(item.getBarcode());
        tbItem.setCid(item.getCid());
        tbItem.setImage(item.getImage());
        tbItem.setNum(item.getNum());
        //补全item参数，上下架(默认上架)、创建时间、更新时间
        tbItem.setStatus(1);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(tbItem.getCreated());
        //保存商品信息TbItem（自增长主键返回）
        itemMapper.insert(tbItem);
        //构建商品详情对象并保存（补全参数：商品id、创建时间、更新时间）
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(id);
        tbItemDesc.setItemDesc(item.getContent());
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(tbItemDesc.getCreated());
        itemDescMapper.insert(tbItemDesc);



        //发送消息即可(将id当作消息进行发送)
        jmsMessagingTemplate.convertAndSend(topic, id + "");
        //1.商品数据库保存
        //新增商品成功后
        //2.实现该新增的商品页面静态化
        //outPutHTML(id);
        //3.新增索引
        //
    }

    @Override
    public List<TbItem> select() {
        return itemMapper.selectByQuery(null);
    }

    @Override
    public void outPutHTML(Long id) {

        //1.获取商品、商品详情信息
        TbItem tbItem = itemMapper.selectByPrimaryKey(id);
        TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(id);
        System.out.println("tbItemDesc===>" + tbItemDesc);
        //2.输出静态HTML（页面静态化）
        Context context = new Context();
        context.setVariable("item", tbItem);
        context.setVariable("tbItemDesc", tbItemDesc);
        try {
            engine.process("item", context, new FileWriter(new File("D:\\htmls\\" + id + ".html")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public TbItem get(Long id) {
        return itemMapper.selectByPrimaryKey(id);
    }
}
