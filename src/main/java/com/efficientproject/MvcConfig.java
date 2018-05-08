package com.efficientproject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = { "com.efficientproject.web" })
public class MvcConfig implements WebMvcConfigurer {

	public MvcConfig() {
		super();
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
//		addViewControllers(registry);
		registry.addViewController("/").setViewName("redirect:/login");
		registry.addViewController("/login").setViewName("index.html");
		registry.addViewController("/logout").setViewName("redirect:/login");
		/*
		 * registry.addViewController("/registration.html");
		 * registry.addViewController("/homepage.html");
		 * registry.addViewController("/emailError.html");
		 * registry.addViewController("/home.html");
		 * registry.addViewController("/invalidSession.html");
		 * registry.addViewController("/admin.html");
		 * registry.addViewController("/users.html");
		 */
	}
}
