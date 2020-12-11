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
 * Cl� Priv�e DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est d�fini par la norme <a href=
 * "http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS
 * 186-2</a> (<i>Digital Signature Scheme</i>) du NIST.
 *
 * @version $Revision: 1.6 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class DSAPrivateKey extends PrivateKey {

	/**
	 *
	 */
	private static final long serialVersionUID = -6744540637294417567L;

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
	 * Le param�tre x.
	 */
	private final BigInteger X;

	/**
	 * Construit une cl� priv�e DSA � partir de ses param�tres.
	 *
	 * @param p le param�tre p.
	 * @param q le param�tre q.
	 * @param g le param�tre g.
	 * @param x le param�tre x.
	 */
	protected DSAPrivateKey(final BigInteger p, final BigInteger q, final BigInteger g, final BigInteger x) {
		P = p;
		Q = q;
		G = g;
		X = x;
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
		final DSAPrivateKey key = (DSAPrivateKey) object;
		return P.equals(key.P) && Q.equals(key.Q) && G.equals(key.G) && X.equals(key.X);
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
	 * Retourne le param�tre x.
	 *
	 * @return le param�tre x.
	 */
	public BigInteger getX() {
		return X;
	}

	/**
	 * Retourne une valeur de hachage simple pour cette cl�.
	 *
	 * @return la valeur de hachage.
	 */
	@Override
	public int hashCode() {
		return P.hashCode() * Q.hashCode() * G.hashCode() * X.hashCode();
	}

}
