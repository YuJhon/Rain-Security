package com.jhon.rain.security.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * <p>功能描述</br> Session过期策略 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainExpiredSessionStrategy
 * @date 2017/10/26 17:13
 */
public class RainExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

	/**
	 * <pre>构造函数</pre>
	 *
	 * @param invalidSessionUrl Session失效重定向的url
	 */
	public RainExpiredSessionStrategy(String invalidSessionUrl) {
		super(invalidSessionUrl);
	}

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent eventØ) throws IOException, ServletException {
		onSessionInvalid(eventØ.getRequest(),eventØ.getResponse());
	}

	@Override
	protected boolean isConcurrency() {
		return true;
	}
}
