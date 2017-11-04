package com.jhon.rain.rbac.repository.support;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.data.domain.PageRequest;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.Date;

/**
 * <p>功能描述</br> 抽象的查询构建器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName AbstractConditionBuilder
 * @date 2017/11/4 9:32
 */
public abstract class AbstractConditionBuilder<D> {

	/**
	 * <pre>添加In语句</pre>
	 *
	 * @param queryWrapper
	 * @param column
	 * @param values
	 */
	protected void addInConditionToColumn(QueryWrapper<D> queryWrapper, String column, Object values) {
		if (needAddCondition(values)) {
			Path<?> fieldPath = getPath(queryWrapper.getRoot(), column);
			if (values.getClass().isArray()) {
				queryWrapper.addPredicate(fieldPath.in((Object[]) values));
			} else if (values instanceof Collection) {
				queryWrapper.addPredicate(fieldPath.in((Collection) values));
			}
		}
	}

	/**
	 * <pre>添加Between条件查询</pre>
	 *
	 * @param queryWrapper
	 * @param column       属性名
	 * @param minValue     范围下限
	 * @param maxValue     范围上限
	 */
	protected void addBetweenConditionToColumn(QueryWrapper<D> queryWrapper, String column, Comparable minValue, Comparable maxValue) {
		if (minValue != null || maxValue != null) {
			Path<? extends Comparable> fieldPath = getPath(queryWrapper.getRoot(), column);
			if (minValue != null && maxValue != null) {
				queryWrapper.addPredicate(queryWrapper.getCb().between(fieldPath, minValue, processMaxValueOnDate(maxValue)));
			} else if (minValue != null) {
				queryWrapper.addPredicate(queryWrapper.getCb().greaterThanOrEqualTo(fieldPath, minValue));
			} else if (maxValue != null) {
				queryWrapper.addPredicate(queryWrapper.getCb().lessThanOrEqualTo(fieldPath, processMaxValueOnDate(maxValue)));
			}
		}
	}

	/**
	 * <pre>对日期最大值的处理</pre>
	 *
	 * @param maxValue
	 * @return
	 */
	private Comparable processMaxValueOnDate(Comparable maxValue) {
		if (maxValue instanceof Date) {
			maxValue = new DateTime(maxValue).withTimeAtStartOfDay().plusDays(1).plusSeconds(-1).toDate();
		}
		return maxValue;
	}

	/**
	 * <pre>添加大于条件查询</pre>
	 *
	 * @param queryWrapper
	 * @param column
	 * @param minValue     范围下限
	 */
	protected void addGreaterThanConditionToColumn(QueryWrapper<D> queryWrapper, String column, Comparable minValue) {
		if (minValue != null) {
			Path<? extends Comparable> fieldPath = getPath(queryWrapper.getRoot(), column);
			queryWrapper.addPredicate(queryWrapper.getCb().greaterThan(fieldPath, minValue));
		}
	}

	/**
	 * <pre>添加大于等于查询条件</pre>
	 *
	 * @param queryWrapper
	 * @param column
	 * @param minValue
	 */
	protected void addGreaterThanOrEqualConditionToColumn(QueryWrapper<D> queryWrapper, String column, Comparable minValue) {
		if (minValue != null) {
			Path<? extends Comparable> fieldPath = getPath(queryWrapper.getRoot(), column);
			queryWrapper.addPredicate(queryWrapper.getCb().greaterThanOrEqualTo(fieldPath, minValue));
		}
	}

	/**
	 * <pre>添加小于条件查询</pre>
	 *
	 * @param queryWrapper
	 * @param column
	 * @param maxValue     范围上限
	 */
	protected void addLessThanConditionToColumn(QueryWrapper<D> queryWrapper, String column, Comparable maxValue) {
		if (maxValue != null) {
			Path<? extends Comparable> fieldPath = getPath(queryWrapper.getRoot(), column);
			queryWrapper.addPredicate(queryWrapper.getCb().lessThan(fieldPath, processMaxValueOnDate(maxValue)));
		}
	}

