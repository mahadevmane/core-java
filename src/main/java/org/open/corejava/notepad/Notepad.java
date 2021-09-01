package org.open.corejava.notepad;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class Notepad extends JFrame {
	private static final long serialVersionUID = 1L;
	static JTextArea notePad;
	private final JScrollPane sp;

	public Notepad() throws HeadlessException {
		setTitle("Notepad");
		setLayout(new BorderLayout());
		setSize(500, 300);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();

		JMenu file = new JMenu("File");
		file.setMnemonic('F');
		JMenuItem newItem = new JMenuItem("New", 'N');
		newItem.setAccelerator(KeyStroke.getKeyStroke('N',
				KeyEvent.CTRL_DOWN_MASK));
		newItem.addActionListener(new NewItemListener());
		file.add(newItem);
		JMenuItem openItem = new JMenuItem("Open", 'O');
		openItem.setAccelerator(KeyStroke.getKeyStroke('O',
				KeyEvent.CTRL_DOWN_MASK));
		openItem.addActionListener(new OpenItemListener());
		file.add(openItem);
		JMenuItem saveItem = new JMenuItem("Save", 'S');
		saveItem.setAccelerator(KeyStroke.getKeyStroke('S',
				KeyEvent.CTRL_DOWN_MASK));
		saveItem.addActionListener(new SaveItemListener());
		file.add(saveItem);
		file.addSeparator();
		JMenuItem printItem = new JMenuItem("Print...", 'P');
		printItem.setAccelerator(KeyStroke.getKeyStroke('P',
				KeyEvent.CTRL_DOWN_MASK));
		printItem.addActionListener(new PrintItemListener());
		file.add(printItem);
		file.addSeparator();
		JMenuItem exitItem = new JMenuItem("Exit", 'x');
		exitItem.addActionListener(new ExitItemListener());
		file.add(exitItem);
		menuBar.add(file);

		JMenu edit = new JMenu("Edit");
		edit.setMnemonic('E');
		JMenuItem cutItem = new JMenuItem("Cut", 't');
		cutItem.setAccelerator(KeyStroke.getKeyStroke('X',
				KeyEvent.CTRL_DOWN_MASK));
		cutItem.addActionListener(new CutItemListener());
		edit.add(cutItem);
		JMenuItem copyItem = new JMenuItem("Copy", 'C');
		copyItem.setAccelerator(KeyStroke.getKeyStroke('C',
				KeyEvent.CTRL_DOWN_MASK));
		copyItem.addActionListener(new CopyItemListener());
		edit.add(copyItem);
		JMenuItem pasteItem = new JMenuItem("Paste", 'P');
		pasteItem.setAccelerator(KeyStroke.getKeyStroke('V',
				KeyEvent.CTRL_DOWN_MASK));
		pasteItem.addActionListener(new PasteItemListener());
		edit.add(pasteItem);
		JMenuItem delItem = new JMenuItem("Delete", 'l');
		delItem.setAccelerator(KeyStroke.getKeyStroke("DELETE"));
		delItem.addActionListener(new DeleteItemListener());
		edit.add(delItem);
		edit.addSeparator();
		JMenuItem findItem = new JMenuItem("Find", 'F');
		findItem.setAccelerator(KeyStroke.getKeyStroke('F',
				KeyEvent.CTRL_DOWN_MASK));
		findItem.addActionListener(new FindItemListener());
		edit.add(findItem);
		menuBar.add(edit);

		JMenu format = new JMenu("Format");
		format.setMnemonic('o');
		JCheckBoxMenuItem wwItem = new JCheckBoxMenuItem("Word Wrap");
		wwItem.addActionListener(new WWItemListener());
		format.add(wwItem);
		JMenuItem fontItem = new JMenuItem("Font...", 'F');
		fontItem.addActionListener(new FontItemListener());
		format.add(fontItem);
		menuBar.add(format);

		JMenu view = new JMenu("View");
		view.setMnemonic('V');
		JCheckBoxMenuItem statusBarItem = new JCheckBoxMenuItem("Status Bar");
		statusBarItem.addActionListener(new StatusBarItemListener());
		view.add(statusBarItem);
		menuBar.add(view);

		JMenu help = new JMenu("Help");
		help.setMnemonic('H');
		JMenuItem helpItem = new JMenuItem("About Notepad", 'A');
		helpItem.addActionListener(new HelpItemListener());
		help.add(helpItem);
		menuBar.add(help);

		notePad = new JTextArea();
		notePad.setFont(new Font("Courier New", Font.PLAIN, 22));
		sp = new JScrollPane(notePad);

		add(menuBar, BorderLayout.NORTH);
		add(sp, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		JFrame jf = new Notepad();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}
