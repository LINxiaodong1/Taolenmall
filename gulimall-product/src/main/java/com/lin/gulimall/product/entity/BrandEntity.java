package com.lin.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.lin.common.exception.addGroup;
import com.lin.common.valid.ListValue;
import com.lin.common.valid.UpdateStatusGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Ʒ
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-12-04 14:02:58
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Ʒ
	 */

	@TableId
	private Long brandId;
	/**
	 * Ʒ
	 */
	@NotBlank(message = "品牌名必须输入")
	private String name;
	/**
	 * Ʒ
	 */
	@URL(message = "logo必须是合法的url地址")
	private String logo;
	/**
	 * 
	 */
	private String descript;
	/**
	 * 
	 */
	@ListValue(vals={0,1},groups = {UpdateStatusGroup.class})

	private Integer showStatus;
	/**
	 * 
	 */
	@Pattern(regexp = "^[a-zA-Z]$",message = "检索首字母必须是一个字母",groups = {addGroup.class})
	private String firstLetter;
	/**
	 * 
	 */
	@Min(value = 0,message = "排序值必须大于0")
	private Integer sort;

}
