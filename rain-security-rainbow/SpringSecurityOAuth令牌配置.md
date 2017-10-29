#### SpringSecurityOAuth令牌配置

* 令牌的配置
![OAuth_Token_Custom_Config01](../photos/1029/OAuth_Token_Custom_Config01.png)

* 配置支持多个应用
![OAuth_Token_Custom_Config_Mutiple_Application](../photos/1029/OAuth_Token_Custom_Config_Mutiple_Application.png)

* 多个应用支持可配置

    ![OAuth2YmlConfiguration](../photos/1029/OAuth2YmlConfiguration.png)

    ![OAuth2ClientPropertiesConfig](../photos/1029/OAuth2ClientPropertiesConfig.png)


* 应用1：
    ![Application1PropertiesConfig](../photos/1029/Application1PropertiesConfig.png)

    ![Application1RequestResult](../photos/1029/Application1RequestResult.png)

* 应用2：
    ![Application2PropertiesConfig](../photos/1029/Application2PropertiesConfig.png)

    ![Application2RequestResult](../photos/1029/Application2RequestResult.png)

* 代码实现：
    ![CustomerConfigImplementation](../photos/1029/CustomerConfigImplementation.png)

    ```java
    package com.jhon.rain.security.app;
    
    import com.jhon.rain.security.core.properties.SecurityProperties;
    import com.jhon.rain.security.core.properties.oauth2.OAuth2ClientProperties;
    import org.apache.commons.lang.ArrayUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
    import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
    import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
    import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
    import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
    
    /**
     * <p>功能描述</br> 认证服务器配置 </p>
     *
     * @author jiangy19
     * @version v1.0
     * @FileName RainAuthorizationServerConfig
     * @date 2017/10/28 13:41
     */
    @Configuration
    @EnableAuthorizationServer
    public class RainAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    
        @Autowired
        private AuthenticationManager authenticationManager;
    
        @Autowired
        private UserDetailsService userDetailsService;
    
        @Autowired
        private SecurityProperties securityProperties;
    
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authenticationManager)
                            .userDetailsService(userDetailsService);
        }
    
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
            if (ArrayUtils.isNotEmpty(securityProperties.getOauth().getClients())){
                for (OAuth2ClientProperties config : securityProperties.getOauth().getClients()) {
                    builder.withClient(config.getClientId())
                            .secret(config.getClientSecret())
                            .accessTokenValiditySeconds(config.getAccessTokenValiditySeconds())
                            .authorizedGrantTypes("password","refresh_token")
                            .scopes("all","read","write");
                }
            }
        }
    }
    ```

* 问题：
    ![TokenProcessProblem](../photos/1029/TokenProcessProblem.png)

* 解决办法：持久化token
    ![RedisTokenStore](../photos/1029/RedisTokenStore.png)

    ![ServerConfigProcess](../photos/1029/ServerConfigProcess.png)

* Redis中查看：
    ![RedisStoreAuthenticationTokenInfo](../photos/1029/RedisStoreAuthenticationTokenInfo.png)

这样就解决了，服务重启之后Token失效的问题了。

