package com.huangyunkun;

import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class TriConsumerStartApp {

    private static final Logger log = LoggerFactory.getLogger(TriConsumerStartApp.class);

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
        applicationContext.start();

        while (true) {
            IHelloService helloService = applicationContext.getBean(IHelloService.class);

            log.info("Response as: " + helloService.sayHello("world"));

            helloService.sayHelloAsync("async world").thenAccept(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    log.info("Response as: " + s);
                }
            });

            TimeUnit.SECONDS.sleep(5);
        }
    }
}
