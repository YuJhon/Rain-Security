package com.jhon.rain.rbac.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>功能描述</br> 用户信息 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainUserVO
 * @date 2017/11/2 17:22
 */
@Data
public class RainUserVO {

	private Long id;
	/**
	 * 角色id
	 */
	@NotBlank(message = "角色id不能为空")
	private Long roleId;

	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空")
	private String username;
}
