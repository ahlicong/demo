package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Greeting;
import com.example.demo.dto.ResultDto;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	@Autowired
	private Dao dao;

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "username", defaultValue = "World") String name) {
		long count = counter.incrementAndGet();
		dao.insertTest(count, name);
		return new Greeting(count, String.format(template, name));
	}

	@PostMapping("/saveResult")
	public void saveResult(@RequestBody ResultDto result) {
		dao.insertResult(result);
	}
}
