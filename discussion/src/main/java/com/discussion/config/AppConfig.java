package com.discussion.config;

import static com.discussion.constants.Constants.DB_DRIVER;
import static com.discussion.constants.Constants.DB_PASSWORD;
import static com.discussion.constants.Constants.DB_URL;
import static com.discussion.constants.Constants.DB_USERNAME;

import java.time.Duration;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableJpaAuditing
public class AppConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        String driverClassname = System.getenv(DB_DRIVER);
        String url = System.getenv(DB_URL);
        String username = System.getenv(DB_USERNAME);
        String password = System.getenv(DB_PASSWORD);
        log.info(driverClassname);
        log.info(url);
        log.info(username);
        log.info(password);

        dataSourceBuilder.driverClassName(driverClassname);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto","update");
        em.setJpaProperties(properties);
        em.setPackagesToScan("com.discussion.model");
        em.setPersistenceUnitName("DiscussionPersistenceUnit");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return  new ObjectMapper()
        		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    public RedisCacheConfiguration cacheConfiguration() {
    	return RedisCacheConfiguration.defaultCacheConfig()
    			.entryTtl(Duration.ofSeconds(10))
    			.disableCachingNullValues()
    			.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));    	
    }
    
    
    @Bean
    public RedisTemplate<String, ResponseEntity<ObjectNode>> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    	final RedisTemplate<String, ResponseEntity<ObjectNode>> redisTemplate = new RedisTemplate<>();
    	redisTemplate.setConnectionFactory(redisConnectionFactory);
    	redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    	return redisTemplate;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
}
