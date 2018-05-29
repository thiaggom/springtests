package guru.springframework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:database.properties")
public class ConfigurationProperties {

	@Value("${user.name}")
	String username;
	
	@Value("${user.password}")
	String password;
	
	@Value("${url}")
	String url;

	@Bean
	public DatabaseConfiguration databaseConfiguration() {
		return new DatabaseConfiguration(username, password, url);
	}
	
}
