# Spring Boot 开发模板
[TOC]
## 项目功能&模块列表
* Spring MVC
* Data Binding
* Logback
* Redis
* Druid and Monitor
* Mybatis
* Mybatis Common Mapper
* Mybatis PageHelper
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

## 功能模块集成&使用
### Spring MVC
#### 如何集成Spring MVC?
pom.xml新增spring-boot-starter-web依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

如需对外提供数据接口API, 按照如下规范, 示例如下：
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
一组相关的配置，用prefix方式直接注入Bean，更少的代码，更简洁。
```java
@ConfigurationProperties(prefix = "user")
public class UserConfig {

    private String cname;
    private Integer age;
    private String info;
    
}
```
单个独立配置，用@Value进行注入。
```java
public class UserConfig {
    
    @Value("com.cname")
    private String name;
}
```

### Logback
#### Logback的日志在项目中如何使用？
logger的使用采用注解注入的方式，使用参考如下：

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
pom.xml中添加redis的依赖。
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```
application-xxx.yml中如下配置：
```yaml
spring:
  #Redis配置
  redis:
    host: localhost
    port: 6379
```
最后java类中注入StringRedisTemplate或者RedisTemplate成员，用来对redis进行操作。
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
pom.xml中添加mariadb, 以及druid的依赖。
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
application-xxx.yml中如下配置：
```yaml
spring:
  #数据源配置
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://localhost:33066/test?useUnicode=true&characterEncoding=utf-8
    username: root
    password: root
    #连接池配置
    druid:
      validation-query: SELECT 'x'
      validation-query-timeout: 5000
      initial-size: 1
      max-active: 160
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false
      min-idle: 1
      #配置获取连接等待超时的时间
      max-wait: 60000
      #配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
      time-between-eviction-runs-millis: 60000
      #配置一个连接在池中最小生存的时间，单位是毫秒
      min-evictable-idle-time-millis: 300000
      #配置监控统计拦截的filters
      filters: stat,wall
      #打开PSCache
      pool-prepared-statements: true
      #每个连接上PSCache的大小
      max-pool-prepared-statement-per-connection-size: 20
      web-stat-filter:
        enabled: true
        session-stat-enable: true
        profile-enable: true

```
给spring注入dataSource。
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
### Mybatis & Common Mapper& PageHelper
#### Mybatis如何集成？
pom文件中加入mybatis, Common Mapper, PageHelper的依赖
```xml
<dependencies>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>1.3.1</version>
    </dependency>
</dependencies>
```
application-xxx.yml中如下配置:
```yaml
mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.cloudoer.project.project.module.bean

#mappers 多个接口时逗号隔开
mapper:
  #mappers: tk.mybatis.springboot.util.MyMapper
  not-empty: false
  identity: MYSQL

#pagehelper配置
pagehelper:
  helperDialect: mariadb
  reasonable: true
  pageSizeZero: true
  params: count=countSql
```
mybatis如何配置common mapper进行高效开发？

```java
import com.cloudoer.project.project.module.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
* 编写自己的XxxMapper继承MyMapper, XxxMapper便拥有了很多基础的CRUD API, 例如：
* <ul>
*     <li>selectByPrimaryKey</li>
*     <li>select</li>
*     <li>delete</li>
*     <li>deleteByPrimaryKey</li>
* </ul>
*/
@Mapper
@Component
public interface UserMapper extends MyMapper<User> {
    //···
}
```
如何进行查询分页？
```java
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    
    @Override
    public PageInfo<User> getUsers() {
        List<User> query = userMapper.query();
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(query);
    }
}

```

### Test
#### 如何集成Spring Test Framework？
pom文件中加入spring-boot-starter-test的依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```
在src/test/java得对应得包内编写测试用例，示例如下：
```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testAddUser() {

        UserDto userDto = new UserDto();
        userDto.setName("test add");
        userService.addUser(userDto);
    }
}
```

