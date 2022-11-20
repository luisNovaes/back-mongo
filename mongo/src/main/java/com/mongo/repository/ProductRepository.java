package com.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongo.model.Product;


public interface ProductRepository extends MongoRepository < Product, Long > {

}
