#### Spring Security 验证码功能重构

##### 1.验证码基本参数可配置
1.1 默认配置 (配置在core模块)

![Demo01](../photos/SecurityPropertiesLevelDemo01.png)

![Default Config Properties](../photos/Default-Config-Properties.png)

![Default](../photos/Default-level.png)

1.2 应用级配置 (配置在rainbow模块)

![Demo02](../photos/SecurityPropertiesLevelDemo02.png)

![Application Level](../photos/Application-config-Result.png)

1.3 请求级配置 (配置值在调用接口时传递)
![Demo03](../photos/SecurityPropertiesLevelDemo03.png)

![Interface Level](../photos/IntefaceLevel-Result.png)

1.4 Properties配置层级关系
![Security Application YML](../photos/SecurityApplicationYML.png)

![Security Customer Properties Level Struct](../photos/SecurityCustomerPropertiesLeveStruct.png)

##### 2.验证码拦截接口可配置

##### 3.验证码的生成逻辑可配置