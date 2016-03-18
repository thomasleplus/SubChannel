// $Id: FileListener.java,v 1.3 2003/04/19 07:55:14 leplusth Exp $

package org.leplus.channel;

import java.io.File;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JFileChooser;

/**
 * Sélectionneur de fichier.
 *
 * @version $Revision: 1.3 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class FileListener
	implements ActionListener {
	
	private JFrame     frame;
	private JTextField field;
	private boolean    mode;
	
   	public FileListener(JFrame frame, JTextField field, boolean mode) {
		this.frame = frame;
		this.field = field;
		this.mode = mode;
	}
	
	public void actionPerformed(ActionEvent ae) {
		JFileChooser fc = new JFileChooser();
		if (mode) {
			fc.setDialogType(JFileChooser.SAVE_DIALOG);
			fc.setDialogTitle("Sauvegarder");
		} else {
			fc.setDialogType(JFileChooser.OPEN_DIALOG);
			fc.setDialogTitle("Ouvrir");
		}
		if(fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
			field.setText(fc.getSelectedFile().getAbsolutePath());
	}
	
}
