package capgemini.gameshop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {
    public void send(String to, String from, String title, String text){

        log.info("Mail to: " + to + ". From: " + from + ". Title: " + title + ". Text: " + text);

    }
}
