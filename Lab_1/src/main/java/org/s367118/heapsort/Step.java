package org.s367118.heapsort;

import lombok.Getter;

import java.util.Arrays;

public class Step<T> {
    public Step(T[] history, String message){
        if (history != null)
            this.history = history;
        this.message = message;
    }
    @Getter
    T[] history;

    @Getter
    String message;

    @Override
    public String toString() {
        return (history == null ? "null" : Arrays.toString(history))  + (message == null ? "" :  " : " + this.getMessage());
    }
}
