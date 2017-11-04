package com.jhon.rain.rbac.repository;

import com.jhon.rain.rbac.domain.RainResourceDO;
import org.springframework.stereotype.Repository;

/**
 * <p>功能描述</br> 资源数据访问层 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainResourceRepository
 * @date 2017/11/2 19:42
 */
@Repository
public interface RainResourceRepository extends RainRepository<RainResourceDO> {

	RainResourceDO findByName(String name);
}
