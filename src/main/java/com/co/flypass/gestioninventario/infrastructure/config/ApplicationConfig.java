package com.co.flypass.gestioninventario.infrastructure.config;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

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
    public Executor executorService() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }
}
