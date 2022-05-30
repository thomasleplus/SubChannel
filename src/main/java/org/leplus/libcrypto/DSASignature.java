package org.leplus.libcrypto;

import java.math.BigInteger;
import java.util.Objects;

/**
 * Signature Cryptographique DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est d�fini par la norme <a href=
 * "http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS
 * 186-2</a> (<i>Digital Signature Scheme</i>) du NIST.
 *
 * @version $Revision: 1.6 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class DSASignature extends Signature {

	/**
	 *
	 */
	private static final long serialVersionUID = 4361487488030607401L;

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
	private final BigInteger R;

	/**
	 * Le param�tre s.
	 */
	private final BigInteger S;

	/**
	 * Construit une signature DSA � partir de ses param�tres.
	 *
	 * @param r le param�tre r.
	 * @param s le param�tre s.
	 */
	protected DSASignature(final BigInteger r, final BigInteger s) {
		R = r;
		S = s;
		length = LENGTH;
	}

	/**
	 * Retourne la longueur de la signature (en octets).
	 *
	 * @return la longueur de la signature (en octets).
	 */
	@Override
	protected int doGetLength() {
		return LENGTH;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DSASignature other = (DSASignature) obj;
        return Objects.equals(R, other.R) && Objects.equals(S, other.S);
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

	@Override
    public int hashCode() {
        return Objects.hash(R, S);
    }

}
