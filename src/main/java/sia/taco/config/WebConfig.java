package sia.taco.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* separate configuration class to work with view controller */
@Configuration
public class WebConfig implements WebMvcConfigurer{

	 @Override
	 public void addViewControllers(ViewControllerRegistry registry) {
	 registry.addViewController("/home").setViewName("home");
	 }

}
