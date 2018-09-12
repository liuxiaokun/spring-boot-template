# Spring Boot 开发模板

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
#### 如何继承ActiveMq？
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
        jmsMessagingTemplate.convertAndSend(queue, "hello.mq, queue, message!" + i);

        //send topic message.
        Destination topic = new ActiveMQTopic("test-topic");
        jmsMessagingTemplate.convertAndSend(topic, "hello, mq ,topic" + i);
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


