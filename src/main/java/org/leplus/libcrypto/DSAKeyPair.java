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
import java.security.InvalidKeyException;

/**
 * Pair Clé Privée / Clé Publique DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est défini par la norme
 * <a href="http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS 186-2</a>
 * (<i>Digital Signature Scheme</i>) du NIST.
 *
 * @version $Revision: 1.6 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class DSAKeyPair
	extends KeyPair {
	
	/**
	 * Construit une pair de clés DSA à partir de la clé publique et
	 * de la clé privée.
	 *
	 * @param pvk la clé publique.
	 * @param pbk la clé privée.
	 */
	public DSAKeyPair(DSAPrivateKey pvk, DSAPublicKey pbk) {
		if (!pvk.getP().equals(pbk.getP()))
			throw new RuntimeException();
		if (!pvk.getQ().equals(pbk.getQ()))
			throw new RuntimeException();
		if (!pvk.getG().equals(pbk.getG()))
			throw new RuntimeException();
		prvKey = pvk;
		pubKey = pbk;
		length = pubKey.length;
	}
	
	/**
	 * Retourne le paramètre P des clés.
	 *
	 * @return P.
	 */
	public BigInteger getP() {
		return ((DSAPublicKey)pubKey).getP();
	}
	
	/**
	 * Retourne le paramètre Q des clés.
	 *
	 * @return Q.
	 */
	public BigInteger getQ() {
		return ((DSAPublicKey)pubKey).getQ();
	}
	
	/**
	 * Retourne le paramètre G des clés.
	 *
	 * @return G.
	 */
	public BigInteger getG() {
		return ((DSAPublicKey)pubKey).getG();
	}
	
	/**
	 * Retourne le paramètre X de la clé publique.
	 *
	 * @return X.
	 */
	public BigInteger getX() {
		return ((DSAPrivateKey)prvKey).getX();
	}
	
	/**
	 * Retourne le paramètre Y de la clé privée.
	 *
	 * @return Y.
	 */
	public BigInteger getY() {
		return ((DSAPublicKey)pubKey).getY();
	}
	
	/**
	 * Compare deux pairs de clés.
	 *
	 * @param object la pair à comparer.
	 * @return true si les deux pairs sont égaux, false sinon.
	 */
	public boolean equals(Object object) {
		KeyPair pair = (KeyPair)object;
		return ((DSAPublicKey)pubKey).equals(pair.pubKey)
			&& ((DSAPrivateKey)prvKey).equals(pair.prvKey);
	}
	
	/**
	 * Retourne une valeur de hachage simple pour cette pair de clés.
	 *
	 * @return la valeur de hachage.
	 */
	public int hashCode() {
		return pubKey.hashCode() * prvKey.hashCode();
	}
	
}
