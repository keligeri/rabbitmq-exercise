package com.cc.rabbit.tut2.sender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;


public class Tut2Sender {

    @Autowired
    private RabbitTemplate template;
    @Autowired
    private Queue queue;

    int dots = 0;
    int count = 0;

    @Scheduled
    public void send() {
        StringBuilder builder = new StringBuilder();
        if (dots++ == 3) {
            dots = 1;
        }

        // IntStream.range(0, dots).forEach(i -> builder.append('.'));
        for (int i = 0; i < dots; i++) {
            builder.append('.');
        }

        builder.append(Integer.toString(++count));
        String message = builder.toString();

        template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }

}