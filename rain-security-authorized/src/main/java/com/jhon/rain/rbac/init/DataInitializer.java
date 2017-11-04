package com.jhon.rain.rbac.init;

/**
 * <p>功能描述</br> 数据初始化接口定义：封装系统数据的初始化行为，开发可以向系统中添加此接口的实现类，来增加自定义的数据初始化行为</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName DataInitializer
 * @date 2017/11/4 14:42
 */
public interface DataInitializer {

	/**
	 * <pre>初始化器的执行顺序，数值越大的初始化器越靠后执行</pre>
	 *
	 * @return
	 */
	Integer getIndex();

	/**
	 * <pre>初始化数据的方法</pre>
	 *
	 * @throws Exception
	 */
	void init() throws Exception;
}
