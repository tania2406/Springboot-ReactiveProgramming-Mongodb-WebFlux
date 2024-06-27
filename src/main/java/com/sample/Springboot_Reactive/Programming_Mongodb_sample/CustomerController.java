package com.sample.Springboot_Reactive.Programming_Mongodb_sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository crepo;
	@RequestMapping("/test")
	public String test()
	{
		return "this is test for reactive programming with mongodb";
	}
	@PostMapping("/save")
	public Mono<Customer> save(@RequestBody Customer cmp)
	{
		crepo.save(cmp).subscribe(); // must subscribe otherwise will not work
		Mono<Customer> mono=Mono.just(cmp);
		return mono;
	}
	@GetMapping(value="/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<Customer> alldata()
	{
		return crepo.findAll();
	}
	@GetMapping(value="/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Mono<Customer> byid(@PathVariable int id)
	{
		return crepo.findById(id);
	}
	@GetMapping(value="/name/{name}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<Customer> byname(@PathVariable String name)
	{
		return crepo.findByName(name);
	}
	@DeleteMapping("/del/{id}")
	public Mono<String> del(@PathVariable int id)
	{
		crepo.deleteById(id).subscribe(); // must subscribe otherwises not work
		Mono<String> mono=Mono.just("data deleted");	
		return mono;
	}
	@GetMapping("/city/{city}")
	public Flux<Customer> bycity(@PathVariable String city)
	{
		 return crepo.findByCity(city);	
		
	}
	}

