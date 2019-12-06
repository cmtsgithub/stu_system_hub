package com.stu.service.impl;

import com.stu.mapper.StuAdminMapper;
import com.stu.pojo.StuAdmin;
import com.stu.service.AdminLoginService;
import com.stu.utils.JsonResult;
import com.stu.utils.JsonUtils;
import com.stu.utils.jedis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private StuAdminMapper stuAdminMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${SESSION_PRE}")
    private String SESSION_PRE;

    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;


    /**
     * 校验管理员账号密码 登陆方法
     * @param name 用户名
     * @param password 密码
     * @return JsonResult
     */
    @Override
    public JsonResult login(String name, String password) {
        //查询用户名是否存在
        StuAdmin stuAdmin = stuAdminMapper.selectByName(name);
        if(stuAdmin == null){
            return JsonResult.build(400, "用户不存在");
        }
        //判断密码是否正确
        if(!stuAdmin.getPassword().equals(password)) {
            return JsonResult.build(400, "用户密码错误");
        }
        //生成一个token
        String token = UUID.randomUUID().toString();
        //把用户信息保存到redis中
        //redis的key就是token，value是Admin对象转换成的json字符串
        //为了安全，就不要把密码保存到Redis数据库里面去，因为这样太危险了，因此我们先把密码置空
        stuAdmin.setPassword(null);
        String key = SESSION_PRE + ":" + token;
        String value = JsonUtils.objectToJson(stuAdmin);
        jedisClient.set(key, value);
        //设置key过期时间
        jedisClient.expire(key, SESSION_EXPIRE);
        //返回token
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return JsonResult.ok(map);
    }

    /**
     * 通过json获取用户信息
     * @param token token
     * @return JsonResult
     */
    @Override
    public JsonResult getUserByToken(String token) {
        //拼接key
        String key = SESSION_PRE + ":" + token;
        //查询redis
        String json = jedisClient.get(key);
        if(StringUtils.isEmpty(json)){
            return JsonResult.build(400, "用户登陆已过期");
        }
        return JsonResult.ok(JsonUtils.jsonToPojo(json, StuAdmin.class));
    }

    /**
     * 用户登出
     * @param token token
     * @return JsonResult
     */
    @Override
    public JsonResult logout(String token) {
        //拼接key
        String key = SESSION_PRE + ":" + token;
        //查询token是否存在
        Boolean exists = jedisClient.exists(key);
        if(!exists){
            return JsonResult.build(400, "用户登陆已过期");
        }
        //设置删除此token的key
        jedisClient.del(key);
        //返回
        return JsonResult.ok();
    }


}
