package com.jhon.rain.security.core;

import com.jhon.rain.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>功能描述</br> 使用注解使得核心配置生效 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SecurityCoreConfig
 * @date 2017/10/19 11:22
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
