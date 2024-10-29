package com.papiricoh.rpggame.model.inventory;

import com.papiricoh.rpggame.model.data.DataTag;

public class Item {
    private String label;
    private DataTag data;

    public Item(String label) {
        this.label = label;
        this.data = null;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Item)) {
            return false;
        }
        Item item = (Item) obj;

        if((item.getLabel().equals(this.getLabel())) && ((this.data == null && item.getData() == null) || (this.data.equals(item.data)))) {
            return true;
        }
        return false;
    }

    private DataTag getData() {
        return this.data;
    }
}
