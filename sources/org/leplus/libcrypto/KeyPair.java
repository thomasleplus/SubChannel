/* $Id: KeyPair.java,v 1.3 2003/04/19 07:55:22 leplusth Exp $ */

package org.leplus.libcrypto;

/**
 * Pair Clé Privée / Clé Publique.
 *
 * @version $Revision: 1.3 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class KeyPair
	extends Key {
	
	/**
	 * La clé privée.
	 */
	protected PrivateKey prvKey;
	
	/**
	 * La clé publique.
	 */
	protected PublicKey pubKey;
	
	/**
	 * Retourne la clé privée.
	 *
	 * @return la clé privée.
	 */
	public final PrivateKey getPrivateKey() {
		return prvKey;
	}
	
	/**
	 * Retourne la clé publique.
	 *
	 * @return la clé publique.
	 */
	public final PublicKey getPublicKey() {
		return pubKey;
	}
	
}
