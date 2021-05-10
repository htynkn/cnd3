package com.huangyunkun;

import java.util.concurrent.CompletableFuture;

public class HelloServiceProvider implements IHelloService {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }

    @Override
    public CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture("hello " + name);
    }
}
