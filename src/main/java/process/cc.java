package process;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class cc {
    public static void main(String[] argv) throws Exception {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        ArrayList<Integer> b = new ArrayList<>(a);
        b.set(0, 9);
        for(int i:a){
            System.out.println(i);
        }
    }
}

class MyItemListener implements ItemListener {
    // This method is called only if a new item has been selected.
    public void itemStateChanged(ItemEvent evt) {
        JComboBox cb = (JComboBox) evt.getSource();

        Object item = evt.getItem();

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Item was just selected
        } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
            // Item is no longer selected
        }
    }
}