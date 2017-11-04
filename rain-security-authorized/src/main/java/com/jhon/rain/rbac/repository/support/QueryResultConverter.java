package com.jhon.rain.rbac.repository.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能描述</br> 查询结果装换器</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName QueryResultConverter
 * @date 2017/11/4 9:09
 */
@Slf4j
public class QueryResultConverter {

	/**
	 * <pre>根据类型装换</pre>
	 *
	 * @param pageData
	 * @param clazz
	 * @param pageable
	 * @param <D>
	 * @param <V>
	 * @return
	 */
	public static <D, V> Page<V> convert(Page<D> pageData, Class<V> clazz, Pageable pageable) {
		List<D> contents = pageData.getContent();
		List<V> vos = convert(contents, clazz);
		return new PageImpl<V>(vos, pageable, pageData.getTotalElements());
	}

	/**
	 * <pre>根据自定义的装换器装换</pre>
	 *
	 * @param pageData
	 * @param pageable
	 * @param converter
	 * @param <D>
	 * @param <V>
	 * @return
	 */
	public static <D, V> Page<V> convert(Page<D> pageData, Pageable pageable, DO2VOConverter<D, V> converter) {
		List<D> contents = pageData.getContent();
		List<V> vos = convert(contents, converter);
		return new PageImpl<V>(vos, pageable, pageData.getTotalElements());
	}


	/**
	 * <pre>对应的List装换为指定的Class对象</pre>
	 *
	 * @param contents 需要装换的内容
	 * @param clazz    目标对象的类型
	 * @param <V>
	 * @param <D>
	 * @return
	 */
	public static <D, V> List<V> convert(List<D> contents, Class<V> clazz) {
		List<V> vos = new ArrayList<>();
		for (D domain : contents) {
			V vo = null;
			try {
				vo = clazz.newInstance();
				BeanUtils.copyProperties(vo, domain);
			} catch (Exception e) {
				log.error("装换数据失败，{}", e);
				throw new RuntimeException("Transfer Data Failed");
			}

			if (vo != null) {
				vos.add(vo);
			}
		}
		return vos;
	}

	/**
	 * <pre>对应的List装换为指定的Class对象</pre>
	 *
	 * @param contents  需要装换的内容
	 * @param converter 自定义装换器(调用者自己实现接口)
	 * @param <D>
	 * @param <V>
	 * @return
	 */
	public static <D, V> List<V> convert(List<D> contents, DO2VOConverter<D, V> converter) {
		List<V> vos = new ArrayList<>();
		for (D domain : contents) {
			vos.add(converter.convert(domain));
		}
		return vos;
	}
}
