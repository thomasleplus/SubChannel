/* $Id: DSAPrivateKey.java,v 1.6 2003/04/19 07:55:22 leplusth Exp $ */

package org.leplus.libcrypto;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Cl� Priv�e DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est d�fini par la norme
 * <a href="http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS 186-2</a>
 * (<i>Digital Signature Scheme</i>) du NIST.
 *
 * @version $Revision: 1.6 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class DSAPrivateKey
	extends PrivateKey {
	
	/**
	 * Le param�tre p.
	 */
	private BigInteger P;
	
	/**
	 * Le param�tre q.
	 */
	private BigInteger Q;
	
	/**
	 * Le param�tre g.
	 */
	private BigInteger G;
	
	/**
	 * Le param�tre x.
	 */
	private BigInteger X;
	
	/**
	 * Construit une cl� priv�e DSA � partir de ses param�tres.
	 *
	 * @param p le param�tre p.
	 * @param q le param�tre q.
	 * @param g le param�tre g.
	 * @param x le param�tre x.
	 */
	protected DSAPrivateKey(BigInteger p, BigInteger q, BigInteger g, BigInteger x) {
		P = p;
		Q = q;
		G = g;
		X = x;
		length = (int)StrictMath.ceil((double)P.bitLength() / 128) * 16;
	}
	
	/**
	 * Retourne le param�tre p.
	 *
	 * @return le param�tre p.
	 */
	public BigInteger getP() {
		return P;
	}
	
	/**
	 * Retourne le param�tre q.
	 *
	 * @return le param�tre q.
	 */
	public BigInteger getQ() {
		return Q;
	}
	
	/**
	 * Retourne le param�tre g.
	 *
	 * @return le param�tre g.
	 */
	public BigInteger getG() {
		return G;
	}
	
	/**
	 * Retourne le param�tre x.
	 *
	 * @return le param�tre x.
	 */
	public BigInteger getX() {
		return X;
	}
	
	/**
	 * Compare deux cl�s.
	 *
	 * @param object la cl� � comparer.
	 * @return true si les deux cl�s sont �gales, false sinon.
	 */
	public boolean equals(Object object) {
		DSAPrivateKey key = (DSAPrivateKey)object;
		return P.equals(key.P) && Q.equals(key.Q) && G.equals(key.G) && X.equals(key.X);
	}
	
	/**
	 * Retourne une valeur de hachage simple pour cette cl�.
	 *
	 * @return la valeur de hachage.
	 */
	public int hashCode() {
		return P.hashCode() * Q.hashCode() * G.hashCode() * X.hashCode();
	}
	
}
