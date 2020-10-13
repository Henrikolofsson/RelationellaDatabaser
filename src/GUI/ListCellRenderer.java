package GUI;
/*
    @Author: Henrik Olofsson
    @Date: 2020-10-12
    A class I ended up not needing. The main purpose was to clarify to the JList what information of the items it contained and what information that should be rendered.
 */
import Entities.ComboBoxItem;

import javax.swing.*;
import java.awt.*;

public class ListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value instanceof ComboBoxItem) {
            value = ((ComboBoxItem)value).toString();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}
