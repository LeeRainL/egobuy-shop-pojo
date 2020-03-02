package com.igeekhome.egobuygoodsservice.service.impl;

import com.igeekhome.egobuy.pojo.TreeNode;
import com.igeekhome.egobuygoodsservice.mapper.ItemCatMapper;
import com.igeekhome.egobuygoodsservice.service.IItemCatService;
import com.igeekhome.shop.pojo.TbItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yadonghe
 * @date 2020-03-02 14:25
 */
@Service
public class ItemCatServiceImpl implements IItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<TreeNode> selectNodes(Long parentId) {
        Example example = new Example(TbItemCat.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", parentId);

        List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);

        List<TreeNode> nodes = new ArrayList<>();
        for (TbItemCat itemCat : tbItemCats) {
            nodes.add(new TreeNode(itemCat.getId(), itemCat.getName(), itemCat.getIsParent()==1?false:true));
        }
        return nodes;
    }
}
