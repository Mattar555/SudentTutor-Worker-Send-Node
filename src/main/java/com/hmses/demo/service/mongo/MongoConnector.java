package com.hmses.demo.service.mongo;

import com.hmses.demo.domain.User;
import com.hmses.demo.domain.graph.GraphCredentials;
import com.hmses.demo.repository.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoConnector {

    private GraphRepository graphRepository;

    @Autowired
    public MongoConnector(GraphRepository graphRepository) {
        this.graphRepository = graphRepository;
    }

    public GraphCredentials getGraphCredentials() {
        assert graphRepository.count() == 1;
        return graphRepository.findAll().get(0);
    }

    public GraphCredentials configureGraphClient(GraphCredentials graphCredentials) {
        return graphRepository.save(graphCredentials);
    }
}
