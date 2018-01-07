package com.cc.rabbit.tut.config;

import com.cc.rabbit.tut.receiver.Tut1Receiver;
import com.cc.rabbit.tut.sender.Tut1Sender;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut1", "hello-world"})
@Configuration
public class Tut1Config {

    private final RabbitTemplate template;

    @Autowired
    public Tut1Config(RabbitTemplate template) {
        this.template = template;
    }

    @Bean
    public Queue hello() {
        return new Queue("myqueue");
    }

    @Profile("receiver")
    @Bean
    public Tut1Receiver receiver() {
        return new Tut1Receiver();
    }

    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender(template, hello());
    }

}
