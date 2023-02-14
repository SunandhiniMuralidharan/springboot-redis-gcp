package com.student.redis.cofiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String REDIS_HOST;

    @Value("${spring.redis.port}")
    private int REDIS_PORT;

    @Value("${spring.redis.database}")
    private int REDIS_DATABASE;
    
    @Value("${spring.redis.timeout}")
    private int REDIS_TIMEOUT;
    
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
    	
    	RedisStandaloneConfiguration redisStandaloneConfiguration = 
            		new RedisStandaloneConfiguration(REDIS_HOST, REDIS_PORT);
    	
    	redisStandaloneConfiguration.setDatabase(REDIS_DATABASE);
            
            JedisConnectionFactory jedis = new JedisConnectionFactory(redisStandaloneConfiguration);
            jedis.getPoolConfig().setMaxIdle(30);
            jedis.getPoolConfig().setMinIdle(10);
            jedis.setTimeout(REDIS_TIMEOUT);
            
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