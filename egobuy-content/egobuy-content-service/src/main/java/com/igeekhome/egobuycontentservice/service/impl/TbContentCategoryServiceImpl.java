package com.igeekhome.egobuycontentservice.service.impl;

import com.igeekhome.egobuy.content.pojo.TbContentCategory;
import com.igeekhome.egobuy.pojo.TreeNodeV2;
import com.igeekhome.egobuycontentservice.mapper.ContentCategoryMapper;
import com.igeekhome.egobuycontentservice.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容分类(TbContentCategory)表服务实现类
 *
 * @author makejava
 * @since 2020-03-03 11:33:53
 */
@Service("tbContentCategoryService")
@Transactional
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TreeNodeV2> queryTreeNode() {
        //根节点集合
        List<TreeNodeV2> tops = new ArrayList<>();
        //非跟节点集合
        List<TreeNodeV2> subs = new ArrayList<>();
        //所有节点集合
        List<TreeNodeV2> all = new ArrayList<>();
        List<TbContentCategory> categories = contentCategoryMapper.selectAll();
        //获取根节点
        for(TbContentCategory category : categories) {
            TreeNodeV2 node = new TreeNodeV2(category.getId(),category.getParentId(), category.getName(), category.getIsParent()==1?false:true, new ArrayList<>());
            if (category.getParentId() == 0) {
                tops.add(node);
            } else {
                subs.add(node);
            }
            all.add(node);
        }
        //遍历所有的非根节点
        for (TreeNodeV2 node : subs) {
            //根据父节点id在all找当前节点的父节点
            /*
            for (TreeNodeV2 n : all) {
                if (n.getId() == node.getParentId()) {
                    n就是parent
                }
            }
             */
            //node的父节点
            TreeNodeV2 parent = all.stream().filter(n -> node.getParentId().equals(n.getId())).findFirst().orElse(null);
            //将当前节点存放到parent的children中
            if (parent != null) {
                parent.getChildren().add(node);
            }

        }

        return tops;
    }

    @Override
    @Transactional(readOnly = true)
    public TbContentCategory queryById(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TbContentCategory> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public TbContentCategory insert(TbContentCategory tbContentCategory) {
        tbContentCategory.setIsParent(0);
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(tbContentCategory.getCreated());
        contentCategoryMapper.insert(tbContentCategory);

        //更新父节点，如果父节点原来parentId为0，需要修改为1
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        if (parent.getIsParent() == 0) {
            parent.setIsParent(1);
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        return tbContentCategory;
    }

    @Override
    public TbContentCategory update(TbContentCategory tbContentCategory) {
        contentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        //1.先获取父级id
        TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
        Long parentId = category.getParentId();
        //contentCategoryMapper.deleteByPrimaryKey(id);
        //递归删除当前节点以及子节点
        deleteNode(id);
        //判断当前节点父节点是否还有子节点，如果没有，那么需要将当前节点父节点的isParent更新为0
        //1.获取父节点数据 Long parentId = category.getParentId();
        Example example = new Example(TbContentCategory.class);
        example.createCriteria().andEqualTo("parentId", parentId);
        //父节点的其他子节点
        List<TbContentCategory> subs = contentCategoryMapper.selectByExample(example);
        if (subs == null || subs.size() == 0) {
            //父节点没有其他子节点，需要将isParent更新为0
            //2.获取父节点
            TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
            parent.setIsParent(0);
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        return true;
    }

    /**
     * 根据父id删除子元素以及本身
     * @param parentId
     */
    public void deleteNode(Long parentId) {
        //找出当前节点所有子元素进行删除
        Example example = new Example(TbContentCategory.class);
        example.createCriteria().andEqualTo("parentId", parentId);
        //直接子节点
        List<TbContentCategory> subs = contentCategoryMapper.selectByExample(example);
        //如果存在子节点，那么遍历每个子节点，通过递归删除子节点的子节点
        if (subs != null && subs.size() > 0) {
            for (TbContentCategory category : subs) {
                deleteNode(category.getId());
            }
        }
        //删除本身
        contentCategoryMapper.deleteByPrimaryKey(parentId);
    }
}