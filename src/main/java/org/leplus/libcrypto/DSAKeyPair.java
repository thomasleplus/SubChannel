package org.leplus.libcrypto;

import java.math.BigInteger;

/**
 * Pair Cl� Priv�e / Cl� Publique DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est d�fini par la norme <a href=
 * "http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS
 * 186-2</a> (<i>Digital Signature Scheme</i>) du NIST.
 *
 * @version $Revision: 1.6 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class DSAKeyPair extends KeyPair {

	/**
	 *
	 */
	private static final long serialVersionUID = 4535068078804905828L;

	/**
	 * Construit une pair de cl�s DSA � partir de la cl� publique et de la cl�
	 * priv�e.
	 *
	 * @param pvk la cl� publique.
	 * @param pbk la cl� priv�e.
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
	 * Retourne le param�tre G des cl�s.
	 *
	 * @return G.
	 */
	public BigInteger getG() {
		return ((DSAPublicKey) getPublicKey()).getG();
	}

	/**
	 * Retourne le param�tre P des cl�s.
	 *
	 * @return P.
	 */
	public BigInteger getP() {
		return ((DSAPublicKey) getPublicKey()).getP();
	}

	/**
	 * Retourne le param�tre Q des cl�s.
	 *
	 * @return Q.
	 */
	public BigInteger getQ() {
		return ((DSAPublicKey) getPublicKey()).getQ();
	}

	/**
	 * Retourne le param�tre X de la cl� publique.
	 *
	 * @return X.
	 */
	public BigInteger getX() {
		return ((DSAPrivateKey) getPrivateKey()).getX();
	}

	/**
	 * Retourne le param�tre Y de la cl� priv�e.
	 *
	 * @return Y.
	 */
	public BigInteger getY() {
		return ((DSAPublicKey) getPublicKey()).getY();
	}

}
