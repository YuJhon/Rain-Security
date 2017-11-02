package com.jhon.rain.rbac.repository;

import com.jhon.rain.rbac.domain.RainUserDO;
import org.springframework.stereotype.Repository;

/**
 * <p>功能描述</br> 用户数据访问层 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainUserRepository
 * @date 2017/11/2 19:42
 */
@Repository
public interface RainUserRepository extends RainRepository<RainUserDO> {

	/**
	 * <pre>通过用户名查询用户</pre>
	 * @param username 用户名
	 * @return 用户数据
	 */
	RainUserDO findByUsername(String username);
}
