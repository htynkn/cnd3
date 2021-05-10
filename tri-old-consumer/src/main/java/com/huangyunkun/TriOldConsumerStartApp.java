package com.huangyunkun;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

public class TriOldConsumerStartApp {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
        applicationContext.start();

        while (true) {
            IHelloService helloService = applicationContext.getBean(IHelloService.class);

            System.out.println("Response as: " + helloService.sayHello("world"));

            TimeUnit.SECONDS.sleep(5);
        }
    }
}
