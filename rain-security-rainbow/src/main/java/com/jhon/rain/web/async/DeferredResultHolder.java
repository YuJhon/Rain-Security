package com.jhon.rain.web.async;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>功能描述</br> 异步结果处理器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName DeferredResultHolder
 * @date 2017/10/18 11:55
 */
@Component
@Data
public class DeferredResultHolder {

	private Map<String, DeferredResult<String>> map = new HashMap<String, DeferredResult<String>>();

}
