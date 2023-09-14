import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class texteditor implements ActionListener {
    JFrame frame;
    // add menu bar
    JMenuBar menuBar;

    JMenu File, Edit;
    // add menu for file
    JMenuItem Newfile, Openfile, Savefile;
    // add menu for edit
    JMenuItem Copy, Cut, Paste, Selectall, Close;
    // text area
    JTextArea textArea;

    texteditor() {
        frame = new JFrame();

        menuBar = new JMenuBar();
        textArea = new JTextArea(null, null, 0, 0);

        File = new JMenu("File");
        Edit = new JMenu("Edit");

        // initillize file items
        Newfile = new JMenuItem("New Window");
        Openfile = new JMenuItem("Open File");
        Savefile = new JMenuItem("Save File");
        // add to actionlistener
        Newfile.addActionListener(this);
        Openfile.addActionListener(this);
        Savefile.addActionListener(this);

        // add to them in file

        File.add(Newfile);
        File.add(Openfile);
        File.add(Savefile);

        // initiallize edit items
        Copy = new JMenuItem("Copy");
        Cut = new JMenuItem("Cut");
        Paste = new JMenuItem("Paste");
        Selectall = new JMenuItem("SelectAll");
        Close = new JMenuItem("Close");
        // add to actionlistener
        Copy.addActionListener(this);
        Cut.addActionListener(this);
        Paste.addActionListener(this);
        Selectall.addActionListener(this);
        Close.addActionListener(this);

        // added to edit

        Edit.add(Copy);
        Edit.add(Cut);
        Edit.add(Paste);
        Edit.add(Selectall);
        Edit.add(Close);

        // add menu to menu bar
        menuBar.add(File);
        menuBar.add(Edit);

        // set menu bar
        frame.setJMenuBar(menuBar);
        // frame.add(textArea);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));

        panel.add(textArea, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane);

        frame.add(panel);

        // frame.setBounds(200, 100, 1000, 1000);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle("Simanshu Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Openfile) {
            JFileChooser fileChooser = new JFileChooser("C:");
            int openbutton = fileChooser.showOpenDialog(null);

            if (openbutton == JFileChooser.APPROVE_OPTION) {
                java.io.File file = fileChooser.getSelectedFile();
                String filepath = file.getPath();
                try {
                    FileReader fileReader = new FileReader(filepath);

                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String Intermediate = "", output = "";
                    while ((Intermediate = bufferedReader.readLine()) != null) {
                        output += Intermediate + "\n";

                    }
                    textArea.setText(output);

                } catch (IOException ee) {
                    ee.printStackTrace();

                }
            }
        }
        if (e.getSource() == Savefile) {
            JFileChooser fileChooser = new JFileChooser("C:");

            int saveButton = fileChooser.showSaveDialog(null);
            if (saveButton == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() +
                        "text");

                try {
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        }
        if (e.getSource() == Newfile) {
            texteditor nTexteditor = new texteditor();

        }
        if (e.getSource() == Copy) {
            textArea.copy();
        }
        if (e.getSource() == Cut) {
            textArea.cut();
        }
        if (e.getSource() == Selectall) {
            textArea.selectAll();
        }
        if (e.getSource() == Paste) {
            textArea.paste();
        }
        if (e.getSource() == Close) {
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        texteditor text = new texteditor();

    }
}