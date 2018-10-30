package com;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.CacheControl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass=false)
@EnableJpaRepositories(basePackages="com.repository")
@EntityScan(basePackages="com.dto")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MyStarter implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(MyStarter.class, args);
	}


	@Bean("localeChangeInterceptor")
	public LocaleChangeInterceptor getLocaleChangeInterceptor() {
		LocaleChangeInterceptor interceptor= new LocaleChangeInterceptor();
		interceptor.setParamName("language");
		return interceptor;
	}
	
	@Bean("localeResolver")
	public LocaleResolver getLocaleResolver() {
		SessionLocaleResolver resolver= new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getLocaleChangeInterceptor());
	}
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/")
			.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
			.resourceChain(false)
			.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
		registry.addResourceHandler("/js/**").addResourceLocations("/js/")
			.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
			.resourceChain(false)
			.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
		registry.addResourceHandler("/images/**").addResourceLocations("/images/")
			.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
			.resourceChain(false)
			.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "indexList");
		registry.addRedirectViewController("/index", "indexList");
		registry.addRedirectViewController("/loginPage", "login");
		registry.addRedirectViewController("/registerPage", "register");
	}
}
