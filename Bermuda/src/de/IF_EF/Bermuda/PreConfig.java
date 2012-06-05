package de.IF_EF.Bermuda;

/**
 * Eine Klasse, deren Objekt eine Swing-Oberfläche erzeugt,
 * mit der die Konfiguration aus einer ZIP-Datei erstellt wird
 *
 * @author Fynn Hauptmeier
 * @version 1.0
 */
;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;

public class PreConfig {
	// Datenfelder
	private JFrame window;

	private static JFileChooser fileChooser = new JFileChooser(
                            System.getProperty("user.dir"));

	/**
	 * 
	 * 
	 * @return selectedFile Die ausgewählte Datei
	 */
	public static File getFile() {
		int result = fileChooser.showOpenDialog(null);

		if (result != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		File selectedFile = fileChooser.getSelectedFile();
		return selectedFile;
	}

	/**
	 * Startet die Swing-Gui
	 */
	public PreConfig() {
		fensterErzeugen();
	}

	// ---- Implementierung der Men�-Funktionen ----

	/**
	 * Ruft die Konfiguration auf
	 */
	private void openFile() {
		File file = getFile();
		if (file != null) {
			ConfigHelper.createConfig(file);
		}
		System.exit(0);
	}

	/**
	 * Beendet die Swing-Gui
	 */
	private void exit() {
		System.exit(0);
	}

	/**
	 * Erzeugt die Swing-Gui samt Inhalt.
	 */
	private void fensterErzeugen() {
		window = new JFrame("Konfiguration laden");
		createMenu(window);
		window.pack();
		window.setVisible(true);
	}

	/**
	 * Das Menü erzeugen.
	 * 
	 * @param window
	 *            Das Fenster, in das das Menüeingefügt werden soll.
	 */
	private void createMenu(JFrame window) {
		final int SHORTCUT_MASK = Toolkit.getDefaultToolkit()
				.getMenuShortcutKeyMask();

		JMenuBar menu = new JMenuBar();
		window.setJMenuBar(menu);

		JMenu fileMenu = new JMenu("Konfiguration");
		menu.add(fileMenu);

		JMenuItem openEntry = new JMenuItem("Konfiguration laden");
		openEntry.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				SHORTCUT_MASK));
		openEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		fileMenu.add(openEntry);

		JMenuItem exitEntry = new JMenuItem("Abbrechen");
		exitEntry.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				SHORTCUT_MASK));
		exitEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		fileMenu.add(exitEntry);

	}
}
