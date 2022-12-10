package com.lin.gulimall.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.lin.common.exception.addGroup;
import com.lin.common.exception.updateGroup;
import com.lin.common.valid.UpdateStatusGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lin.gulimall.product.entity.BrandEntity;
import com.lin.gulimall.product.service.BrandService;
import com.lin.common.utils.PageUtils;
import com.lin.common.utils.R;

import javax.validation.Valid;


/**
 * Ʒ
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-12-04 14:02:58
 */
@RestController
@RequestMapping("/product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
 
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   
    public R save(@Validated(addGroup.class) @RequestBody BrandEntity brand/*, BindingResult bindingResult*/){
//        Map<String,String> map=new HashMap<>();
//        if (bindingResult.hasErrors()) {
//            bindingResult.getFieldErrors().forEach(item->{
//                String fieldName= item.getField();
//                String fieldError= item.getDefaultMessage();
//                map.put(fieldName,fieldError);
//
//            });

//            return R.error(400,"提交的数据不合法").put("data",map);
//        }
        brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   
    public R update(@RequestBody BrandEntity brand){
		brandService.updateById(brand);

        return R.ok();
    }
    @RequestMapping("/update/status")

    public R update1(@Validated(UpdateStatusGroup.class)@RequestBody BrandEntity brand){
        brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
