package com.jhon.rain.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * <p>功能描述</br> 队列监听器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName QueueListener
 * @date 2017/10/18 12:04
 */
@Component
@Slf4j
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private MockQueue mockQueue;

	@Autowired
	private DeferredResultHolder deferredResultHolder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		new Thread(() -> {
			while (true) {
				if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
					String orderNum = mockQueue.getCompleteOrder();
					log.info("返回订单处理结果：" + orderNum);
					deferredResultHolder.getMap().get(orderNum).setResult("Place Order Success");
					mockQueue.setCompleteOrder(null);
				} else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
