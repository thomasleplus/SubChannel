// $Id: Verify.java,v 1.3 2003/04/19 07:55:15 leplusth Exp $

package org.leplus.channel;

import org.leplus.libcrypto.JavaPRNGenerator;
import org.leplus.libcrypto.DSAPublicKey;
import org.leplus.libcrypto.DSASignature;
import org.leplus.libcrypto.DSASignatureEngine;
import org.leplus.libcrypto.SHA1DigestEngine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

import java.math.BigInteger;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Application de vérification de signature.
 *
 * @version $Revision: 1.3 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class Verify
	implements ActionListener {
	
	private JFrame     mainFrame;
	private JTextField pubField;
	private JTextField privField;
	private JTextField fileField;
	private JTextField sigField;
	
   	public Verify() {
		
		mainFrame = new JFrame("Vérification de signatures");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 1));
		
		JPanel pubPanel = new JPanel();
	    pubField = new JTextField(30);
		pubField.setText("Clé publique");
		JButton pubButton = new JButton("Sélectionner");
		pubButton.addActionListener(new FileListener(mainFrame, pubField, false));
		pubPanel.add(pubField);
		pubPanel.add(pubButton);
		mainPanel.add(pubPanel);
		
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
		sigButton.addActionListener(new FileListener(mainFrame, sigField, false));
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
			File pubKey = new File(pubField.getText());
			ObjectInputStream pubStream = new ObjectInputStream(new FileInputStream(pubKey));
			DSAPublicKey pk = (DSAPublicKey)pubStream.readObject();
			pubStream.close();
			DSASignatureEngine g = new DSASignatureEngine(new JavaPRNGenerator());
			File file = new File(fileField.getText());
			FileInputStream fileStream = new FileInputStream(file);
			g.update(fileStream);
			fileStream.close();
			SHA1DigestEngine d = new SHA1DigestEngine();
			File sigKey = new File(sigField.getText());
			ObjectInputStream sigStream = new ObjectInputStream(new FileInputStream(sigKey));
			DSASignature sig = (DSASignature)sigStream.readObject();
			sigStream.close();
			boolean b = g.verify(pk, sig);
			JFrame msg = new JFrame("Résumé");
			JPanel pnl = new JPanel();
			String info;
			if (b) {
				fileStream = new FileInputStream(file);
				d.update(fileStream);
				fileStream.close();
				info  = "<html><b>Signature valide.</b><br><br><b>Clé privée dans le canal subliminal :</b><br><br>";
				BigInteger X = d.digest().getInt().multiply(sig.getS().subtract(pk.getY()).modInverse(pk.getQ())).mod(pk.getQ());
				info += "X = " + split(X.toString());
				info +="</html>";
			} else
				info = "<html><b>Signature invalide.</b></html>";
			pnl.add(new JLabel(info));
			msg.getContentPane().add(pnl);
			msg.pack();
			msg.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		mainFrame.dispose();
	}
	
	private String split(String string) {
		if (string.length() < 50)
			return string + "<br>";
		String result = string.substring(0, 49) + "<br>";
		int i = 50;
		while (i <= string.length() - 50) {
			result += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + string.substring(i, i + 49) + "<br>";
			i += 50;
		}
		result += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + string.substring(i) + "<br>";
		return result;
	}
	
}
