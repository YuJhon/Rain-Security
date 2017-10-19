package com.jhon.rain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>功能描述</br> 用户查询条件 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName UserQueryCondition
 * @date 2017/10/16 9:03
 */
@Data
public class UserQueryCondition {

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "年龄的开始值")
	private int age;

	@ApiModelProperty(value = "年龄的结束值")
	private int ageTo;

	/**
	 * 性别
	 */
	private String gender;
}
