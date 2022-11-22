package net.piotrwyrw.mkfont.frontend;

import net.piotrwyrw.mkfont.LetterData;

import javax.swing.*;
import java.awt.*;

public class LetterPanel extends JPanel {

    public static final int CELL_SIZE = 80;
    public static final int CELL_COUNT = 8; // Do NOT change this
    public static final int REQ_DIM = CELL_SIZE * CELL_COUNT;
    public static final int X_OVERSCAN = 5;
    public static final int Y_OVERSCAN = 40;

    private LetterEditor parent;

    public void accept(LetterEditor editor) {
        editor.setSize(REQ_DIM + X_OVERSCAN, REQ_DIM + Y_OVERSCAN);
        editor.add(this);
        this.parent = editor;
    }

    @Override
    public void paintComponent(Graphics g) {

        // Set the background color
        g.setColor(ColorPalette.LE_BACKGROUND);
        g.fillRect(0, 0, REQ_DIM, REQ_DIM);

        g.setColor(ColorPalette.LE_FOREGROUND);

        // Draw the reference grid
        for (int i = 0; i < CELL_COUNT + 1; i++) {
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, REQ_DIM);
            g.drawLine(0, i * CELL_SIZE, REQ_DIM, i * CELL_SIZE);
        }

        // Fill in the set cells
        LetterData data = parent.getData();
        if (data == null)
            return;

        boolean[][] bData = data.getData();
        for (int x = 0; x < data.getDimension(); x ++)
            for (int y = 0; y < data.getDimension(); y ++)
                if (bData[x][y])
                    g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

    }

}
