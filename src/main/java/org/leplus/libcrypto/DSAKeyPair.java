package org.leplus.libcrypto;

import java.math.BigInteger;

/**
 * Pair Clé Privée / Clé Publique DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est défini par la norme
 * <a href="https://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">
 * FIPS186-2</a> (<i>Digital Signature Scheme</i>) du NIST.
 */
public final class DSAKeyPair extends KeyPair {

	/**
	 *
	 */
	private static final long serialVersionUID = 4535068078804905828L;

	/**
	 * Construit une pair de clés DSA à partir de la clé publique et de la clé
	 * privée.
	 *
	 * @param pvk la clé publique.
	 * @param pbk la clé privée.
	 */
	public DSAKeyPair(final DSAPrivateKey pvk, final DSAPublicKey pbk) {
	    super(pvk, pbk);
        if (!pvk.getP().equals(pbk.getP())
                || !pvk.getQ().equals(pbk.getQ())
                || !pvk.getG().equals(pbk.getG())) {
            throw new IllegalArgumentException("Inconsistent keys");
        }
	}

	@Override
    @SuppressWarnings("PMD.UselessOverridingMethod")
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }

	/**
	 * Retourne le paramêtre G des clés.
	 *
	 * @return G.
	 */
	public BigInteger getG() {
		return ((DSAPublicKey) getPublicKey()).getG();
	}

	/**
	 * Retourne le paramêtre P des clés.
	 *
	 * @return P.
	 */
	public BigInteger getP() {
		return ((DSAPublicKey) getPublicKey()).getP();
	}

	/**
	 * Retourne le paramêtre Q des clés.
	 *
	 * @return Q.
	 */
	public BigInteger getQ() {
		return ((DSAPublicKey) getPublicKey()).getQ();
	}

	/**
	 * Retourne le paramêtre X de la clé publique.
	 *
	 * @return X.
	 */
	public BigInteger getX() {
		return ((DSAPrivateKey) getPrivateKey()).getX();
	}

	/**
	 * Retourne le paramêtre Y de la clé privée.
	 *
	 * @return Y.
	 */
	public BigInteger getY() {
		return ((DSAPublicKey) getPublicKey()).getY();
	}

}
