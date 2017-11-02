package com.jhon.rain.rbac.service;

import com.jhon.rain.rbac.dto.RainUserCondition;
import com.jhon.rain.rbac.dto.RainUserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>功能描述</br> 用户管理的接口定义 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainUserService
 * @date 2017/11/2 21:42
 */
public interface RainUserService {

	/**
	 * <pre>创建用户</pre>
	 *
	 * @param userInfo 用户信息
	 * @return
	 */
	RainUserVO create(RainUserVO userInfo);

	/**
	 * <pre>更新用户信息 </pre>
	 *
	 * @param userInfo
	 * @return
	 */
	RainUserVO update(RainUserVO userInfo);

	/**
	 * <pre>删除用户信息</pre>
	 *
	 * @param id
	 */
	void delete(Long id);

	/**
	 * <pre>获取用户信息</pre>
	 *
	 * @param id 用户主键信息
	 * @return
	 */
	RainUserVO getInfo(Long id);

	/**
	 * <pre>分页查询用户信息</pre>
	 *
	 * @param condition 查询条件
	 * @param pageable  分页对象
	 * @return 分页数据
	 */
	Page<RainUserVO> query(RainUserCondition condition, Pageable pageable);
}
