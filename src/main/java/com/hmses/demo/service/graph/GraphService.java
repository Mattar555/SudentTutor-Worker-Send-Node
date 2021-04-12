package com.hmses.demo.service.graph;


import com.hmses.demo.domain.graph.*;
import com.hmses.demo.service.graph.enumerations.MessageReadEnums;
import com.hmses.demo.service.graph.enumerations.MessageSendEnums;
import com.hmses.demo.service.mongo.MongoConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Service
public class GraphService {

    @Autowired
    private MongoConnector mongoConnector;

    private OutlookService outlookService;

    public void configure(GraphCredentials graphCredentials) {
        mongoConnector.configureGraphClient(graphCredentials);
    }

    public void authenticateAndAuthorize(String code, String idToken) {
        IdToken parsedIdToken = IdToken.parseEncodedToken(idToken);
        GraphCredentials graphCredentials = mongoConnector.getGraphCredentials();
        TokenResponse tokenResponse = AuthorizationHelper.getTokenFromAuthCode(
                code,
                graphCredentials
        );
        outlookService = OutlookServiceBuilder.getOutlookService(tokenResponse.getAccessToken());
        graphCredentials.setTokenResponse(tokenResponse);
        configure(graphCredentials);
    }

    public void readEmails() throws IOException {
        refreshIfNecessary();
        PagedResult<Message> messages = outlookService.getMessages(
                MessageReadEnums.FOLDER.getValue(),
                MessageReadEnums.SORT.getValue(),
                MessageReadEnums.PROPERTIES.getValue(),
                Integer.valueOf(MessageReadEnums.MAX_RESULTS.getValue()))
                .execute().body();
        //TODO: Parse results and update database with confirmed tutors
    }

    public void sendMail(String emailAddress) throws IOException {
       refreshIfNecessary();
       Message message = formulateMessage(emailAddress);
       outlookService.sendMessage(message).execute();
    }

    private void refreshIfNecessary() {
        GraphCredentials graphCredentials = mongoConnector.getGraphCredentials();
        Calendar now = Calendar.getInstance();
        if (now.getTime().after(graphCredentials.getTokenResponse().getExpirationTime())) {
            TokenResponse tokenResponse = AuthorizationHelper.refreshToken(graphCredentials);
            graphCredentials.setTokenResponse(tokenResponse);
            configure(graphCredentials);
            outlookService = OutlookServiceBuilder.getOutlookService(tokenResponse.getAccessToken());
        }
    }

    private Message formulateMessage(String emailAddress) {
        Message message = new Message();
        message.setSubject(MessageSendEnums.SUBJECT.getValue());
        List<Recipient> recipientList = new LinkedList<>();
        Recipient recipient = new Recipient();
        EmailAddress address = new EmailAddress();
        address.setAddress(emailAddress);
        recipient.setEmailAddress(address);
        recipientList.add(recipient);
        message.setRecipients(recipientList);
        Body body = new Body();
        body.setContent(MessageSendEnums.BODY.getValue());
        body.setContentType(MessageSendEnums.BODY_CONTENT.getDescription());
        message.setBody(body);
        return message;
    }

}
