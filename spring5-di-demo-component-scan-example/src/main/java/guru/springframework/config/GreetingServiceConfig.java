package guru.springframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import guru.services.GreetingRepository;
import guru.services.GreetingService;
import guru.services.GreetingServiceFactory;

@Configuration
public class GreetingServiceConfig {

	@Bean
	public GreetingServiceFactory greetingServiceFactory(GreetingRepository repository) {
		return new GreetingServiceFactory(repository);
	}
	
	@Bean
	@Primary
	@Profile({"default","en"})
	public GreetingService primaryGreetingService(GreetingServiceFactory factory) {
		return factory.createGreetingService("en");
	}
	
	@Bean
	@Primary
	@Profile("es")
	public GreetingService primarySpanishGreetingService(GreetingServiceFactory factory) {
		return factory.createGreetingService("es");
	}
	
	@Bean
	@Primary
	@Profile("de")
	public GreetingService primaryGermanGreetingService(GreetingServiceFactory factory) {
		return factory.createGreetingService("de");
	}
	
	
	
}
