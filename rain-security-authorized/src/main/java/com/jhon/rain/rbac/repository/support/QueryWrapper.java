package com.jhon.rain.rbac.repository.support;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * <p>功能描述</br> 包装用于构建JPA动态查询时所需要的对象</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName QueryWrapper
 * @date 2017/11/4 9:00
 */
@Data
public class QueryWrapper<T> {

	/**
	 * JPA Root
	 */
	public Root<T> root;

	/**
	 * JPA CriteriaBuilder
	 */
	private CriteriaBuilder cb;

	/**
	 * JPA Predicate 集合
	 */
	private List<Predicate> predicates;

	/**
	 * JPA 查询对象
	 */
	private CriteriaQuery<?> query;

	/**
	 * <pre>构建函数</pre>
	 *
	 * @param root
	 * @param query
	 * @param cb
	 * @param predicates
	 */
	public QueryWrapper(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb, List<Predicate> predicates) {
		this.root = root;
		this.query = query;
		this.cb = cb;
		this.predicates = predicates;
	}

	/**
	 * <pre>添加Predicate</pre>
	 *
	 * @param predicate
	 */
	public void addPredicate(Predicate predicate) {
		this.predicates.add(predicate);
	}
}
