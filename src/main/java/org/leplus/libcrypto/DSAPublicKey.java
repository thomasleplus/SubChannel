package org.leplus.libcrypto;

import java.math.BigInteger;
import java.util.Objects;

/**
 * Clé Publique DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est défini par la norme
 * <a href="https://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">
 * FIPS186-2</a> (<i>Digital Signature Scheme</i>) du NIST.
 */
public final class DSAPublicKey extends PublicKey {

	/**
	 *
	 */
	private static final long serialVersionUID = -3734011439289082463L;

	/**
	 * Le paramêtre p.
	 */
	private final BigInteger P;

	/**
	 * Le paramêtre q.
	 */
	private final BigInteger Q;

	/**
	 * Le paramêtre g.
	 */
	private final BigInteger G;

	/**
	 * Le paramêtre y.
	 */
	private final BigInteger Y;

	/**
	 * Construit une clé publique DSA à partir de ses paramêtres.
	 *
	 * @param p le paramêtre p.
	 * @param q le paramêtre q.
	 * @param g le paramêtre g.
	 * @param y le paramêtre y.
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
	 * Retourne le paramêtre g.
	 *
	 * @return le paramêtre g.
	 */
	public BigInteger getG() {
		return G;
	}

	/**
	 * Retourne le paramêtre p.
	 *
	 * @return le paramêtre p.
	 */
	public BigInteger getP() {
		return P;
	}

	/**
	 * Retourne le paramêtre q.
	 *
	 * @return le paramêtre q.
	 */
	public BigInteger getQ() {
		return Q;
	}

	/**
	 * Retourne le paramêtre y.
	 *
	 * @return le paramêtre y.
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
