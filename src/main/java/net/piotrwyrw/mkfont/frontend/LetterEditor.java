package net.piotrwyrw.mkfont.frontend;

import net.piotrwyrw.mkfont.Control;
import net.piotrwyrw.mkfont.LetterData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LetterEditor extends JFrame {

    private LetterPanel panel;
    private LetterData data;
    private String designation;

    public LetterEditor() {
        super("Letter Editor [8x8]");

        panel = new LetterPanel();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        // Better do not change this to avoid screwing things up
        setResizable(false);

        panel.accept(this);

        setLocationRelativeTo(null);
        setVisible(true);

        promptForDesignation();

        data = new LetterData(LetterPanel.CELL_COUNT, designation);

        setupListeners();
    }

    private void promptForDesignation() {
        designation = JOptionPane.showInputDialog(this, "Please enter a designation for this letter", "Designation", JOptionPane.QUESTION_MESSAGE);

        if (designation == null || designation.isEmpty())
            dispose();

        designation = designation.replaceAll("\\s+", "");

        if (designation.isEmpty())
            dispose();

        setTitle(getTitle() + " ~ *\"L_" + designation + "\"");
    }

    private void saveAndExit() {
        Control.masterWindow().addLetter(data);
        JOptionPane.showMessageDialog(this, "Letter saved as \"" + data.getDesignation() + "\"");
        dispose();
    }

    private void setupListeners() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();

                int x = (int)(((double)p.getX() - LetterPanel.X_OVERSCAN) / ((double)LetterPanel.CELL_SIZE));
                int y = (int)(((double)p.getY() - LetterPanel.Y_OVERSCAN) / ((double)LetterPanel.CELL_SIZE));

                data.flip(x, y);
                panel.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (!e.isControlDown())
                    return;

                int kc = e.getKeyCode();
                if (kc == KeyEvent.VK_E) {
                    saveAndExit();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public LetterPanel getPanel() {
        return panel;
    }

    public LetterData getData() {
        return data;
    }

    public String getDesignation() {
        return designation;
    }
}
