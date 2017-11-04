package com.jhon.rain.rbac.repository.support;

import org.springframework.beans.BeanUtils;

/**
 * <p>功能描述</br> 抽象类DO转VO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName AbstractDO2VOConverter
 * @date 2017/11/3 9:28
 */
public abstract class AbstractDO2VOConverter<D, V> implements DO2VOConverter<D, V> {

	@Override
	public V convert(D domain) {
		V vo = null;
		try {
			Class<V> clazz = GenericUtils.getGenericClass(getClass(), 1);
			vo = clazz.newInstance();
			BeanUtils.copyProperties(domain, vo);
			doConvert(domain, vo);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return vo;
	}

	/**
	 * <pre>子类实现自己的附加装换逻辑</pre>
	 *
	 * @param domain 实体类
	 * @param vo     ViewObject
	 */
	protected abstract void doConvert(D domain, V vo);
}