### Spring Boot Monitor
#### 如何集成Spring Boot Monitor，如何访问？
pom文件中加入spring-boot-starter-actuator的依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
application-xxx.yml中如下配置:
```yaml
#spring-boot监控
management:
  endpoints:
    enabled-by-default: true
    web:
      exposure:
        include: health,info,env,metrics,trace,beans,status,loggers
      base-path: /monitor
  server:
    port: 8081
```
[监控地址: http://localhost:8081/monitor](http://localhost:8081/monitor)

### Thymeleaf
#### 如何集成Thymeleaf？
pom.xml加入Thymeleaf的依赖。
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
然后在src/main/resources目录下
* public 静态页面
* static 静态文件，js，css，png
* template 动态页面，thymeleaf模板文件存放地方

### Global Exception Handler
如何开发自己定制的全局异常处理？
```java
@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        log.error("global handle Exception", e);
        return RespUtil.fail();
    }

    @ExceptionHandler(BizException.class)
    public Object bizExceptionHandler(BizException ex) {
        log.error("runtimeExceptionHandler", ex);
        return RespUtil.fail(EXCEPTION, ex.getMessage());
    }

    //··· 如需更多类型的Exception处理，可在下面自行定义。
}
```
### Quartz & Mail
#### 如何集成Quartz和Mail？
pom.xml加入Quartz的依赖。
```xml
<dependencies>
    <dependency>
        <groupId>org.quartz-scheduler</groupId>
        <artifactId>quartz</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
</dependencies>
```
mail在application-xxx.yml中如下配置：
```yaml
spring:
  #邮件配置
  mail:
    host: smtp.163.com
    username: xxxxx@163.com
    password: xxxxx
    default-encoding: UTF-8
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
            required: true
```

在quartz子包下编写quartz类，示例定时发邮件：
```java
@Component
@Configurable
@EnableScheduling
@Slf4j
public class SendMailQuartz {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Scheduled(cron = "0 0/60 * * * ?")
    public void send() throws MessagingException {
        log.info("定时器开始发邮件了");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setSubject("this is from schedule mail sender");
        mimeMessageHelper.setTo("liuxk@cloudoer.com");
        mimeMessageHelper.setText("mail content information");

        this.javaMailSender.send(mimeMessage);
        log.info("定时器发完邮件了");
    }
}
```
### ActiveMq
#### 如何集成ActiveMq？
pom.xml加入ActiveMq得依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
```
application-xxx.yml中如下配置：
```yaml
spring:
  #activemq配置
  activemq:
    broker-url: tcp://localhost:61616
    in-memory: true
    packages:
      trust-all: true
```
配置JmsListenerContainerFactory：
```java
@Configuration
public class ActiveMqConfig {

    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("3");
        return factory;
    }
}
```
如何在项目中发送queue或者topic消息:
```java
public class SendMq {

    public void produce() {

        //send queue message.
        Destination queue = new ActiveMQQueue("test-queue");
        jmsMessagingTemplate.convertAndSend(queue, "this is a queue message!");

        //send topic message.
        Destination topic = new ActiveMQTopic("test-topic");
        jmsMessagingTemplate.convertAndSend(topic, "this is a topic message!");
    }
}
```
项目中如何消费消息?
```java
@Component
@Slf4j
public class SimpleConsumer {

    @JmsListener(destination = "test-queue", containerFactory = "queueListenerFactory")
    public void consumeSimpleQueue(String text) {
        log.info("消费了一条消息：{}", text);
    }

    @JmsListener(destination = "test-topic", containerFactory = "topicListenerFactory")
    public void consumeSimpleTopic(String text) {
        log.info("topic1消费了一条消息：{}", text);
    }

    @JmsListener(destination = "test-topic", containerFactory = "topicListenerFactory")
    public void consumeSimpleTopic2(TextMessage text) throws JMSException {
        log.info("topic2消费了一条消息：{}", text.getText());
    }
}
```
### Common Response Data Style
#### 数据接口API，统一返回格式工具类
```java
package com.cloudoer.project.project.module.utils;

/**
 * 用于Restful webService API数据接口统一返回。
 *
 * @author liuxiaokun
 * @version 0.0.1
 * @see com.cloudoer.project.project.module.consts.RespCode 返回码常量
 * @see com.cloudoer.project.project.module.consts.RespMsg 返回信息常量
 * @since 2018/9/6
 */
@AllArgsConstructor
@Data
public class RespUtil {

    /**
     * 返回码常量
     *
     * @see com.cloudoer.project.project.module.consts.RespCode 返回码常量
     */
    private int code;

    /**
     * 返回信息常量
     *
     * @see com.cloudoer.project.project.module.consts.RespMsg 返回信息常量
     */
    private String message;

    /**
     * 返回数据 Vo, 可以为任意数据类型
     */
    private Object data;

    /**
     * 数据接口访问 <b>成功</b> 的统一返回格式
     * <ul>
     * <li>code为 <code>SUCCESS</code> </li>
     * <li>message为 <code>OPERATION_SUCCESS<code/> </li>
     * <li>返回空数据 <code>EMPTY</code> </li>
     * </ul>
     *
     * @return 返回实体
     */
    public static Object succeed() {
        return new RespUtil(SUCCESS, OPERATION_SUCCESS, EMPTY);
    }

    /**
     * 数据接口访问 <b>成功</b> 的统一返回格式
     * <ul>
     * <li>code为 <code>SUCCESS</code></li>
     * <li>返回空数据 <code>EMPTY</code></li>
     * </ul>
     *
     * @param message 返回提示信息
     * @return 返回实体
     */
    public static Object succeed(String message) {
        return new RespUtil(SUCCESS, message, EMPTY);
    }

    /**
     * 数据接口访问 <b>成功</b> 的统一返回格式
     * <ul>
     * <li>code为 <code>SUCCESS</code> </li>
     * <li>message为 <code>OPERATION_SUCCESS<code/> </li>
     * </ul>
     *
     * @param data 返回数据
     * @return 返回实体
     */
    public static Object succeed(Object data) {
        return new RespUtil(SUCCESS, OPERATION_SUCCESS, data);
    }

