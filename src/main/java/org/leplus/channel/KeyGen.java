// $Id: KeyGen.java,v 1.4 2003/04/20 00:37:15 leplusth Exp $

package org.leplus.channel;

import org.leplus.libcrypto.JavaPRNGenerator;
import org.leplus.libcrypto.DSAKeyPair;
import org.leplus.libcrypto.DSAKeyPairGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;

/**
 * Application de génération de clé.
 *
 * @version $Revision: 1.4 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class KeyGen
	implements ActionListener {
	
	private static final String[] sizes = { "1024", "960", "896", "832", "768", "704", "640", "576", "512" };
	
	private JFrame     mainFrame;
	private JComboBox  sizeCombo;
	private JTextField pubField;
	private JTextField privField;
	
   	public KeyGen() {
		
		mainFrame = new JFrame("DSA Key Generation");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 1));
		
		JPanel sizePanel = new JPanel();
		JLabel sizeLabel = new JLabel("Key Length: ");
		sizeCombo = new JComboBox(sizes);
		sizePanel.add(sizeLabel);
		sizePanel.add(sizeCombo);
		mainPanel.add(sizePanel);
		
		JPanel pubPanel = new JPanel();
	    pubField = new JTextField(30);
		pubField.setText("Public Key");
		JButton pubButton = new JButton("Select");
		pubButton.addActionListener(new FileListener(mainFrame, pubField, true));
		pubPanel.add(pubField);
		pubPanel.add(pubButton);
		mainPanel.add(pubPanel);
		
		JPanel privPanel = new JPanel();
	    privField = new JTextField(30);
		privField.setText("Private Key");
		JButton privButton = new JButton("Select");
		privButton.addActionListener(new FileListener(mainFrame, privField, true));
		privPanel.add(privField);
		privPanel.add(privButton);
		mainPanel.add(privPanel);
		
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
			int size = (Integer.parseInt((String)sizeCombo.getSelectedItem()) - 512) / 64;
			DSAKeyPairGenerator g = new DSAKeyPairGenerator(new JavaPRNGenerator(), size, 100);
			DSAKeyPair kp = (DSAKeyPair)g.generateKeyPair();
			File pubKey = new File(pubField.getText());
			if (!pubKey.exists()) pubKey.createNewFile();
			ObjectOutputStream pubStream = new ObjectOutputStream(new FileOutputStream(pubKey));
			pubStream.writeObject(kp.getPublicKey());
			pubStream.close();
			File privKey = new File(privField.getText());
			if (!privKey.exists()) privKey.createNewFile();
			ObjectOutputStream privStream = new ObjectOutputStream(new FileOutputStream(privKey));
			privStream.writeObject(kp.getPrivateKey());
			privStream.close();
			JFrame msgFrame = new JFrame("Summary");
 			JPanel msgPanel = new JPanel();
			String info = "<html><b>Information about the keys:</b><br><br>";
			info += "P = " + split(kp.getP().toString());
			info += "Q = " + split(kp.getQ().toString());
			info += "G = " + split(kp.getG().toString());
			info += "X = " + split(kp.getX().toString());
			info += "Y = " + split(kp.getY().toString());
			info += "</html>";
			msgPanel.add(new JLabel(info));
			msgFrame.getContentPane().add(msgPanel);
			msgFrame.pack();
			msgFrame.show();
		} catch (IOException ex) {
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
