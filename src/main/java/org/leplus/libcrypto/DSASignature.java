/*
 * SubChannel - A study on subliminal channels in DSA algorithm.
 * Copyright (C) 2016 Thomas Leplus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.leplus.libcrypto;

import java.math.BigInteger;

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

	/**
	 * Compare deux signatures.
	 *
	 * @param object la signature � comparer.
	 * @return true si les deux signatures sont �gales, false sinon.
	 */
	@Override
	public boolean equals(final Object object) {
		final DSASignature signature = (DSASignature) object;
		return R.equals(signature.R) && S.equals(signature.S);
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
	 * Retourne une valeur de hachage simple pour cette signature.
	 *
	 * @return la valeur de hachage.
	 */
	@Override
	public int hashCode() {
		return R.hashCode() * S.hashCode();
	}

}
