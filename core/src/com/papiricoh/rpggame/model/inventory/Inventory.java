package com.papiricoh.rpggame.model.inventory;

public interface Inventory {
    void addItem(int index, ItemStack stack);
    ItemStack getItem(int index);
    ItemStack removeItem(int index);
    int getSize();
}
