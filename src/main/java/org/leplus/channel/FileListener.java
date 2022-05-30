package org.leplus.channel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * S�lectionneur de fichier.
 *
 * @version $Revision: 1.3 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
@SuppressFBWarnings("EI_EXPOSE_REP2")
public final class FileListener implements ActionListener {

	private final JFrame frame;
	private final JTextField field;
	private final boolean mode;

	public FileListener(final JFrame frame, final JTextField field, final boolean mode) {
		this.frame = frame;
		this.field = field;
		this.mode = mode;
	}

	@Override
	public void actionPerformed(final ActionEvent ae) {
		final JFileChooser fc = new JFileChooser();
		if (mode) {
			fc.setDialogType(JFileChooser.SAVE_DIALOG);
			fc.setDialogTitle("Sauvegarder");
		} else {
			fc.setDialogType(JFileChooser.OPEN_DIALOG);
			fc.setDialogTitle("Ouvrir");
		}
		if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			field.setText(fc.getSelectedFile().getAbsolutePath());
		}
	}

}
