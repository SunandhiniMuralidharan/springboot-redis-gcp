package com.student.redis.cofiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
    	
    	try {
    	
            RedisStandaloneConfiguration redisStandaloneConfiguration = 
            		new RedisStandaloneConfiguration("10.255.127.195", 6379);
            
            return new JedisConnectionFactory(redisStandaloneConfiguration);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return null;
        
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
