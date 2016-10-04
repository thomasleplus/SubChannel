/* $Id: DSASignature.java,v 1.6 2003/04/19 07:55:22 leplusth Exp $ */

package org.leplus.libcrypto;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Signature Cryptographique DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est d�fini par la norme
 * <a href="http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS 186-2</a>
 * (<i>Digital Signature Scheme</i>) du NIST.
 *
 * @version $Revision: 1.6 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class DSASignature
	extends Signature {
	
	/**
	 * La longueur en octets des signatures DSA.
	 */
	public static final int LENGTH = 20;
	
	/**
	 * La longueur en octets des signatures DSA encod�es.
	 */
	public static final int ENCODED_LENGTH = 46;
	
	/**
	 * Le param�tre r.
	 */
	private BigInteger R;
	
	/**
	 * Le param�tre s.
	 */
	private BigInteger S;
	
	/**
	 * Construit une signature DSA � partir de ses param�tres.
	 *
	 * @param r le param�tre r.
	 * @param s le param�tre s.
	 */
	protected DSASignature(BigInteger r, BigInteger s) {
		R = r;
		S = s;
		length = LENGTH;
	}
	
	/**
	 * Retourne le param�tre R de la signature.
	 *
	 * @return le param�tre R.
	 */
	public BigInteger getR() {
		return R;
	}
	
	/**
	 * Retourne le param�tre S de la signature.
	 *
	 * @return le param�tre S.
	 */
	public BigInteger getS() {
		return S;
	}
	
	/**
	 * Retourne la longueur de la signature (en octets).
	 *
	 * @return la longueur de la signature (en octets).
	 */
	protected int doGetLength() {
		return LENGTH;
	}
	
	/**
	 * Compare deux signatures.
	 *
	 * @param object la signature � comparer.
	 * @return true si les deux signatures sont �gales, false sinon.
	 */
	public boolean equals(Object object) {
		DSASignature signature = (DSASignature)object;
		return R.equals(signature.R) && S.equals(signature.S);
	}
	
	/**
	 * Retourne une valeur de hachage simple pour cette signature.
	 *
	 * @return la valeur de hachage.
	 */
	public int hashCode() {
		return R.hashCode() * S.hashCode();
	}
	
}
