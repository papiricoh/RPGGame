package com.papiricoh.rpggame.model.inventory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractInventory implements Inventory {
    private static final int DEFAULT_SIZE = 24;
    private List<ItemStack> itemList;


    public AbstractInventory() {
        itemList = new ArrayList<>(DEFAULT_SIZE);
    }

    public AbstractInventory(int size) {
        itemList = new ArrayList<>(size);
    }

    public AbstractInventory(List<ItemStack> itemList) {
        this.itemList = new ArrayList<>(itemList);
    }

    @Override
    public void addItem(int index, ItemStack stack) {
        itemList.add(index, stack);
    }

    @Override
    public int getSize() {
        return itemList.size();
    }

    @Override
    public ItemStack getItem(int index) {
        return itemList.get(index);
    }

    @Override
    public ItemStack removeItem(int index) {
        return itemList.remove(index);
    }
}
