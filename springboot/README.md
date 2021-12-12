## TODO
* SpringBoot 集成 Swagger3 
* SpringBoot Actuator 输出应用信息
* Transactional 事务
* Spring Validation 集成
* Flyway 数据迁移案例

Swagger3 访问地址：http://localhost:8081/swagger-ui/index.html

## SpringBoot Actuator 输出应用信息

application.yml

```yaml
# actuator info 信息（应用信息）
info:
  app:
    # 在 maven 项目中你可以直接用下列方式引用 maven properties的值 ref: https://segmentfault.com/a/1190000021611510
    name: "@project.artifactId@"
    version: "@project.version@"
    description: "@project.description@"
    encoding: "@project.build.sourceEncoding@"
    build:
      timestamp: "@maven.build.timestamp@"
    java:
      source: "@java.version@"
      target: "@java.version@"
#  开启所有的 actuator 端点运行 API 访问。
management:
  endpoints:
    web:
      exposure:
        include: "*"
```
pom.xml 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.highzap.jerp.ebs</groupId>
    <artifactId>expense-budget-service</artifactId>
    <version>${revision}</version>
    <description>费用预算管理</description>
    
    <properties>
        <revision>1.0.0-SNAPSHOT</revision>
        <maven.build.timestamp.format>yyyy-MM-dd HH:mm:ss</maven.build.timestamp.format>
    </properties>
   <resources>
     <resource>
       <directory>src/main/resources</directory>
       <filtering>true</filtering>
     </resource>
    </resources>
    <plugins>
      <!--解决 maven.build.timestamp 在 maven 3.2.2 版本之后，取出来的是 UTC 时间，与中国时区相差8小时-->
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>build-helper-maven-plugin</artifactId>
        <executions>
          <execution>
            <id>timestamp-property</id>
            <goals>
              <goal>timestamp-property</goal>
            </goals>
            <configuration>
              <name>maven.build.timestamp</name>
              <timeZone>GMT+8</timeZone>
            </configuration>
          </execution>
        </executions>
      </plugin>
  </plugins>
</project>
```