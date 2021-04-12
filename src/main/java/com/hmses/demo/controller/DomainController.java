package com.hmses.demo.controller;

import com.hmses.demo.domain.graph.*;
import com.hmses.demo.service.graph.GraphService;
import com.hmses.demo.service.mongo.MongoConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/graph")
public class DomainController {

    @Autowired
    private GraphService graphService;


    @PostMapping(path = "/authorize")
    public void authorize(@RequestParam("code") String code,
                          @RequestParam("id_token") String idToken,
                          @RequestParam("state") String state,
                          HttpServletRequest request) {
        graphService.authenticateAndAuthorize(code, idToken);
    }


    @PostMapping(path = "/configure")
    public void configure(@RequestBody GraphCredentials graphCredentials) {
        graphService.configure(graphCredentials);
    }

}
