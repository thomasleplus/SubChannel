/* $Id: KeyPairGenerator.java,v 1.3 2003/04/19 07:55:22 leplusth Exp $ */

package org.leplus.libcrypto;

/**
 * Générateur de Pair de Clés Cryptographiques.
 *
 * @version $Revision: 1.3 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class KeyPairGenerator {
	
	/**
	 * Retourne la longueur des clés générées (en octets).
	 *
	 * @return la longueur des clés (en octets).
	 */
	public final int getLength() {
		return doGetLength();
	}
	
	/**
	 * Retourne la longueur des clés générées (en octets).
	 *
	 * @return la longueur des clés (en octets).
	 */
	protected abstract int doGetLength();
	
	/**
	 * Génère une nouvelle pair de clés.
	 *
	 * @return la pair de clés.
	 */
	public final KeyPair generateKeyPair() {
		return doGenerateKeyPair();
	}
	
	/**
	 * Génère une nouvelle pair de clés.
	 *
	 * @return la pair de clés.
	 */
	protected abstract KeyPair doGenerateKeyPair();
	
}
