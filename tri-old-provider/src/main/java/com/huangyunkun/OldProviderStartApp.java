package com.huangyunkun;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OldProviderStartApp {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("provider.xml");
        applicationContext.start();

        System.in.read();
    }
}
