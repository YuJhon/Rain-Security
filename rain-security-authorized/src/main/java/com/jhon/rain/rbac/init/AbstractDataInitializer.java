package com.jhon.rain.rbac.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>功能描述</br> 抽象数据初始化器，所有的数据初始化器应该继承此类</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName AbstractDataInitializer
 * @date 2017/11/4 14:49
 */
@Slf4j
public abstract class AbstractDataInitializer implements DataInitializer {

	@Override
	@Transactional
	public void init() throws Exception {
		if (isNeedInit()) {
			log.info("使用 {} 初始化数据", getClass().getSimpleName());
			doInit();
			log.info("使用 {} 初始化数据完成", getClass().getSimpleName());
		}
	}

	/**
	 * <pre>实际的初始化数据操作</pre>
	 *
	 * @throws Exception
	 */
	protected abstract void doInit() throws Exception;

	/**
	 * <pre>是否需要执行初始化操作</pre>
	 *
	 * @return
	 */
	protected abstract boolean isNeedInit();
}
