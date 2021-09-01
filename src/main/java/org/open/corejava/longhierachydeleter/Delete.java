package org.open.corejava.longhierachydeleter;

/**
 * Mainly useful when system gives an error "To Long Path" while deleting...
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Delete extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private final JPanel jp1;
    private final JPanel jp2;
	static JTextField path;
	private final JButton browse;
    private final JButton del;
    private final JButton cancel;
	static JProgressBar progress;

	public Delete() {
		setTitle("Long Hierachy Deleter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		jp1 = new JPanel();
		jp1.add(new JLabel("Select File or Folder: "));

		path = new JTextField(22);
		path.setEditable(false);
		jp1.add(path);

		browse = new JButton(" Browse ");
		browse.setMnemonic('B');
		browse.addActionListener(this);
		jp1.add(browse);

		del = new JButton("Start Deleting...");
		del.setMnemonic('S');
		del.addActionListener(this);
		jp1.add(del);

		cancel = new JButton(" Cancel ");
		cancel.setMnemonic('C');
		cancel.addActionListener(this);
		jp1.add(cancel);

		jp2 = new JPanel();
		jp2.setLayout(new BorderLayout());
		UIManager.put("ProgressBar.selectionBackground", Color.black);
		UIManager.put("ProgressBar.selectionForeground", Color.white);
		UIManager.put("ProgressBar.foreground", new Color(7, 190, 22));
		progress = new JProgressBar();
		progress.setStringPainted(true);
		progress.setVisible(false);
		jp2.add(jp1, BorderLayout.CENTER);
		jp2.add(progress, BorderLayout.SOUTH);

		add(jp2);
	}

	public static void main(String[] args) {
		JFrame f = new Delete();
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals(" Browse "))
			new Thread(new BrowseAction()).start();
		else if (action.equals("Start Deleting..."))
			new Thread(new DeleteAction()).start();
		else
			System.exit(0);
	}
}
