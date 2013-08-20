package com.sandbox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.sandbox.controller", "com.sandbox.service" })
public class MyWebConfig extends WebMvcConfigurerAdapter {
 
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//    }
 
    @Bean
    public ViewResolver viewResolver() {
    	VelocityViewResolver viewResolver = new VelocityViewResolver();
        viewResolver.setViewClass(VelocityView.class);
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".vm");
        return viewResolver;
    }
    
    @Bean
    public VelocityConfigurer velocityConfig(){
    	VelocityConfigurer configurer = new VelocityConfigurer();
    	
    	configurer.setResourceLoaderPath("/WEB-INF/views");
    	
    	return configurer;
    }
    
}