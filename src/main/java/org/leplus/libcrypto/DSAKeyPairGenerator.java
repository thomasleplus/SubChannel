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
import java.security.InvalidParameterException;

/**
 * G�n�rateur de Pair de Cl�s Cryptographiques DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est d�fini par la norme <a href=
 * "http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS
 * 186-2</a> (<i>Digital Signature Scheme</i>) du NIST.
 *
 * @version $Revision: 1.7 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class DSAKeyPairGenerator extends KeyPairGenerator {

	/**
	 * 2 en BigInteger.
	 */
	private static final BigInteger two = BigInteger.valueOf(2);

	/**
	 * Pour un Q choisit al�atoirement, LIMIT est le nombre de tentatives pour
	 * trouver P tel que Q divise P-1 avant de changer de Q.
	 */
	private static final int LIMIT = 4096;

	/**
	 * La longueur des cl�s g�n�r�es (en octets).
	 */
	private final int length;

	/**
	 * La certitude du teste de primalit� .
	 */
	private final int certainty;

	/**
	 * Le g�n�rateur de nombres al�atoires.
	 */
	private final PRNGenerator random;

	/**
	 * Construit un g�n�rateur de pairs de cl�s DSA de 1024 bits (avec une certitude
	 * de primalit� � 100).
	 *
	 * @param prng le g�n�rateur de nombres pseudo-al�atoires � utiliser.
	 */
	public DSAKeyPairGenerator(final PRNGenerator prng) throws InvalidParameterException {
		random = prng;
		length = 128;
		certainty = 100;
	}

	/**
	 * Construit un g�n�rateur de pairs de cl�s DSA.
	 *
	 * @param prng le g�n�rateur de nombres pseudo-al�atoires � utiliser.
	 * @param t    le param�tre de s�curit� T compris entre 0 et 8 (la taille des
	 *             cl�s sera 512+64T bits). <b>T > 3 est fortement recommend� depuis
	 *             1996.</b>
	 * @param c    la certitude voulu sur la primalit� des entiers g�n�r�s: les
	 *             entiers sont premiers avec une probabilit� p =
	 *             (1-1/2<sup>c</sup>).
	 * @throws InvalidParameterException si t est inf�rieur � 0 ou sup�rieur � 8.
	 */
	public DSAKeyPairGenerator(final PRNGenerator prng, final int t, final int c) throws InvalidParameterException {
		if (t < 0 || t > 8) {
			throw new InvalidParameterException("T < 0 || T > 8");
		}
		random = prng;
		length = 64 + 8 * t;
		certainty = c;
	}

	/**
	 * G�n�re une nouvelle pair de cl�s.
	 *
	 * @return la pair de cl�s.
	 */
	@Override
	protected KeyPair doGenerateKeyPair() {
		BigInteger p;
		BigInteger q;
		int counter;
		do {
			p = two;
			q = random.getBigPrime(160, 160, certainty);
			for (counter = 0; counter < LIMIT; counter++) {
				final BigInteger t = random.getBigInt(length << 3, length << 3);
				p = t.subtract(t.mod(two.multiply(q))).add(BigInteger.ONE);
				if (p.bitLength() == length << 3 && p.isProbablePrime(certainty)) {
					break;
				}
			}
		} while (counter == LIMIT);
		BigInteger g;
		do {
			g = random.getBigInt(two, p.subtract(two)).modPow(p.subtract(BigInteger.ONE).divide(q),
					p); /* FIXME: Why is g a generator ? */
		} while (g.compareTo(BigInteger.ONE) == 0);
		final BigInteger x = random.getBigInt(BigInteger.ONE, q.subtract(BigInteger.ONE));
		final BigInteger y = g.modPow(x, p);
		final DSAPrivateKey pvk = new DSAPrivateKey(p, q, g, x);
		final DSAPublicKey pbk = new DSAPublicKey(p, q, g, y);
		return new DSAKeyPair(pvk, pbk);
	}

	/**
	 * Retourne la longueur en octets des cl�s g�n�r�es (en octets).
	 *
	 * @return la longueur des cl�s (en octets).
	 */
	@Override
	protected int doGetLength() {
		return length;
	}

}
