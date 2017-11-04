package com.jhon.rain.rbac.repository.support;

import org.springframework.core.convert.converter.Converter;

/**
 * <p>功能描述</br> DO2VO的转换顶级接口的定义</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName DO2VOConverter
 * @date 2017/11/3 9:30
 */
public interface DO2VOConverter<D, V> extends Converter<D, V> {
}
