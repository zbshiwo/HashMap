package com.HashMap;

import java.util.NoSuchElementException;

public abstract class AbstractQueue<E> extends AbstractCollection<E> implements Queue<E> {
    protected AbstractQueue() {}

    @Override
    public boolean add(E e) {
        if (offer(e)) {
            return true;
        } else {
            throw new IllegalStateException("Queue full");
        }
    }

    @Override
    public abstract boolean offer(E e);


    @Override
    public E remove() {
        E e = poll();
        if (e == null) {
            throw new NoSuchElementException();
        } else {
            return e;
        }
    }

    @Override
    public abstract E poll();

    @Override
    public E element() {
        E e = peek();
        if (e != null) {
            return e;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public abstract E peek();

    @Override
    public void clear() {
        while (poll() != null);
    }
}
