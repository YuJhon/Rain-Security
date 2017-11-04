package com.jhon.rain.rbac.service;

import com.jhon.rain.rbac.dto.RainResourceVO;

/**
 * <p>功能描述</br> 资源服务的业务逻辑接口 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainResourceService
 * @date 2017/11/2 21:42
 */
public interface RainResourceService {

	/**
	 * <pre>获取资源树</pre>
	 *
	 * @param userId 用户Id
	 * @return
	 */
	RainResourceVO getTree(Long userId);

	/**
	 * <pre>根据资源ID查询资源信息</pre>
	 *
	 * @param id
	 * @return
	 */
	RainResourceVO getInfo(Long id);

	/**
	 * <pre>新增资源</pre>
	 *
	 * @param resourceInfo
	 * @return
	 */
	RainResourceVO create(RainResourceVO resourceInfo);

	/**
	 * <pre>更新资源</pre>
	 *
	 * @param resourceInfo
	 * @return
	 */
	RainResourceVO update(RainResourceVO resourceInfo);

	/**
	 * <pre>根据Id删除资源</pre>
	 *
	 * @param id
	 */
	void delete(Long id);

	/**
	 * <pre>上下移动资源</pre>
	 *
	 * @param id
	 * @param up
	 * @return
	 */
	Long move(Long id, boolean up);
}
