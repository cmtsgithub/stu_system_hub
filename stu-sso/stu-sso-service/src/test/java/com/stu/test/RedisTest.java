package com.stu.test;

import com.stu.service.AdminLoginService;
import com.stu.utils.JsonResult;
import com.stu.utils.jedis.JedisClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisTest {

    @Test
    public void testRedisClient(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        jedisClient.set("k2", "v2");
        String k2 = jedisClient.get("k2");
        System.out.println(k2);
    }

    @Test
    public void testLogin(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
        AdminLoginService loginService = applicationContext.getBean(AdminLoginService.class);
        JsonResult result = loginService.login("张三", "123456");
        System.out.println(result);
    }
}
