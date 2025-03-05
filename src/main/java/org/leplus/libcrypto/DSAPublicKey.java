package org.leplus.libcrypto;

import java.math.BigInteger;
import java.util.Objects;

/**
 * Cl� Publique DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est d�fini par la norme <a href=
 * "http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS
 * 186-2</a> (<i>Digital Signature Scheme</i>) du NIST.
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
        super((int) StrictMath.ceil((double) p.bitLength() / 128) * 16);
		P = p;
		Q = q;
		G = g;
		Y = y;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        DSAPublicKey other = (DSAPublicKey) obj;
        return Objects.equals(G, other.G) && Objects.equals(P, other.P)
                && Objects.equals(Q, other.Q) && Objects.equals(Y, other.Y);
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

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(G, P, Q, Y);
        return result;
    }

}
