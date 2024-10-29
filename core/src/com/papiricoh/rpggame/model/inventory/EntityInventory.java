package com.papiricoh.rpggame.model.inventory;

import java.util.ArrayList;
import java.util.List;

public class EntityInventory extends AbstractInventory {

    public EntityInventory(int size) {
        super(new ArrayList<ItemStack>(size));
    }

    public EntityInventory(List<ItemStack> itemList) {
        super(itemList);
    }
}
