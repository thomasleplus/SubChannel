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

import org.leplus.libcrypto.DSAPrivateKey;
import org.leplus.libcrypto.DSASignature;
import org.leplus.libcrypto.DSASignatureEngine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Application de génération de signature.
 *
 * @version $Revision: 1.3 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class Sign
	implements ActionListener {
	
	private JFrame     mainFrame;
	private JTextField privField;
	private JTextField fileField;
	private JTextField sigField;
	
   	public Sign() {
		
		mainFrame = new JFrame("Sign Files");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 1));
		
		JPanel privPanel = new JPanel();
	    privField = new JTextField(30);
		privField.setText("Private Key");
		JButton privButton = new JButton("Select");
		privButton.addActionListener(new FileListener(mainFrame, privField, false));
		privPanel.add(privField);
		privPanel.add(privButton);
		mainPanel.add(privPanel);
		
		JPanel filePanel = new JPanel();
	    fileField = new JTextField(30);
		fileField.setText("File");
		JButton fileButton = new JButton("Select");
		fileButton.addActionListener(new FileListener(mainFrame, fileField, false));
		filePanel.add(fileField);
		filePanel.add(fileButton);
		mainPanel.add(filePanel);
		
		JPanel sigPanel = new JPanel();
	    sigField = new JTextField(30);
		sigField.setText("Signature");
		JButton sigButton = new JButton("Select");
		sigButton.addActionListener(new FileListener(mainFrame, sigField, true));
		sigPanel.add(sigField);
		sigPanel.add(sigButton);
		mainPanel.add(sigPanel);
		
		JPanel okPanel = new JPanel();
		JButton okButton = new JButton("OK");
		okButton.addActionListener(this);
		okPanel.add(okButton);
		mainPanel.add(okPanel);
		
		mainFrame.getContentPane().add(mainPanel);
        mainFrame.pack();
        mainFrame.show();
	}
	
	public void actionPerformed(ActionEvent ae) {
		try {
			File privKey = new File(privField.getText());
			ObjectInputStream privStream = new ObjectInputStream(new FileInputStream(privKey));
			DSAPrivateKey pk = (DSAPrivateKey)privStream.readObject();
			privStream.close();
			DSASignatureEngine g = new DSASignatureEngine(new BiasedPRNGenerator(pk.getX()));
			File file = new File(fileField.getText());
			FileInputStream fileStream = new FileInputStream(file);
			g.update(fileStream);
			fileStream.close();
			DSASignature sg = (DSASignature)g.sign(pk);
			File sig = new File(sigField.getText());
			if (!sig.exists()) sig.createNewFile();
			ObjectOutputStream sigStream = new ObjectOutputStream(new FileOutputStream(sig));
			sigStream.writeObject(sg);
			sigStream.close();
			JFrame msg = new JFrame("Summary");
			JPanel pnl = new JPanel();
			String info = "<html><b>Information about signature:</b><br><br>";
			info += "R = " + sg.getR() + "<br>";
			info += "S = " + sg.getS() + "<br>";
			info += "</html>";
			pnl.add(new JLabel(info));
			msg.getContentPane().add(pnl);
			msg.pack();
			msg.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		mainFrame.dispose();
	}
	
}
