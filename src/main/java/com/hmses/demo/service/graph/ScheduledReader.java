package com.hmses.demo.service.graph;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScheduledReader {

    @Autowired
    private GraphService graphService;

    @Scheduled(fixedRate = 3600000)
    public void reportCurrentTime() throws IOException {
        graphService.readEmails();
    }
}
