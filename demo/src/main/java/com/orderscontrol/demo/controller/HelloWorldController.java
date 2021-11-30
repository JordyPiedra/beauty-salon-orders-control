package com.orderscontrol.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.orderscontrol.demo.entity.HelloWorldBean;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	// RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(value="/hello-world", headers="X-API-VERSION=2")
	public String helloWorldV2() {
		return "Hello World Version2";
	}
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}

	@GetMapping("/hello-world-bean/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean(name);
	}

	@GetMapping("/hello-world-internationalized")
	public String helloWorldInternationalized(
			//@RequestHeader(name = "Accept-Language", required = false) Locale locale
			) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}
}
