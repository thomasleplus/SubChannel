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
 * Signature Cryptographique DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est défini par la norme
 * <a href="http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS 186-2</a>
 * (<i>Digital Signature Scheme</i>) du NIST.
 *
 * @version $Revision: 1.6 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class DSASignature
	extends Signature {
	
	/**
	 * La longueur en octets des signatures DSA.
	 */
	public static final int LENGTH = 20;
	
	/**
	 * La longueur en octets des signatures DSA encodées.
	 */
	public static final int ENCODED_LENGTH = 46;
	
	/**
	 * Le paramètre r.
	 */
	private BigInteger R;
	
	/**
	 * Le paramètre s.
	 */
	private BigInteger S;
	
	/**
	 * Construit une signature DSA à partir de ses paramètres.
	 *
	 * @param r le paramètre r.
	 * @param s le paramètre s.
	 */
	protected DSASignature(BigInteger r, BigInteger s) {
		R = r;
		S = s;
		length = LENGTH;
	}
	
	/**
	 * Retourne le paramètre R de la signature.
	 *
	 * @return le paramètre R.
	 */
	public BigInteger getR() {
		return R;
	}
	
	/**
	 * Retourne le paramètre S de la signature.
	 *
	 * @return le paramètre S.
	 */
	public BigInteger getS() {
		return S;
	}
	
	/**
	 * Retourne la longueur de la signature (en octets).
	 *
	 * @return la longueur de la signature (en octets).
	 */
	protected int doGetLength() {
		return LENGTH;
	}
	
	/**
	 * Compare deux signatures.
	 *
	 * @param object la signature à comparer.
	 * @return true si les deux signatures sont égales, false sinon.
	 */
	public boolean equals(Object object) {
		DSASignature signature = (DSASignature)object;
		return R.equals(signature.R) && S.equals(signature.S);
	}
	
	/**
	 * Retourne une valeur de hachage simple pour cette signature.
	 *
	 * @return la valeur de hachage.
	 */
	public int hashCode() {
		return R.hashCode() * S.hashCode();
	}
	
}
