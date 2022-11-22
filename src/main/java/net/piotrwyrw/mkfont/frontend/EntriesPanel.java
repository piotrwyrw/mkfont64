package net.piotrwyrw.mkfont.frontend;

import net.piotrwyrw.mkfont.LetterData;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The panel that contains all thumbnails of the letters
 * created so far.
 */
public class EntriesPanel extends JPanel {

    public static final int width = 300;
    public static final int height = 300;

    public EntriesPanel() {
        super();
        setLayout(new FlowLayout());
    }

    public void addEntry(LetterData data) {
        JPanel p = new JPanel();
        p.setBackground(ColorPalette.EP_BACKGROUND);
        p.setLayout(new FlowLayout());

        JLabel label = new JLabel(data.getDesignation());
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        p.add(label);
        p.setMinimumSize(new Dimension(100, 150));
        add(p);
    }

}
