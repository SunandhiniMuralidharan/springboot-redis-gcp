package com.student.redis.cofiguration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfiguration {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
    	
    	RedisStandaloneConfiguration redisStandaloneConfiguration = 
            		new RedisStandaloneConfiguration("10.29.247.147", 6379);
            
            JedisConnectionFactory jedis = new JedisConnectionFactory(redisStandaloneConfiguration);
            jedis.getPoolConfig().setMaxIdle(30);
            jedis.getPoolConfig().setMinIdle(10);
            //jedis.setTimeout(2000);
            
            return jedis;
        
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new JdkSerializationRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }
}
