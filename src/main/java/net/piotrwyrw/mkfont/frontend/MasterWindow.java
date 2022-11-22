package net.piotrwyrw.mkfont.frontend;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;
import net.piotrwyrw.mkfont.LetterData;

import javax.swing.*;
import java.util.HashMap;

public class MasterWindow extends JFrame {

    private EntriesPanel entriesPanel;
    private JMenuBar menuBar;
    private MenuSystem system;
    private HashMap<String, LetterData> letters;

    public MasterWindow() {
        super("MkFont 64");

        letters = new HashMap<>();

        setSize(1500, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        layoutSetup();

        setVisible(true);
    }

    private void layoutSetup() {
        // Set the Look And Feel to a Intellij Dark theme-like one
        LafManager.install(new DarculaTheme());

        menuBar = new JMenuBar();
        system = new MenuSystem();

        JMenu actionMenu = new JMenu("Action");

        JMenuItem newItem = new JMenuItem("New Letter", 'N');
        actionMenu.add(newItem);
        system.add("new", newItem);

        JMenuItem loadItem = new JMenuItem("Load", 'L');
        actionMenu.add(loadItem);
        system.add("load", loadItem);

        JMenuItem exportItem = new JMenuItem("Export as assembly code", 'E');
        actionMenu.add(exportItem);
        system.add("export", exportItem);

        JMenu miscMenu = new JMenu("Miscellaneous");

        JMenuItem aboutItem = new JMenuItem("About", 'A');
        miscMenu.add(aboutItem);
        system.add("about", aboutItem);

        menuBar.add(actionMenu);
        menuBar.add(miscMenu);

        setJMenuBar(menuBar);

        this.entriesPanel = new EntriesPanel();
        add(this.entriesPanel);
    }

    public EntriesPanel getEntriesPanel() {
        return entriesPanel;
    }

    public MenuSystem getSystem() {
        return system;
    }

    public HashMap<String, LetterData> getLetters() {
        return letters;
    }

    private String promptForNewDesignator() {
        String str = JOptionPane.showInputDialog(this, "Please enter a new designator for the entry", "Invalid Entry Designation", JOptionPane.WARNING_MESSAGE);
        if (str == null)
            return promptForNewDesignator();

        if (str.isEmpty())
            return promptForNewDesignator();

        str = str.replaceAll("\\s+", "");

        if (str.isEmpty())
            return promptForNewDesignator();

        return str;
    }

    public void addLetter(LetterData data) {
        if (this.letters.containsKey(data.getDesignation())) {
            String another = promptForNewDesignator();
            data.setDesignation(another);
        }

        this.letters.put(data.getDesignation(), data);
        this.entriesPanel.addEntry(data);
        revalidate();
    }

}
