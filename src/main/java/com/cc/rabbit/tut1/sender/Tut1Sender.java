package com.cc.rabbit.tut1.sender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut1Sender {

    private final RabbitTemplate template;
    private final Queue queue;

    @Autowired
    public Tut1Sender(RabbitTemplate template, Queue queue) {
        this.template = template;
        this.queue = queue;
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        String msg = "Hello World!";
        template.convertAndSend(queue.getName(), msg);
        System.out.println(" [x] Sent '" + msg + "'");
    }


}
