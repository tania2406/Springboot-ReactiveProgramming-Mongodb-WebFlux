package com.sample.Springboot_Reactive.Programming_Mongodb_sample;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveCrudRepository<Customer,Integer>{

	Flux<Customer> findByName(String name);

	Flux<Customer> findByCity(String city);

}
