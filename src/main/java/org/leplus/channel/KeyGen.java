package org.leplus.channel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Application de g�n�ration de cl�.
 *
 * @version $Revision: 1.4 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
@SuppressFBWarnings("PATH_TRAVERSAL_IN")
public final class KeyGen implements ActionListener {

	private static final Integer[] sizes = { 1024, 960, 896, 832, 768, 704, 640, 576, 512 };

	private final JFrame mainFrame;
	private final JComboBox<Integer> sizeCombo;
	private final JTextField pubField;
	private final JTextField privField;

	public KeyGen() {

		mainFrame = new JFrame("DSA Key Generation");

		final JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 1));

		final JPanel sizePanel = new JPanel();
		final JLabel sizeLabel = new JLabel("Key Length: ");
		sizeCombo = new JComboBox<Integer>(sizes);
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
		mainFrame.setVisible(true);
	}

	@Override
	@SuppressFBWarnings("PATH_TRAVERSAL_OUT")
	public void actionPerformed(final ActionEvent ae) throws NumberFormatException {
		try (ObjectOutputStream pubStream = new ObjectOutputStream(new FileOutputStream(pubField.getText()));
		        ObjectOutputStream privStream = new ObjectOutputStream(new FileOutputStream(privField.getText()))) {
			final int size = ((Integer) sizeCombo.getSelectedItem() - 512) / 64;
			final DSAKeyPairGenerator g = new DSAKeyPairGenerator(new JavaPRNGenerator(), size, 100);
			final DSAKeyPair kp = (DSAKeyPair) g.generateKeyPair();
			pubStream.writeObject(kp.getPublicKey());
			privStream.writeObject(kp.getPrivateKey());
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
	        msgFrame.setVisible(true);
	        mainFrame.dispose();
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}

	private String split(final String string) {
		if (string.length() < 50) {
			return string + "<br>";
		}
		StringBuilder result = new StringBuilder();
		result.append(string.substring(0, 49)).append("<br>");
		int i = 50;
		while (i <= string.length() - 50) {
			result.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append(string.substring(i, i + 49)).append("<br>");
			i += 50;
		}
		result.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append(string.substring(i)).append("<br>");
		return result.toString();
	}

}
