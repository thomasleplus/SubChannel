// $Id: Main.java,v 1.4 2003/04/20 00:37:15 leplusth Exp $

package org.leplus.channel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * Classe principale de l'application.
 *
 * @version $Revision: 1.4 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class Main {
	
   	public static void main(String[] args)
		throws Exception {
		
		JFrame mainFrame = new JFrame("DSA Subliminal");
		
		JPanel mainPanel = new JPanel();
		
		JButton keyButton = new JButton("DSA Key Generation");
        keyButton.setMnemonic('G');
		keyButton.addActionListener(new popUp("org.leplus.channel.KeyGen"));
		mainPanel.add(keyButton);
		
		JButton signButton = new JButton("Sign Files");
        signButton.setMnemonic('S');
		signButton.addActionListener(new popUp("org.leplus.channel.Sign"));
		mainPanel.add(signButton);
		
		JButton verifyButton = new JButton("Verify Signatures");
        verifyButton.setMnemonic('V');
		verifyButton.addActionListener(new popUp("org.leplus.channel.Verify"));
		mainPanel.add(verifyButton);
		
		mainFrame.getContentPane().add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.show();
		
	}
	
	private static final class popUp
		implements ActionListener {
		
		String windowClass;
		
		public popUp(String cl) {
			windowClass = cl;
		}
		
		public void actionPerformed(ActionEvent ae) {
			try {
				getClass().forName(windowClass).newInstance();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
}
