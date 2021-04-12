package com.hmses.demo.repository;

import com.hmses.demo.domain.graph.GraphCredentials;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GraphRepository extends MongoRepository<GraphCredentials, String> {
}
