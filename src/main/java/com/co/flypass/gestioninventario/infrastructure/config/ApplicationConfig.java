package com.co.flypass.gestioninventario.infrastructure.config;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ApplicationConfig {

    @Bean
    public Scheduler ioScheduler() {
        return Schedulers.io(); // Scheduler para operaciones de I/O
    }

    @Bean
    public Scheduler newThreadScheduler() {
        return Schedulers.newThread(); // Scheduler para crear nuevos hilos
    }

    @Bean
    public Scheduler computationScheduler() {
        return Schedulers.computation(); // Scheduler para operaciones computacionales
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(10); // Ajusta el tamaño del pool según tus necesidades
    }
}
