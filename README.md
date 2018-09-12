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

如需对外提供数据接口API, 按照如下规范。
```java
/**
* 此Controller对外提供Restful的数据接口服务，
* 用@RestController注解修饰类
*/
@RestController
public class Controller {
    
    /**
    * 查询符合条件的用户集合，
    * 查询操作用Http的GET方法。
    */
    @GetMapping("users")
    public Object listUsers() {
        return userList;
    }
    
    /**
    * 查询符合条件的用户，
    * 查询操作用Http的GET方法。
    */
    @GetMapping("user/{userId}")
    public Object getUser(@PathParam Long userId) {
        return getUser(userId);
    }
    
    /**
    * 新增用户，
    * 新增操作用Http的POST方法。
    */
    @PostMapping("user")
    public Object testConfig(UserVo userVo) {
        return addUser(userVo);
    }
    
    /**
    * 修改用户信息，
    * 修改操作用Http的PUT方法。
    */
    @PutMapping("user")
    public Object testConfig(UserVo userVo) {
        return modifiyUser(userVo);
    }
     
    /**
    * 根据用户ID删除一个用户，
    * 删除操作用Http的DELETE方法。
    */
    @DeleteMapping("user/{userId}")
    public Object deleteUser(@PathParam Long userId) {
        return deleteUser(userId);
    }
}
```
## Data Binding

yml配置文件中配置数据
```yaml
#自定义配置文件
user:
  cname: fred1
  age: 221
  info: ${user.cname}已经${user.age}
```
1. 一组相关的配置，用prefix方式直接注入Bean，更少的代码，更简洁。
```java
    @ConfigurationProperties(prefix = "user")
    public class UserConfig {
    
        private String cname;
        private Integer age;
        private String info;
        
    }
```
2. 单个独立配置，用@Value进行注入。
```java
    public class UserConfig {
        
        @Value("com.cname")
        private String name;
    }
```

## Logback
logger的诸如方式采用注解方式

```java
@Service
@Slf4j
public class UserServiceImpl {
   
    /**
     * 日志logger的成员名字是：log，
     * 使用参考下面代码，采用占位符方式。
     */
    public void addUser(UserDto userDto) {
        log.info("this is addUser method, params is :{}", userDto);
    }
}
```