package com.papiricoh.rpggame.exceptions;

public class ItemStackEmptyException extends Exception {
    public ItemStackEmptyException(String theItemStackIsEmpty) {
        super(theItemStackIsEmpty);
    }
}
