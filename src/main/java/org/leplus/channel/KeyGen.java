package org.leplus.channel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.leplus.libcrypto.DSAKeyPair;
import org.leplus.libcrypto.DSAKeyPairGenerator;
import org.leplus.libcrypto.JavaPRNGenerator;

/**
 * Application de g�n�ration de cl�.
 *
 * @version $Revision: 1.4 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class KeyGen implements ActionListener {

	private static final String[] sizes = { "1024", "960", "896", "832", "768", "704", "640", "576", "512" };

	private final JFrame mainFrame;
	private final JComboBox sizeCombo;
	private final JTextField pubField;
	private final JTextField privField;

	public KeyGen() {

		mainFrame = new JFrame("DSA Key Generation");

		final JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 1));

		final JPanel sizePanel = new JPanel();
		final JLabel sizeLabel = new JLabel("Key Length: ");
		sizeCombo = new JComboBox(sizes);
		sizePanel.add(sizeLabel);
		sizePanel.add(sizeCombo);
		mainPanel.add(sizePanel);

		final JPanel pubPanel = new JPanel();
		pubField = new JTextField(30);
		pubField.setText("Public Key");
		final JButton pubButton = new JButton("Select");
		pubButton.addActionListener(new FileListener(mainFrame, pubField, true));
		pubPanel.add(pubField);
		pubPanel.add(pubButton);
		mainPanel.add(pubPanel);

		final JPanel privPanel = new JPanel();
		privField = new JTextField(30);
		privField.setText("Private Key");
		final JButton privButton = new JButton("Select");
		privButton.addActionListener(new FileListener(mainFrame, privField, true));
		privPanel.add(privField);
		privPanel.add(privButton);
		mainPanel.add(privPanel);

		final JPanel okPanel = new JPanel();
		final JButton okButton = new JButton("OK");
		okButton.addActionListener(this);
		okPanel.add(okButton);
		mainPanel.add(okPanel);

		mainFrame.getContentPane().add(mainPanel);
		mainFrame.pack();
		mainFrame.show();
	}

	@Override
	public void actionPerformed(final ActionEvent ae) {
		try {
			final int size = (Integer.parseInt((String) sizeCombo.getSelectedItem()) - 512) / 64;
			final DSAKeyPairGenerator g = new DSAKeyPairGenerator(new JavaPRNGenerator(), size, 100);
			final DSAKeyPair kp = (DSAKeyPair) g.generateKeyPair();
			final File pubKey = new File(pubField.getText());
			if (!pubKey.exists()) {
				pubKey.createNewFile();
			}
			final ObjectOutputStream pubStream = new ObjectOutputStream(new FileOutputStream(pubKey));
			pubStream.writeObject(kp.getPublicKey());
			pubStream.close();
			final File privKey = new File(privField.getText());
			if (!privKey.exists()) {
				privKey.createNewFile();
			}
			final ObjectOutputStream privStream = new ObjectOutputStream(new FileOutputStream(privKey));
			privStream.writeObject(kp.getPrivateKey());
			privStream.close();
			final JFrame msgFrame = new JFrame("Summary");
			final JPanel msgPanel = new JPanel();
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
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
		mainFrame.dispose();
	}

	private String split(final String string) {
		if (string.length() < 50) {
			return string + "<br>";
		}
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
