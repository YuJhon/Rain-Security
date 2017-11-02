package com.jhon.rain.rbac.dto;

import com.jhon.rain.rbac.enums.RainResourceTypeEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能描述</br> 资源信息装换类  </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainResourceVO
 * @date 2017/11/2 17:09
 */
@Data
public class RainResourceVO {

	/**
	 * 资源ID
	 */
	private Long id;

	/**
	 * 父资源ID
	 */
	private Long parentId;

	/**
	 * 资源名称
	 */
	private String name;

	/**
	 * 资源链接
	 */
	private String link;

	/**
	 * 资源图标
	 */
	private String icon;

	/**
	 * 资源类型
	 */
	private RainResourceTypeEnum type;

	/**
	 * 子节点
	 */
	private List<RainResourceVO> children = new ArrayList<>();
}
