# Spring Boot 开发模板

## 项目功能模块列表
* Spring MVC
* Data Binding
* Logback
* Redis
* Druid and Monitor
* Mybatis
* Mybatis Common Mapper
* PageHelper
* Test
* Spring Boot Monitor
* Thymeleaf
* Global Exception Handler
* Quartz
* Mail
* ActiveMq
* Common Response Data Style
* Async
* Spring Security

## 功能模块集成，使用
### Spring MVC
#### 如何集成Spring MVC?
1. pom.xml新增spring-boot-starter-web依赖
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    ```

2. 如需对外提供数据接口API, 按照如下规范, 示例如下：
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
### Data Binding

#### 如何使用配置数据绑定到对象？
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

### Logback
#### Logback的日志在项目中如何使用？
1. logger的使用采用注解注入的方式，使用参考如下：

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

### Redis
#### 如何集成Redis
1. pom.xml中添加redis的依赖。
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    ```
2. 注入StringRedisTemplate或者RedisTemplate成员，用来对redis进行操作。
```java
    public class UserServiceImpl implements UserService {
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;
    
    //···
    }
```
### Druid and Monitor
#### 如何集成Druid?
1. pom.xml中添加mariadb, 以及druid的依赖。
    ```xml
    <dependencies>
        <dependency>
            <groupId>org.mariadb.jdbc</groupId>
            <artifactId>mariadb-java-client</artifactId>
            <version>2.2.6</version>
        </dependency>
         
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
    </dependencies>
    ```
2. 给spring注入dataSource。
    ```java
    
    @Configuration
    public class DataSourceConfig {
 
     @Bean
     public DataSource dataSource(Environment env) {
 
         DruidDataSource druidDataSource = new DruidDataSource();
         druidDataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
         druidDataSource.setUrl(env.getProperty("spring.datasource.url"));
         druidDataSource.setUsername(env.getProperty("spring.datasource.username"));
         druidDataSource.setPassword(env.getProperty("spring.datasource.password"));
         druidDataSource.setInitialSize(2);
         druidDataSource.setMaxActive(160);
         druidDataSource.setMinIdle(0);
         druidDataSource.setMaxWait(60000);
 
         return druidDataSource;
        }
    }
    ```
### Mybatis
#### Mybatis如何集成？
1. pom文件中加入mybatis的依赖

    ```xml
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>1.3.1</version>
    </dependency>
    ```
2. mybatis 如何使用？