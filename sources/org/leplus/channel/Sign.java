// $Id: Sign.java,v 1.3 2003/04/19 07:55:15 leplusth Exp $

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
		
		mainFrame = new JFrame("Signature de fichiers");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 1));
		
		JPanel privPanel = new JPanel();
	    privField = new JTextField(30);
		privField.setText("Clé privée");
		JButton privButton = new JButton("Sélectionner");
		privButton.addActionListener(new FileListener(mainFrame, privField, false));
		privPanel.add(privField);
		privPanel.add(privButton);
		mainPanel.add(privPanel);
		
		JPanel filePanel = new JPanel();
	    fileField = new JTextField(30);
		fileField.setText("Fichier");
		JButton fileButton = new JButton("Sélectionner");
		fileButton.addActionListener(new FileListener(mainFrame, fileField, false));
		filePanel.add(fileField);
		filePanel.add(fileButton);
		mainPanel.add(filePanel);
		
		JPanel sigPanel = new JPanel();
	    sigField = new JTextField(30);
		sigField.setText("Signature");
		JButton sigButton = new JButton("Sélectionner");
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
			JFrame msg = new JFrame("Résumé");
			JPanel pnl = new JPanel();
			String info = "<html><b>Informations sur la signature :</b><br><br>";
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