    /**
     * 数据接口访问 <b>成功</b> 的统一返回格式
     * <ul>
     * <li>code为 <code>SUCCESS</code> </li>
     * </ul>
     *
     * @param message 返回提示信息
     * @param data    返回数据
     * @return 返回实体
     */
    public static Object succeed(String message, Object data) {
        return new RespUtil(SUCCESS, message, data);
    }

    /**
     * 数据接口访问 <b>成功</b> 的统一返回格式
     *
     * @param code    返回码
     * @param message 返回提示信息
     * @param data    返回数据
     * @return 返回实体
     */
    public static Object succeed(int code, String message, Object data) {
        return new RespUtil(code, message, data);
    }

    /**
     * 数据接口访问 <b>失败</b> 的统一返回格式
     * <ul>
     * <li>code为 <code>FAILURE</code></li>
     * <li>message为 <code>SERVER_INTERNAL_ERROR </code></li>
     * <li>返回空数据</li>
     * </ul>
     *
     * @return 返回实体
     */
    public static Object fail() {
        return new RespUtil(FAILURE, SERVER_INTERNAL_ERROR, EMPTY);
    }

    /**
     * 数据接口访问 <b>失败</b> 的统一返回格式
     * <ul>
     * <li>message为 <code>SERVER_INTERNAL_ERROR </code></li>
     * <li>返回空数据</li>
     * </ul>
     *
     * @param code 返回码
     * @return 返回实体
     */
    public static Object fail(int code) {
        return new RespUtil(code, OPERATION_FAILURE, EMPTY);
    }

    /**
     * 数据接口访问 <b>失败</b> 的统一返回格式
     * <ul>
     * <li>返回空数据</li>
     * </ul>
     *
     * @param code    返回码
     * @param message 返回提示信息
     * @return 返回实体
     */
    public static Object fail(int code, String message) {
        return new RespUtil(code, message, EMPTY);
    }

    /**
     * 数据接口访问 <b>失败</b> 的统一返回格式
     *
     * @param code    返回码
     * @param message 返回提示信息
     * @param data    返回数据
     * @return 返回实体
     */
    public static Object fail(int code, String message, Object data) {
        return new RespUtil(code, message, data);
    }

}

```
统一的错误码常量。
```java
/**
 * <p>对响应码做如下约定</p>
 * <ul>
 * <li>100XX 为内置基本响应码</li>
 * <li>200XX 为内置预留响应码</li>
 * <li>300XX 为内置预留响应码</li>
 * <li>400XX 为内置预留响应码</li>
 * <li>500XX 为内置预留响应码</li>
 * <li>600XX 为自定义响应码
 * </ul>
 *
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/6
 */
public interface RespCode {

    int SUCCESS = 10000;

    int NOT_SIGN_IN = 40000;
    int FORBIDDEN = 40003;
    int NOT_FOUND = 40004;

    int FAILURE = 50000;
    int EXCEPTION = 50001;
    //···
}
```
统一的错误提示信息常量。
```java
public interface RespMsg {

    String OPERATION_SUCCESS = "操作成功";
    String OPERATION_FAILURE = "操作失败";
    String SERVER_INTERNAL_ERROR = "服务器内部错误";
    String PLEASE_LOGIN_FIRST = "请先登录";
    //···
}
```
### Async
#### 如何集成Spring Async？
配置async executor
```java
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {

        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(5);
        threadPoolExecutor.setMaxPoolSize(50);
        threadPoolExecutor.setQueueCapacity(100);

        threadPoolExecutor.initialize();

        return threadPoolExecutor;
    }
}
```
然后开启Async。
```java
@SpringBootApplication
@EnableAsync
public class ProjectModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectModuleApplication.class, args);
    }
}
```
### Spring Security
#### 如何集成Spring Security？
pom.xml中加入Spring Security的依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
配置WebSecurityConfigurerAdapter，并用@EnableWebSecurity开启。
```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    private static final String roleQuery =
            "select u.name, r.role_name from user u inner join user_role ur on ur.user_id = u.id inner join role r on r.id=ur.role_id" +
                    " where u.name = ?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        /*auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).
                withUser("fred").password(passwordEncoder.encode("fred"))
                .roles("ADMIN","USER");*/

        auth.jdbcAuthentication().passwordEncoder(passwordEncoder)
                .dataSource(dataSource).usersByUsernameQuery("select name, password, enable from user where name = ?")
                .authoritiesByUsernameQuery(roleQuery);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().anyRequest().authenticated()
                .and().formLogin().and().httpBasic();
    }
}
```
## 项目结构
![](.README_images\project-dir-structure.png)
package目录说明
* bean
* config       配置文件
* consts       常量
* controller   控制器
* dao          数据访问层
* dto          数据传输对象
* exception    自定义异常和异常处理
* mq           消息队列
* quartz       定时任务
* security     安全框架相关
* service      业务接口
* service.impl 业务接口实现
* util         通用工具类
* vo           Value Object
