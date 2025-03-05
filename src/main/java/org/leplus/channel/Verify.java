package org.leplus.channel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.leplus.libcrypto.DSAPublicKey;
import org.leplus.libcrypto.DSASignature;
import org.leplus.libcrypto.DSASignatureEngine;
import org.leplus.libcrypto.JavaPRNGenerator;
import org.leplus.libcrypto.SHA1DigestEngine;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Application de v�rification de signature.
 */
public final class Verify implements ActionListener {

	private final JFrame mainFrame;
	private final JTextField pubField;
	private final JTextField fileField;
	private final JTextField sigField;

	public Verify() {

		mainFrame = new JFrame("Verify Signatures");

		final JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 1));

		final JPanel pubPanel = new JPanel();
		pubField = new JTextField(30);
		pubField.setText("Public Key");
		final JButton pubButton = new JButton("Select");
		pubButton.addActionListener(new FileListener(mainFrame, pubField, false));
		pubPanel.add(pubField);
		pubPanel.add(pubButton);
		mainPanel.add(pubPanel);

		final JPanel filePanel = new JPanel();
		fileField = new JTextField(30);
		fileField.setText("File");
		final JButton fileButton = new JButton("Select");
		fileButton.addActionListener(new FileListener(mainFrame, fileField, false));
		filePanel.add(fileField);
		filePanel.add(fileButton);
		mainPanel.add(filePanel);

		final JPanel sigPanel = new JPanel();
		sigField = new JTextField(30);
		sigField.setText("Signature");
		final JButton sigButton = new JButton("Select");
		sigButton.addActionListener(new FileListener(mainFrame, sigField, false));
		sigPanel.add(sigField);
		sigPanel.add(sigButton);
		mainPanel.add(sigPanel);

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
	@SuppressFBWarnings({"OBJECT_DESERIALIZATION", "PATH_TRAVERSAL_IN"})
	public void actionPerformed(final ActionEvent ae) {
        try (ObjectInputStream pubStream = new ObjectInputStream(new FileInputStream(pubField.getText()));
                FileInputStream fileStream = new FileInputStream(fileField.getText());
                ObjectInputStream sigStream = new ObjectInputStream(new FileInputStream(sigField.getText()))) {
			final DSAPublicKey pk = (DSAPublicKey) pubStream.readObject();
			final DSASignatureEngine g = new DSASignatureEngine(new JavaPRNGenerator());
			g.update(fileStream);
			final SHA1DigestEngine d = new SHA1DigestEngine();
			final DSASignature sig = (DSASignature) sigStream.readObject();
			final boolean b = g.verify(pk, sig);
			final JFrame msg = new JFrame("Summary");
			final JPanel pnl = new JPanel();
			String info;
			if (b) {
				d.update(fileStream);
				info = "<html><b>Valid signature.</b><br><br><b>Private key in subliminal channel:</b><br><br>";
				final BigInteger X = d.digest().getInt().multiply(sig.getS().subtract(pk.getY()).modInverse(pk.getQ()))
						.mod(pk.getQ());
				info += "X = " + split(X.toString());
				info += "</html>";
			} else {
				info = "<html><b>Invalid signature.</b></html>";
			}
			pnl.add(new JLabel(info));
			msg.getContentPane().add(pnl);
			msg.pack();
			msg.setVisible(true);
		} catch (final IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		mainFrame.dispose();
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
