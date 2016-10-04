/* $Id: DSAKeyPair.java,v 1.6 2003/04/19 07:55:22 leplusth Exp $ */

package org.leplus.libcrypto;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;

/**
 * Pair Cl� Priv�e / Cl� Publique DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est d�fini par la norme
 * <a href="http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS 186-2</a>
 * (<i>Digital Signature Scheme</i>) du NIST.
 *
 * @version $Revision: 1.6 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class DSAKeyPair
	extends KeyPair {
	
	/**
	 * Construit une pair de cl�s DSA � partir de la cl� publique et
	 * de la cl� priv�e.
	 *
	 * @param pvk la cl� publique.
	 * @param pbk la cl� priv�e.
	 */
	public DSAKeyPair(DSAPrivateKey pvk, DSAPublicKey pbk) {
		if (!pvk.getP().equals(pbk.getP()))
			throw new RuntimeException();
		if (!pvk.getQ().equals(pbk.getQ()))
			throw new RuntimeException();
		if (!pvk.getG().equals(pbk.getG()))
			throw new RuntimeException();
		prvKey = pvk;
		pubKey = pbk;
		length = pubKey.length;
	}
	
	/**
	 * Retourne le param�tre P des cl�s.
	 *
	 * @return P.
	 */
	public BigInteger getP() {
		return ((DSAPublicKey)pubKey).getP();
	}
	
	/**
	 * Retourne le param�tre Q des cl�s.
	 *
	 * @return Q.
	 */
	public BigInteger getQ() {
		return ((DSAPublicKey)pubKey).getQ();
	}
	
	/**
	 * Retourne le param�tre G des cl�s.
	 *
	 * @return G.
	 */
	public BigInteger getG() {
		return ((DSAPublicKey)pubKey).getG();
	}
	
	/**
	 * Retourne le param�tre X de la cl� publique.
	 *
	 * @return X.
	 */
	public BigInteger getX() {
		return ((DSAPrivateKey)prvKey).getX();
	}
	
	/**
	 * Retourne le param�tre Y de la cl� priv�e.
	 *
	 * @return Y.
	 */
	public BigInteger getY() {
		return ((DSAPublicKey)pubKey).getY();
	}
	
	/**
	 * Compare deux pairs de cl�s.
	 *
	 * @param object la pair � comparer.
	 * @return true si les deux pairs sont �gaux, false sinon.
	 */
	public boolean equals(Object object) {
		KeyPair pair = (KeyPair)object;
		return ((DSAPublicKey)pubKey).equals(pair.pubKey)
			&& ((DSAPrivateKey)prvKey).equals(pair.prvKey);
	}
	
	/**
	 * Retourne une valeur de hachage simple pour cette pair de cl�s.
	 *
	 * @return la valeur de hachage.
	 */
	public int hashCode() {
		return pubKey.hashCode() * prvKey.hashCode();
	}
	
}
