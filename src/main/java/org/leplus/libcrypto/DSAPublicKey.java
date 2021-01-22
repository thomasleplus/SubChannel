package org.leplus.libcrypto;

import java.math.BigInteger;

/**
 * Cl� Publique DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est d�fini par la norme <a href=
 * "http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS
 * 186-2</a> (<i>Digital Signature Scheme</i>) du NIST.
 *
 * @version $Revision: 1.5 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class DSAPublicKey extends PublicKey {

	/**
	 *
	 */
	private static final long serialVersionUID = -3734011439289082463L;

	/**
	 * Le param�tre p.
	 */
	private final BigInteger P;

	/**
	 * Le param�tre q.
	 */
	private final BigInteger Q;

	/**
	 * Le param�tre g.
	 */
	private final BigInteger G;

	/**
	 * Le param�tre y.
	 */
	private final BigInteger Y;

	/**
	 * Construit une cl� publique DSA � partir de ses param�tres.
	 *
	 * @param p le param�tre p.
	 * @param q le param�tre q.
	 * @param g le param�tre g.
	 * @param y le param�tre y.
	 */
	protected DSAPublicKey(final BigInteger p, final BigInteger q, final BigInteger g, final BigInteger y) {
		P = p;
		Q = q;
		G = g;
		Y = y;
		length = (int) StrictMath.ceil((double) P.bitLength() / 128) * 16;
	}

	/**
	 * Compare deux cl�s.
	 *
	 * @param object la cl� � comparer.
	 * @return true si les deux cl�s sont �gales, false sinon.
	 */
	@Override
	public boolean equals(final Object object) {
		final DSAPublicKey key = (DSAPublicKey) object;
		return P.equals(key.P) && Q.equals(key.Q) && G.equals(key.G) && Y.equals(key.Y);
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
	 * Retourne le param�tre y.
	 *
	 * @return le param�tre y.
	 */
	public BigInteger getY() {
		return Y;
	}

	/**
	 * Retourne une valeur de hachage simple pour cette cl�.
	 *
	 * @return la valeur de hachage.
	 */
	@Override
	public int hashCode() {
		return P.hashCode() * Q.hashCode() * Y.hashCode() * G.hashCode();
	}

}
