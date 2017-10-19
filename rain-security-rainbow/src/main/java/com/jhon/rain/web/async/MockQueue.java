package com.jhon.rain.web.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>功能描述</br> 模拟队列 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName MockQueue
 * @date 2017/10/18 11:27
 */
@Component
@Slf4j
public class MockQueue {

	/**
	 * 新订单
	 */
	private String placeOrder;

	/**
	 * 订单完成
	 */
	private String completeOrder;


	public String getPlaceOrder() {
		return placeOrder;
	}

	public void setPlaceOrder(String placeOrder) {
		new Thread(() -> {
			log.info("新单通知请求，" + placeOrder);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.completeOrder = placeOrder;
			log.info("下单请求处理完毕," + placeOrder);
		}).start();
	}

	public String getCompleteOrder() {
		return completeOrder;
	}

	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}
}
