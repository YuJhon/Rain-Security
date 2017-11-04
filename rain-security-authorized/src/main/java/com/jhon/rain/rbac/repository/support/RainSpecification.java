package com.jhon.rain.rbac.repository.support;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainSpecification
 * @date 2017/11/4 9:29
 */
public abstract class RainSpecification<D, C> extends AbstractEventConditionBuilder<D, C> implements Specification<D> {
	/**
	 * 构造函数
	 *
	 * @param condition
	 */
	public RainSpecification(C condition) {
		super(condition);
	}

	/**
	 * 构建查询条件，子类必须实现addCondition方法来编写查询的逻辑。
	 * <p>
	 * 子类可以通过addFetch方法控制查询的关联和抓取行为。
	 */
	public Predicate toPredicate(Root<D> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		if (Long.class != query.getResultType()) {
			addFetch(root);
		}

		List<Predicate> predicates = new ArrayList<>();

		QueryWrapper<D> queryWrapper = new QueryWrapper<>(root, query, cb, predicates);

		addCondition(queryWrapper);

		Predicate permissionCondition = getPermissionCondition(queryWrapper);
		if (permissionCondition != null) {
			queryWrapper.addPredicate(permissionCondition);
		}

		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	/**
	 * 添加权限条件，如果要查询的domain实现了{@link ManagedByOrgan}接口，那么传入的Condition对象也应该实现
	 * {@link ManagedByOrgan}接口，
	 * 程序会尝试从Condition对象获取organFullId,然后作为like查询条件添加到查询中。
	 * 查出所有以传入的organFullId开头的domain.
	 *
	 * @param queryWraper
	 * @return
	 */
	protected Predicate getPermissionCondition(QueryWrapper<D> queryWraper) {
		return null;
	}

	/**
	 * <pre>子类可以通过覆盖此方法实现关联抓取，避免n+1查询</pre>
	 *
	 * @param root
	 */
	protected void addFetch(Root<D> root) {

	}

	protected abstract void addCondition(QueryWrapper<D> queryWrapper);
}
