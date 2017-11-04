package com.jhon.rain.rbac.repository.spec;

import com.jhon.rain.rbac.domain.RainUserDO;
import com.jhon.rain.rbac.dto.RainUserCondition;
import com.jhon.rain.rbac.repository.support.QueryWrapper;
import com.jhon.rain.rbac.repository.support.RainSpecification;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainUserSpec
 * @date 2017/11/4 10:55
 */
public class RainUserSpec extends RainSpecification<RainUserDO, RainUserCondition> {

	/**
	 * 构造函数
	 *
	 * @param condition
	 */
	public RainUserSpec(RainUserCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWrapper<RainUserDO> queryWrapper) {
		addLikeCondition(queryWrapper, "username");
	}
}
