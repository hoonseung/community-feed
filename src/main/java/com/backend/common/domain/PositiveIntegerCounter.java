package com.backend.common.domain;

public class PositiveIntegerCounter {

    private int count;

    public PositiveIntegerCounter() {
        this.count = 0;
    }

    public void increment() {
        this.count++;
    }

    public void decrement() {
        if (this.count <= 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }
        this.count--;
    }

    public int getCount() {
        return count;
    }
}
