package org.leplus.channel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.leplus.libcrypto.DSAPrivateKey;
import org.leplus.libcrypto.DSASignature;
import org.leplus.libcrypto.DSASignatureEngine;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Application de g�n�ration de signature.
 */
public final class Sign implements ActionListener {

	private final JFrame mainFrame;
	private final JTextField privField;
	private final JTextField fileField;
	private final JTextField sigField;

	public Sign() {

		mainFrame = new JFrame("Sign Files");

		final JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 1));

		final JPanel privPanel = new JPanel();
		privField = new JTextField(30);
		privField.setText("Private Key");
		final JButton privButton = new JButton("Select");
		privButton.addActionListener(new FileListener(mainFrame, privField, false));
		privPanel.add(privField);
		privPanel.add(privButton);
		mainPanel.add(privPanel);

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
		sigButton.addActionListener(new FileListener(mainFrame, sigField, true));
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
	@SuppressFBWarnings({"OBJECT_DESERIALIZATION", "PATH_TRAVERSAL_IN", "PATH_TRAVERSAL_OUT"})
	public void actionPerformed(final ActionEvent ae) {
        try (ObjectInputStream privStream = new ObjectInputStream(new FileInputStream(privField.getText()));
                FileInputStream fileStream = new FileInputStream(fileField.getText());
                ObjectOutputStream sigStream = new ObjectOutputStream(new FileOutputStream(sigField.getText()))) {
			final DSAPrivateKey pk = (DSAPrivateKey) privStream.readObject();
			final DSASignatureEngine g = new DSASignatureEngine(new BiasedPRNGenerator(pk.getX()));
			g.update(fileStream);
			final DSASignature sg = (DSASignature) g.sign(pk);
			sigStream.writeObject(sg);
			final JFrame msg = new JFrame("Summary");
			final JPanel pnl = new JPanel();
			String info = "<html><b>Information about signature:</b><br><br>";
			info += "R = " + sg.getR() + "<br>";
			info += "S = " + sg.getS() + "<br>";
			info += "</html>";
			pnl.add(new JLabel(info));
			msg.getContentPane().add(pnl);
			msg.pack();
			msg.setVisible(true);
		} catch (final IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		mainFrame.dispose();
	}

}
