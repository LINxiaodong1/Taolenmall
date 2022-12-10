package com.lin.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.common.utils.PageUtils;
import com.lin.common.utils.Query;

import com.lin.gulimall.product.dao.CategoryDao;
import com.lin.gulimall.product.entity.CategoryEntity;
import com.lin.gulimall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> categoryEntityList = baseMapper.selectList(null);

        List<CategoryEntity> levelList1=categoryEntityList.stream().filter(categoryEntity ->
                        categoryEntity.getParentCid()==0
        )
                .map(menu->{
                    menu.setChildren(getChildren(menu,categoryEntityList));
                    return menu;

        }).sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort()))).collect(Collectors.toList());
        return levelList1;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {

        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public Long[] findCatelogPathId(Long catelogId) {
        List<Long> paths=new ArrayList<>();
        List<Long> path = this.findParentPath(catelogId, paths);
        Collections.reverse(path);
//        return (Long[]) path.toArray();
       return path.toArray(new Long[path.size()]);
    }
    private List<Long> findParentPath(Long catelogId,List<Long> paths){
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if(byId.getParentCid()!=0){
            findParentPath(byId.getParentCid(),paths);

        }
        return paths;
    }

    private List<CategoryEntity> getChildren(CategoryEntity menu, List<CategoryEntity> categoryEntityList) {
        List<CategoryEntity> childrenList=categoryEntityList.stream().filter(categoryEntity -> categoryEntity.getParentCid()==menu.getCatId()).map(categoryEntity -> {
            categoryEntity.setChildren(getChildren(categoryEntity,categoryEntityList));
            return categoryEntity;
        }).sorted(Comparator.comparingInt(menu2 -> (menu2.getSort() == null ? 0 : menu2.getSort()))).collect(Collectors.toList());
        return childrenList;
    }

}