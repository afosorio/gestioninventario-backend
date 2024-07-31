package com.co.flypass.gestioninventario.application;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

public abstract class ConcurrentService<T, ID> {

    private final ReentrantLock lock = new ReentrantLock();
    private final ExecutorService executorService;

    protected ConcurrentService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    protected abstract T findById(ID id);

    protected abstract void save(T entity);

    public CompletableFuture<T> getById(ID id) {
        return CompletableFuture.supplyAsync(() -> {
            lock.lock();
            try {
                return findById(id);
            } finally {
                lock.unlock();
            }
        }, executorService);
    }

    public CompletableFuture<Void> saveEntity(T entity) {
        return CompletableFuture.runAsync(() -> {
            lock.lock();
            try {
                save(entity);
            } finally {
                lock.unlock();
            }
        }, executorService);
    }
}