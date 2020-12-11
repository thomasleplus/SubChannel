/*
 * SubChannel - A study on subliminal channels in DSA algorithm.
 * Copyright (C) 2016 Thomas Leplus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.leplus.channel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * S�lectionneur de fichier.
 *
 * @version $Revision: 1.3 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
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
