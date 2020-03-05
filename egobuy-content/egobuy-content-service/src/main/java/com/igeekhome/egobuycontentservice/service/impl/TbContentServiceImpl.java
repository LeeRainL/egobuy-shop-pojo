package com.igeekhome.egobuycontentservice.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeekhome.egobuy.content.pojo.TbContent;
import com.igeekhome.egobuy.util.CustomPageInfo;
import com.igeekhome.egobuy.util.RedisUtil;
import com.igeekhome.egobuycontentservice.mapper.ContentMapper;
import com.igeekhome.egobuycontentservice.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

    @Autowired
    private RedisUtil redisUtil;

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
    public List<TbContent> queryByParentId(Long parentId) {
        //1.先查询缓存（内容、商品...）
        //K-V
        //查询大广告对应的内容
        // string
        //思路1： K->大广告分类的id 89(parentId) V-> [{id:1,title:标题1,url:''},...]
        //问题：89 id存在重复
        //解决方案1：在每个模块缓存字段前加前缀
        /*
        内容id为89 的缓存   CONTENT_89

        商品id为89的缓存    ITEM_89

        //...
         */
        //问题：某一个分类的下缓存难以管理
        // hash类型   key-(若干个key-value)
        //   内容       CONTENT - (分类id:当前分类下的内容)
        //   商品       ITEM - （商品id：商品的缓存)

        //2.如果缓存中存在数据，则直接返回，不需要查询数据库

        //问题：默认redis操作的是string类型，但是实际存放值的时候，更希望直接操作对象
        //默认的RedisTemplate是没有实现对象与JSON相互转换的
        //1.使用默认的RedisTemplate，手动进行转换 2.自定义RestTemplate，配置key value的转换
        //hget CONTENT 89

        Object content = redisUtil.hget("CONTENT", parentId + "");
        if (content != null) {
            System.out.println("命中缓存.....");
            //string->List<Content>
            return  (List<TbContent>)content;//返回数据
        }
        //10000
        //9900->删除缓存之前的数据，或者稍后再试
        //100->放行
        System.out.println("缓存未命中...查询数据库...");
        //3.如果缓存中不存在数据，则从数据库查询数据，查询完之后，在缓存中存放一份数据
        Example example = new Example(TbContent.class);
        example.createCriteria().andEqualTo("categoryId",parentId);
        //查询数据
        List<TbContent> contents = contentMapper.selectByExample(example);

        //存放缓存 默认60分钟过期
        redisUtil.hset("CONTENT", parentId+"", contents, 60*60);

        return contents;
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

        //缓存同步
        redisUtil.del("CONTENT");
        return null;
    }

    @Override
    public TbContent update(TbContent tbContent) {
        //更新时间
        tbContent.setUpdated(new Date());
        contentMapper.updateByPrimaryKey(tbContent);
        redisUtil.del("CONTENT");
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        contentMapper.deleteByPrimaryKey(id);
        redisUtil.del("CONTENT");
        return true;
    }
}