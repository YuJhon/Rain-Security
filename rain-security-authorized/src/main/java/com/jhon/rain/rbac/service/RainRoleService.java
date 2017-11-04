package com.jhon.rain.rbac.service;

import com.jhon.rain.rbac.dto.RainRoleVO;

import java.util.List;

/**
 * <p>功能描述</br> 角色接口的定义 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainRoleService
 * @date 2017/11/2 21:42
 */
public interface RainRoleService {

	/**
	 * <pre>创建角色</pre>
	 *
	 * @param roleInfo
	 * @return
	 */
	RainRoleVO create(RainRoleVO roleInfo);

	/**
	 * <pre>更新角色</pre>
	 *
	 * @param roleInfo
	 * @return
	 */
	RainRoleVO update(RainRoleVO roleInfo);

	/**
	 * <pre>删除角色</pre>
	 *
	 * @param id
	 */
	void delete(Long id);

	/**
	 * <pre>获取角色详细信息</pre>
	 *
	 * @param id
	 * @return
	 */
	RainRoleVO getInfo(Long id);

	/**
	 * <pre>查询所有角色</pre>
	 *
	 * @return
	 */
	List<RainRoleVO> findAll();

	/**
	 * <pre>获取角色对应的资源 </pre>
	 *
	 * @param id
	 * @return
	 */
	String[] getRoleResources(Long id);

	/**
	 * <pre>角色和资源的关联 </pre>
	 *
	 * @param id
	 * @param ids
	 */
	void setRoleResources(Long id, String ids);
}
