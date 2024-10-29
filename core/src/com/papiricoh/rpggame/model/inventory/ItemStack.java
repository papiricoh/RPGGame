package com.papiricoh.rpggame.model.inventory;

import com.papiricoh.rpggame.exceptions.ItemStackEmptyException;
import com.papiricoh.rpggame.exceptions.ItemStackFullException;
import com.papiricoh.rpggame.exceptions.ItemStackIncorrectItemException;

public class ItemStack {
    private static final int MAX_CAPACITY = 64;
    private Item item;
    private int quantity;

    public ItemStack(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    public void increment(Item item) throws ItemStackFullException, ItemStackIncorrectItemException {
        if(quantity + 1 > MAX_CAPACITY) {
            throw new ItemStackFullException("The itemStack of " + item.getLabel() + " is full");
        }
        if(!item.equals(item)) {
            throw new ItemStackIncorrectItemException("The item on the ItemStack its not the same one provided by the parameter");
        }
        quantity++;
    }

    public Item subtract() throws ItemStackEmptyException {
        if(quantity - 1 < 0) {
            throw new ItemStackEmptyException("The ItemStack is empty");
        }
        quantity--;
        return this.item;
    }

    public int getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }
}
