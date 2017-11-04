package com.jhon.rain.rbac.init;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/**
 * <p>功能描述</br> 系统初始化器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SystemDataInitializer
 * @date 2017/11/4 14:57
 */
@Component
@Slf4j
public class SystemDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private List<DataInitializer> dataInitializers;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (CollectionUtils.isNotEmpty(dataInitializers)) {

			/*dataInitializers.sort((initor1, initor2) -> {
				return initor1.getIndex().compareTo(initor2.getIndex());
			});*/

			dataInitializers.sort(Comparator.comparing(DataInitializer::getIndex));

			dataInitializers.stream().forEach(dataInitializer -> {
				try {
					dataInitializer.init();
				} catch (Exception e) {
					log.error("系统数据初始化失败({}):{}", dataInitializer.getClass().getSimpleName(), e);
					e.printStackTrace();
				}
			});
		}
	}
}
