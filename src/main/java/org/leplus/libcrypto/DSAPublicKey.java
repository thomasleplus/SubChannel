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

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Cl� Publique DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est d�fini par la norme
 * <a href="http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS 186-2</a>
 * (<i>Digital Signature Scheme</i>) du NIST.
 *
 * @version $Revision: 1.5 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class DSAPublicKey
	extends PublicKey {
	
	/**
	 * Le param�tre p.
	 */
	private BigInteger P;
	
	/**
	 * Le param�tre q.
	 */
	private BigInteger Q;
	
	/**
	 * Le param�tre g.
	 */
	private BigInteger G;
	
	/**
	 * Le param�tre y.
	 */
	private BigInteger Y;
	
	/**
	 * Construit une cl� publique DSA � partir de ses param�tres.
	 *
	 * @param p le param�tre p.
	 * @param q le param�tre q.
	 * @param g le param�tre g.
	 * @param y le param�tre y.
	 */
	protected DSAPublicKey(BigInteger p, BigInteger q, BigInteger g, BigInteger y) {
		P = p;
		Q = q;
		G = g;
		Y = y;
		length = (int)StrictMath.ceil((double)P.bitLength() / 128) * 16;
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
	 * Retourne le param�tre g.
	 *
	 * @return le param�tre g.
	 */
	public BigInteger getG() {
		return G;
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
	 * Compare deux cl�s.
	 *
	 * @param object la cl� � comparer.
	 * @return true si les deux cl�s sont �gales, false sinon.
	 */
	public boolean equals(Object object) {
		DSAPublicKey key = (DSAPublicKey)object;
		return P.equals(key.P) && Q.equals(key.Q) && G.equals(key.G) && Y.equals(key.Y);
	}
	
	/**
	 * Retourne une valeur de hachage simple pour cette cl�.
	 *
	 * @return la valeur de hachage.
	 */
	public int hashCode() {
		return P.hashCode() * Q.hashCode() * Y.hashCode() * G.hashCode();
	}
	
}
