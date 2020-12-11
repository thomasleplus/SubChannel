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
		if (!pvk.getP().equals(pbk.getP())) {
			throw new RuntimeException();
		}
		if (!pvk.getQ().equals(pbk.getQ())) {
			throw new RuntimeException();
		}
		if (!pvk.getG().equals(pbk.getG())) {
			throw new RuntimeException();
		}
		prvKey = pvk;
		pubKey = pbk;
		length = pubKey.length;
	}

	/**
	 * Compare deux pairs de cl�s.
	 *
	 * @param object la pair � comparer.
	 * @return true si les deux pairs sont �gaux, false sinon.
	 */
	@Override
	public boolean equals(final Object object) {
		final KeyPair pair = (KeyPair) object;
		return ((DSAPublicKey) pubKey).equals(pair.pubKey) && ((DSAPrivateKey) prvKey).equals(pair.prvKey);
	}

	/**
	 * Retourne le param�tre G des cl�s.
	 *
	 * @return G.
	 */
	public BigInteger getG() {
		return ((DSAPublicKey) pubKey).getG();
	}

	/**
	 * Retourne le param�tre P des cl�s.
	 *
	 * @return P.
	 */
	public BigInteger getP() {
		return ((DSAPublicKey) pubKey).getP();
	}

	/**
	 * Retourne le param�tre Q des cl�s.
	 *
	 * @return Q.
	 */
	public BigInteger getQ() {
		return ((DSAPublicKey) pubKey).getQ();
	}

	/**
	 * Retourne le param�tre X de la cl� publique.
	 *
	 * @return X.
	 */
	public BigInteger getX() {
		return ((DSAPrivateKey) prvKey).getX();
	}

	/**
	 * Retourne le param�tre Y de la cl� priv�e.
	 *
	 * @return Y.
	 */
	public BigInteger getY() {
		return ((DSAPublicKey) pubKey).getY();
	}

	/**
	 * Retourne une valeur de hachage simple pour cette pair de cl�s.
	 *
	 * @return la valeur de hachage.
	 */
	@Override
	public int hashCode() {
		return pubKey.hashCode() * prvKey.hashCode();
	}

}