	/**
	 * <pre>添加小于等于条件查询</pre>
	 *
	 * @param queryWrapper
	 * @param column
	 * @param maxValue
	 */
	protected void addLessThanOrEqualConditionToColumn(QueryWrapper<D> queryWrapper, String column, Comparable maxValue) {
		if (maxValue != null) {
			Path<? extends Comparable> fieldPath = getPath(queryWrapper.getRoot(), column);
			queryWrapper.addPredicate(queryWrapper.getCb().lessThanOrEqualTo(fieldPath, processMaxValueOnDate(maxValue)));
		}
	}

	/**
	 * <pre>添加模糊查询条件</pre>
	 *
	 * @param queryWrapper
	 * @param column
	 * @param value
	 */
	protected void addLikeConditionToColumn(QueryWrapper<D> queryWrapper, String column, String value) {
		if (StringUtils.isNotBlank(value)) {
			queryWrapper.addPredicate(createLikeCondition(queryWrapper, column, value));
		}
	}

	/**
	 * <pre>以xx开头的模糊查询条件添加</pre>
	 *
	 * @param queryWrapper
	 * @param column
	 * @param value
	 */
	protected void addRightLikeConditionToColumn(QueryWrapper<D> queryWrapper, String column, String value) {
		if (StringUtils.isNotBlank(value)) {
			Path<String> fieldPath = getPath(queryWrapper.getRoot(), column);
			queryWrapper.addPredicate(queryWrapper.getCb().like(fieldPath, value + "%"));
		}
	}

	/**
	 * <pre>创建模糊查询条件</pre>
	 *
	 * @param queryWrapper
	 * @param column
	 * @param value
	 * @return
	 */
	protected Predicate createLikeCondition(QueryWrapper<D> queryWrapper, String column, String value) {
		Path<String> fieldPath = getPath(queryWrapper.getRoot(), column);
		Predicate condition = queryWrapper.getCb().like(fieldPath, "%" + value + "%");
		return condition;
	}

	/**
	 * <pre>添加等于条件</pre>
	 *
	 * @param queryWrapper
	 * @param column
	 * @param value
	 */
	protected void addEqualsConditionToColumn(QueryWrapper<D> queryWrapper, String column, Object value) {
		if (needAddCondition(value)) {
			Path<?> fieldPath = getPath(queryWrapper.getRoot(), column);
			queryWrapper.addPredicate(queryWrapper.getCb().equal(fieldPath, value));
		}
	}

	/**
	 * <pre>添加不等于条件</pre>
	 *
	 * @param queryWrapper
	 * @param column
	 * @param value
	 */
	protected void addNotEqualsConditionToColumn(QueryWrapper<D> queryWrapper, String column, Object value) {
		if (needAddCondition(value)) {
			Path<?> fieldPath = getPath(queryWrapper.getRoot(), column);
			queryWrapper.addPredicate(queryWrapper.getCb().notEqual(fieldPath, value));
		}
	}

	/**
	 * <pre>获取</pre>
	 *
	 * @param root
	 * @param property
	 * @return
	 */
	protected Path getPath(Root root, String property) {
		String[] names = StringUtils.split(property, ".");
		Path path = root.get(names[0]);
		for (int i = 0; i < names.length; i++) {
			path = path.get(names[i]);
		}
		return path;
	}

	/**
	 * <pre>判断是否添加where条件</pre>
	 *
	 * @param value 检测的对象
	 * @return 返回是否需要添加where
	 */
	protected boolean needAddCondition(Object value) {
		boolean addCondition = false;
		if (value != null) {
			if (value instanceof String) {
				if (StringUtils.isNotBlank(value.toString())) {
					addCondition = true;
				}
			} else if (value.getClass().isArray()) {
				if (ArrayUtils.isNotEmpty((Object[]) value)) {
					addCondition = true;
				}
			} else if (value instanceof Collection) {
				if (CollectionUtils.isNotEmpty((Collection) value)) {
					addCondition = true;
				}
			}
		}
		return addCondition;
	}
}
