package org.leplus.channel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Classe principale de l'application.
 *
 * @version $Revision: 1.4 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class Main {

	private static final class popUp implements ActionListener {

		String windowClass;

		public popUp(final String cl) {
			windowClass = cl;
		}

		@Override
		public void actionPerformed(final ActionEvent ae) {
			try {
				getClass();
				Class.forName(windowClass).getDeclaredConstructor().newInstance();
			} catch (final Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	public static void main(final String[] args) {

		final JFrame mainFrame = new JFrame("DSA Subliminal");

		final JPanel mainPanel = new JPanel();

		final JButton keyButton = new JButton("DSA Key Generation");
		keyButton.setMnemonic('G');
		keyButton.addActionListener(new popUp("org.leplus.channel.KeyGen"));
		mainPanel.add(keyButton);

		final JButton signButton = new JButton("Sign Files");
		signButton.setMnemonic('S');
		signButton.addActionListener(new popUp("org.leplus.channel.Sign"));
		mainPanel.add(signButton);

		final JButton verifyButton = new JButton("Verify Signatures");
		verifyButton.setMnemonic('V');
		verifyButton.addActionListener(new popUp("org.leplus.channel.Verify"));
		mainPanel.add(verifyButton);

		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);

	}

}
