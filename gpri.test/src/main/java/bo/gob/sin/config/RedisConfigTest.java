package bo.gob.sin.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import bo.gob.sin.str.caut.security.config.RedisConfig;

@EnableCaching
@Configuration
@AutoConfigureAfter(RedisConfig.class)
@EnableRedisRepositories
public class RedisConfigTest {
	@Bean
	public RedisProperties properties() {
		return new RedisProperties();
	}

	@Bean
	public LettuceConnectionFactory redisConnectionFactory(RedisProperties redisProperties) {
		return new LettuceConnectionFactory(properties().getHost(), properties().getPort());
	}

}
