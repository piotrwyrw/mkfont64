package net.piotrwyrw.mkfont;

import net.piotrwyrw.mkfont.frontend.LetterEditor;
import net.piotrwyrw.mkfont.frontend.MasterWindow;
import net.piotrwyrw.mkfont.frontend.MenuSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control {

    private static MasterWindow window;

    public Control() {
        masterWindow();
        setupListeners();
    }

    private void setupListeners() {
        MenuSystem system = window.getSystem();
        system.hookInto("new", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LetterEditor();
            }
        });

        system.hookInto("about", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(window, "You are running MkFont 64 v" + MkfontConstants.VERSION + " by Piotr K. Wyrwas", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static MasterWindow masterWindow() {
        if (window != null)
            return window;

        window = new MasterWindow();
        return window;
    }

}
