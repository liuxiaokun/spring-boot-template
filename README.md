# Spring Boot 开发模板

* Spring MVC
* Data Binding
* Logback
* Redis
* Druid and Monitor
* Mybatis
* Test
* PageHelper
* Mybatis Common Mapper
* Spring Boot Monitor
* Thymeleaf
* Global Exception Handler
* Quartz
* Mail
* ActiveMq
* Common Response Data Style
* Async
* Spring Security

## Spring MVC
pom.xml新增spring-boot-starter-web依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

如需提供数据接口, 按照如下规范。
```java
/**
* 此Controller对外提供Restful的数据接口服务，
* 用@RestController修饰类
*/
@RestController
public class Controller {
    
    /**
    * 查询符合条件的用户集合
    * 查询操作用Http的GET方法
    */
    @GetMapping("users")
    public Object listUsers() {
        return userList;
    }
    
    /**
    * 查询符合条件的用户
    * 查询操作用Http的GET方法
    */
    @GetMapping("user/{userId}")
    public Object getUser(@PathParam Long userId) {
        return getUser(userId);
    }
    
    /**
    * 新增用户
    * 新增操作用Http的POST方法
    */
    @PostMapping("user")
    public Object testConfig(UserVo userVo) {
        return addUser(userVo);
    }
    
    /**
    * 修改用户信息
    * 修改操作用Http的PUT方法
    */
    @PutMapping("user")
    public Object testConfig(UserVo userVo) {
        return modifiyUser(userVo);
    }
     
    /**
    * 根据用户ID删除一个用户。
    * 删除操作用Http的DELETE方法
    */
    @DeleteMapping("user/{userId}")
    public Object deleteUser(@PathParam Long userId) {
        return deleteUser(userId);
    }
}
```
##Data Binding

```yaml
#自定义配置文件
user:
  cname: fred1
  age: 221
  info: ${user.cname}已经${user.age}
```
```java
@ConfigurationProperties(prefix = "user")
@Component
public class UserConfig {
    private String cname;
    private Integer age;
    private String info;
}
```
```java

public class UserConfig {
    @Value("com.cname")
    private String name;
}
```

##Logback