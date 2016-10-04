/* $Id: DSAPrivateKey.java,v 1.6 2003/04/19 07:55:22 leplusth Exp $ */

package org.leplus.libcrypto;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Clé Privée DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est défini par la norme
 * <a href="http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS 186-2</a>
 * (<i>Digital Signature Scheme</i>) du NIST.
 *
 * @version $Revision: 1.6 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class DSAPrivateKey
	extends PrivateKey {
	
	/**
	 * Le paramètre p.
	 */
	private BigInteger P;
	
	/**
	 * Le paramètre q.
	 */
	private BigInteger Q;
	
	/**
	 * Le paramètre g.
	 */
	private BigInteger G;
	
	/**
	 * Le paramètre x.
	 */
	private BigInteger X;
	
	/**
	 * Construit une clé privée DSA à partir de ses paramètres.
	 *
	 * @param p le paramètre p.
	 * @param q le paramètre q.
	 * @param g le paramètre g.
	 * @param x le paramètre x.
	 */
	protected DSAPrivateKey(BigInteger p, BigInteger q, BigInteger g, BigInteger x) {
		P = p;
		Q = q;
		G = g;
		X = x;
		length = (int)StrictMath.ceil((double)P.bitLength() / 128) * 16;
	}
	
	/**
	 * Retourne le paramètre p.
	 *
	 * @return le paramètre p.
	 */
	public BigInteger getP() {
		return P;
	}
	
	/**
	 * Retourne le paramètre q.
	 *
	 * @return le paramètre q.
	 */
	public BigInteger getQ() {
		return Q;
	}
	
	/**
	 * Retourne le paramètre g.
	 *
	 * @return le paramètre g.
	 */
	public BigInteger getG() {
		return G;
	}
	
	/**
	 * Retourne le paramètre x.
	 *
	 * @return le paramètre x.
	 */
	public BigInteger getX() {
		return X;
	}
	
	/**
	 * Compare deux clés.
	 *
	 * @param object la clé à comparer.
	 * @return true si les deux clés sont égales, false sinon.
	 */
	public boolean equals(Object object) {
		DSAPrivateKey key = (DSAPrivateKey)object;
		return P.equals(key.P) && Q.equals(key.Q) && G.equals(key.G) && X.equals(key.X);
	}
	
	/**
	 * Retourne une valeur de hachage simple pour cette clé.
	 *
	 * @return la valeur de hachage.
	 */
	public int hashCode() {
		return P.hashCode() * Q.hashCode() * G.hashCode() * X.hashCode();
	}
	
}
