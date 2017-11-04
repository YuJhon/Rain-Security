package com.jhon.rain.rbac.repository.support;

import lombok.Data;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName AbstractEventConditionBuilder
 * @date 2017/11/4 9:47
 */
@Data
public abstract class AbstractEventConditionBuilder<D, C> extends AbstractConditionBuilder<D> {

	/**
	 * 查询条件
	 */
	private C condition;

	/**
	 * <pre>添加小于等于条件查询</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 */
	protected void addLessThanOrEqualCondition(QueryWrapper<D> queryWrapper, String field) {
		addLessThanOrEqualConditionToColumn(queryWrapper, field,
						(Comparable) getValue(getCondition(), field));
	}

	/**
	 * <pre>添加小于等于条件查询</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 * @param column
	 */
	protected void addLessThanOrEqualCondition(QueryWrapper<D> queryWrapper, String field, String column) {
		addLessThanOrEqualConditionToColumn(queryWrapper, column,
						(Comparable) getValue(getCondition(), field));
	}


	/**
	 * <pre>添加小于查询条件</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 */
	protected void addLessThanCondition(QueryWrapper<D> queryWrapper, String field) {
		addLessThanConditionToColumn(queryWrapper, field,
						(Comparable) getValue(getCondition(), field));
	}

	/**
	 * <pre>添加小于查询条件</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 * @param column
	 */
	protected void addLessThanCondition(QueryWrapper<D> queryWrapper, String field, String column) {
		addLessThanConditionToColumn(queryWrapper, column,
						(Comparable) getValue(getCondition(), field));
	}

	/**
	 * <pre>添加大于等于条件查询</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 */
	protected void addGreaterThanOrEqualCondition(QueryWrapper<D> queryWrapper, String field) {
		addGreaterThanOrEqualConditionToColumn(queryWrapper, field,
						(Comparable) getValue(getCondition(), field));
	}

	/**
	 * <pre>添加大于等于条件查询</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 * @param column
	 */
	protected void addGreaterThanOrEqualCondition(QueryWrapper<D> queryWrapper, String field, String column) {
		addGreaterThanOrEqualConditionToColumn(queryWrapper, column,
						(Comparable) getValue(getCondition(), field));
	}

	/**
	 * <pre>添加大于条件</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 */
	protected void addGreaterThanCondition(QueryWrapper<D> queryWrapper, String field) {
		addGreaterThanConditionToColumn(queryWrapper, field,
						(Comparable) getValue(getCondition(), field));
	}

	/**
	 * <pre>添加大于条件</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 * @param column
	 */
	protected void addGreaterThanCondition(QueryWrapper<D> queryWrapper, String field, String column) {
		addGreaterThanConditionToColumn(queryWrapper, column,
						(Comparable) getValue(getCondition(), field));
	}

	/**
	 * <pre>添加介于条件</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 */
	protected void addBetweenCondition(QueryWrapper<D> queryWrapper, String field) {
		addBetweenConditionToColumn(queryWrapper, field, field, field + "To");
	}

	/**
	 * <pre>添加介于条件</pre>
	 *
	 * @param queryWrapper
	 * @param startField   开始值字段
	 * @param endField     结束值字段
	 * @param column       需要添加between条件的列名
	 */
	protected void addBetweenCondition(QueryWrapper<D> queryWrapper, String startField, String endField, String column) {
		addBetweenConditionToColumn(queryWrapper, column,
						(Comparable) getValue(getCondition(), startField),
						(Comparable) getValue(getCondition(), endField));
	}

	/**
	 * <pre>添加in语句</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 */
	protected void addInCondition(QueryWrapper<D> queryWrapper, String field) {
		addInConditionToColumn(queryWrapper, field, getValue(getCondition(), field));
	}

	/**
	 * <pre>添加in语句</pre>
	 *
	 * @param queryWrapper
	 * @param field        指出查询条件的值从condition对象的哪个字段里取
	 * @param column       指出要向哪个字段添加条件
	 */
	protected void addInCondition(QueryWrapper<D> queryWrapper, String field, String column) {
		addInConditionToColumn(queryWrapper, column, getValue(getCondition(), field));
	}


	/**
	 * <pre>添加不等于查询条件</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 */
	protected void addNotEqualsCondition(QueryWrapper<D> queryWrapper, String field) {
		addNotEqualsConditionToColumn(queryWrapper, field, getValue(getCondition(), field));
	}

	/**
	 * <pre>添加不等于查询条件</pre>
	 *
	 * @param queryWrapper
	 * @param field        指出查询条件的值从condition对象的哪个字段里取
	 * @param column       指出要向哪个字段添加条件
	 */
	protected void addNotEqualsCondition(QueryWrapper<D> queryWrapper, String field, String column) {
		addNotEqualsConditionToColumn(queryWrapper, column, getValue(getCondition(), field));
	}


	/**
	 * <pre>添加等于条件</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 */
	protected void addEqualsCondition(QueryWrapper<D> queryWrapper, String field) {
		addEqualsConditionToColumn(queryWrapper, field, getValue(getCondition(), field));
	}

	/**
	 * <pre>添加等于条件</pre>
	 *
	 * @param queryWrapper
	 * @param field        指出查询条件的值从condition对象的哪个字段里取
	 * @param column       指出要向哪个字段添加条件
	 */
	protected void addEqualsCondition(QueryWrapper<D> queryWrapper, String field, String column) {
		addEqualsConditionToColumn(queryWrapper, column, getValue(getCondition(), field));
	}

	/**
	 * <pre>以xx开头的模糊查询</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 */
	protected void addStartWidthLikeCondition(QueryWrapper<D> queryWrapper, String field) {
		addRightLikeConditionToColumn(queryWrapper, field, (String) getValue(getCondition(), field));
	}

	/**
	 * <pre>以xx开头的模糊查询</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 * @param column
	 */
	protected void addStartWidthLikeCondition(QueryWrapper<D> queryWrapper, String field, String column) {
		addRightLikeConditionToColumn(queryWrapper, column, (String) getValue(getCondition(), field));
	}

	/**
	 * <pre>模糊查询：字段名和属性名一致</pre>
	 *
	 * @param queryWrapper
	 * @param field
	 */
	protected void addLikeCondition(QueryWrapper<D> queryWrapper, String field) {
		addLikeCondition(queryWrapper, field, field);
	}

	/**
	 * <pre>模糊查询</pre>
	 *
	 * @param queryWrapper
	 * @param field        字段名
	 * @param column       表列名
	 */
	protected void addLikeCondition(QueryWrapper<D> queryWrapper, String field, String column) {
		addLikeConditionToColumn(queryWrapper, column, (String) getValue(getCondition(), field));
	}


	/**
	 * 构造函数
	 *
	 * @param condition
	 */
	public AbstractEventConditionBuilder(C condition) {
		this.condition = condition;
	}

	/**
	 * <pre>获取查询条件对象指定属性名称的值</pre>
	 *
	 * @param condition
	 * @param field
	 * @return
	 */
	private Object getValue(C condition, String field) {
		try {
			return PropertyUtils.getProperty(condition, field);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
}
