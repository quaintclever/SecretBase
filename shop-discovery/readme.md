## Nacos

### 安装nacos server

**下载地址**

https://github.com/alibaba/nacos/releases

### 启动nacos

解压nacos，进入bin目录

**单机启动**

```bash
./startup.sh -m standalone
```

### 查看服务注册页面

**浏览器请求**

http://localhost:8848/nacos/index.html 

```bash
默认账号密码
账号： nacos
密码： nacos
```

### springboot 整合 nacos

**父工程 gradle配置**
```gradle
ext{
    springCloudVersion = 'Hoxton.SR3'
    springBootVersion = '2.2.5.RELEASE'
    springCloudAlibabaVersion = '2.1.0.RELEASE'
}

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
        mavenBom "org.springframework.boot:spring-boot-dependencies:${springBootVersion}"
        mavenBom "com.alibaba.cloud:spring-cloud-alibaba-dependencies:${springCloudAlibabaVersion}"
        
    }
}
```
**nacos module gradle配置**
```gradle
// spring boot web
compile('org.springframework.boot:spring-boot-starter-web')
// spring cloud nacos
compile("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
```

**nacos module yml 配置**
```yml
server:
  port: 10101

spring:
  application:
    name: nacos
  cloud:
    nacos:
      config:
        server-addr: 127.0.0.1:8848
      discovery:
        server-addr: 127.0.0.1:8848

management:
  endpoints:
    web:
      exposure:
        include: '*'
```

### 参考

https://nacos.io/zh-cn/docs/quick-start.html

https://spring-cloud-alibaba-group.github.io/github-pages/greenwich/spring-cloud-alibaba.html#_spring_cloud_alibaba_nacos_discovery

https://www.jianshu.com/p/ecaf15c54ed7