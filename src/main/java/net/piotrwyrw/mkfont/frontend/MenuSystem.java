package net.piotrwyrw.mkfont.frontend;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MenuSystem {

    private HashMap<String, JMenuItem> items;

    public MenuSystem() {
        this.items = new HashMap<>();
    }

    public void add(String id, JMenuItem itm) {
        if (items.containsKey(id))
            throw new RuntimeException("The identifiers have to be unique.");

        items.put(id, itm);
    }

    public void hookInto(String id, ActionListener listener) {
        if (!items.containsKey(id))
            throw new RuntimeException("The specified ID is not present in the list.");

        items.get(id).addActionListener(listener);
    }

    public HashMap<String, JMenuItem> getItems() {
        return items;
    }
}
