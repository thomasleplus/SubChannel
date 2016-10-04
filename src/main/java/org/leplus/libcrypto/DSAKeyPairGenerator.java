/* $Id: DSAKeyPairGenerator.java,v 1.7 2003/04/19 07:55:22 leplusth Exp $ */

package org.leplus.libcrypto;

import java.security.InvalidParameterException;
import java.math.BigInteger;

/**
 * Générateur de Pair de Clés Cryptographiques DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est défini par la norme
 * <a href="http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS 186-2</a>
 * (<i>Digital Signature Scheme</i>) du NIST.
 *
 * @version $Revision: 1.7 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class DSAKeyPairGenerator
	extends KeyPairGenerator {

	/**
	 * 2 en BigInteger.
	 */
	private static final BigInteger two = BigInteger.valueOf(2);

	/**
	 * Pour un Q choisit aléatoirement, LIMIT est le nombre de
	 * tentatives pour trouver P tel que Q divise P-1 avant de changer
	 * de Q.
	 */
	private static final int LIMIT = 4096;
	
	/**
	 * La longueur des clés générées (en octets).
	 */
	private final int length;
	
	/**
	 * La certitude du teste de primalité .
	 */
	private final int certainty;
	
	/**
	 * Le générateur de nombres aléatoires.
	 */
	private final PRNGenerator random;
	
	/**
	 * Construit un générateur de pairs de clés DSA.
	 *
	 * @param prng le générateur de nombres pseudo-aléatoires à utiliser.
	 * @param t le paramètre de sécurité T compris entre 0 et 8 (la
	 *          taille des clés sera 512+64T bits). <b>T > 3 est
	 *          fortement recommendé depuis 1996.</b>
	 * @param c la certitude voulu sur la primalité des entiers générés:
	 *          les entiers sont premiers avec une probabilité
	 *          p = (1-1/2<sup>c</sup>).
	 * @throws InvalidParameterException si t est inférieur à 0 ou
	 *                                   supérieur à 8.
	 */
	public DSAKeyPairGenerator(PRNGenerator prng, int t, int c)
		throws InvalidParameterException {
		if (t < 0 || t > 8)
			throw new InvalidParameterException("T < 0 || T > 8");
		random = prng;
		length = 64 + 8 * t;
		certainty = c;
	}
	
	/**
	 * Construit un générateur de pairs de clés DSA de 1024 bits
	 * (avec une certitude de primalité à 100).
	 *
	 * @param prng le générateur de nombres pseudo-aléatoires à utiliser.
	 */
	public DSAKeyPairGenerator(PRNGenerator prng)
		throws InvalidParameterException {
		random = prng;
		length = 128;
		certainty = 100;
	}
	
	/**
	 * Retourne la longueur en octets des clés générées (en octets).
	 *
	 * @return la longueur des clés (en octets).
	 */
	protected int doGetLength() {
		return length;
	}
	
	/**
	 * Génère une nouvelle pair de clés.
	 *
	 * @return la pair de clés.
	 */
	protected KeyPair doGenerateKeyPair() {
		BigInteger p;
		BigInteger q;
		int counter;
		do {
			p = two;
			q = random.getBigPrime(160, 160, certainty);
			for (counter = 0; counter < LIMIT; counter++) {
				BigInteger t = random.getBigInt(length << 3, length << 3);
				p = t.subtract(t.mod(two.multiply(q))).add(BigInteger.ONE);
				if (p.bitLength() == (length << 3) && p.isProbablePrime(certainty)) break;
			}
		} while (counter == LIMIT);
		BigInteger g;
		do g = random.getBigInt(two, p.subtract(two)).modPow(p.subtract(BigInteger.ONE).divide(q), p); /* FIXME: Why is g a generator ? */
		while (g.compareTo(BigInteger.ONE) == 0);
		BigInteger x = random.getBigInt(BigInteger.ONE, q.subtract(BigInteger.ONE));
		BigInteger y = g.modPow(x, p);
		DSAPrivateKey pvk = new DSAPrivateKey(p, q, g, x);
		DSAPublicKey pbk = new DSAPublicKey(p, q, g, y);
		return new DSAKeyPair(pvk, pbk);
	}
	
}
