#### SpringBoot使用Maven打包的时候找不到Main

##### 解决办法是手动配置启动类
```xml
<properties>
    <spring.boot.mainclass>com.jhon.rain.RainbowApplication</spring.boot.mainclass>
</properties>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>1.3.3.RELEASE</version>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                    <configuration>
                        <mainClass>${spring.boot.mainclass}</mainClass>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
    <finalName>rainbow</finalName>
</build>
```