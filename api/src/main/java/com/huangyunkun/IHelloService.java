package com.huangyunkun;

import java.util.concurrent.CompletableFuture;

public interface IHelloService {
    public String sayHello(String name);

    public CompletableFuture<String> sayHelloAsync(String name);
}
