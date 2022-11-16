package com.blog.storiesblog;

import com.blog.storiesblog.security.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class StoriesBlogApplication  {

	public static void main(String[] args) {
		SpringApplication.run(StoriesBlogApplication.class, args);
	}

}
