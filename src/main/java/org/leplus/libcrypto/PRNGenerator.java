/* $Id: PRNGenerator.java,v 1.6 2003/04/19 07:55:22 leplusth Exp $ */

package org.leplus.libcrypto;

import java.math.BigInteger;

/**
 * G�n�rateur de Nombres Pseudo-Al�atoires.
 *
 * @version $Revision: 1.6 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class PRNGenerator {
	
	/**
	 * Produit des nombres premiers impairs jusqu'� 101.
	 */
	private static final BigInteger test = new BigInteger("116431182179248680450031658440253681535");
	
	/**
	 * 2 en BigInteger.
	 */
	private static final BigInteger two = BigInteger.valueOf(2);
	
	/**
	 * Certitude de primalit� par d�faut. La certitude de primalit�
	 * est d�finie comme suit : l'entier retourn� est premier avec une
	 * probabilit� p = (1-1/2<sup>certainty</sup>).
	 */
	public static final int DEFAULT_CERTAINTY = 100;
	
	/**
	 * Retourne un bit pseudo-al�atoire.
	 *
	 * @return le bit pseudo-al�atoire.
	 */
	public boolean getBit() {
		byte b = getByte();
		return ((b & 0x01) ^
				((b & 0x02) >>> 1) ^
				((b & 0x04) >>> 2) ^
				((b & 0x08) >>> 3) ^
				((b & 0x10) >>> 4) ^
				((b & 0x20) >>> 5) ^
				((b & 0x40) >>> 6) ^
				((b & 0x80) >>> 7)) == 0;
	}
	
	/**
	 * Retourne un octet pseudo-al�atoire.
	 *
	 * @return l'octet pseudo-al�atoire.
	 */
	public abstract byte getByte();
	
	/**
	 * Retourne un entier court pseudo-al�atoire.
	 *
	 * @return l'entier court pseudo-al�atoire.
	 */
	public short getShort() {
		return (short)(((short)getByte() << 8) + getByte());
	}

	/**
	 * Retourne un entier pseudo-al�atoire.
	 *
	 * @return l'entier pseudo-al�atoire.
	 */
	public int getInt() {
		return ((int)getShort() << 16) + getShort();
	}
	
	/**
	 * Retourne un entier pseudo-al�atoire dont la valeur est
	 * comprise entre les valeurs donn�es.
	 *
	 * @param min la valeur minimum de l'entier.
	 * @param max la valeur maximum de l'entier.
	 * @return l'entier pseudo-al�atoire.
	 */
	public int getInt(int min, int max) {
		if (min > max)
			throw new IndexOutOfBoundsException();
		if (min == max) return min;
		int d = StrictMath.abs(max - min);
		int n1 = getInt();
		int n2 = getInt();
		int n3 = getInt();
		return ((n1 * n2 + n3) % d) + min;
	}
	
	/**
	 * Retourne un entier long pseudo-al�atoire.
	 *
	 * @return l'entier long pseudo-al�atoire.
	 */
	public long getLong() {
		return ((long)getInt() << 32) + getInt();
	}
	
	/**
	 * Retourne un entier long pseudo-al�atoire dont la valeur est
	 * comprise entre les valeurs donn�es.
	 *
	 * @param min la valeur minimum de l'entier long.
	 * @param max la valeur maximum de l'entier long.
	 * @return l'entier long pseudo-al�atoire.
	 */
	public long getLong(long min, long max) {
		if (min > max)
			throw new IndexOutOfBoundsException();
		if (min == max) return min;
		long d = StrictMath.abs(max - min);
		long n1 = getLong();
		long n2 = getLong();
		long n3 = getLong();
		return ((n1 * n2 + n3) % d) + min;
	}
	
	/**
	 * Retourne un nombre r�el pseudo-al�atoire
	 * compris entre 0 et 1.
	 *
	 * @return le r�el pseudo-al�atoire.
	 */
	public float getFloat() {
		return (getInt() & 0x00FFFFFF) / ((float)(1 << 24));
	}
	
	/**
	 * Retourne un nombre r�el double pr�cision pseudo-al�atoire
	 * compris entre 0 et 1.
	 *
	 * @return le r�el double pr�cision pseudo-al�atoire.
	 */
	public double getDouble() {
		return (((long)(getInt() & 0x03FFFFFF) << 27) + (getInt() & 0x07FFFFFF)) / (double)(1L << 53);
	}

	/**
	 * Retourne un tableau d'octets al�atoires de la longueur al�atoire.
	 *
	 * @param length la longueur voulue.
	 * @return le tableau d'octets.
	 */
	public byte[] getBytes(int length) {
		byte[] bytes = new byte[length];
		for (int i = 0; i < length; i++)
			bytes[i] = getByte();
		return bytes;
	}
	
	/**
	 * Retourne un entier pseudo-al�atoire dont la longueur en bits
	 * est comprise entre les valeurs donn�es.
	 *
	 * @param lmin le nombre minimum de bits de l'entier.
	 * @param lmax le nombre maximum de bits de l'entier.
	 * @return le grand entier pseudo-al�atoire.
	 */
	public BigInteger getBigInt(int lmin, int lmax) {
		if (lmin < 0 || lmin > lmax)
			throw new IndexOutOfBoundsException();
		if (lmax == 0)
			return BigInteger.ZERO;
		if (lmin == 0)
			return getBigInt(BigInteger.ZERO, two.pow(lmax).subtract(BigInteger.ONE));
 		return getBigInt(two.pow(lmin-1), two.pow(lmax).subtract(BigInteger.ONE));
	}
	
	/**
	 * Retourne un entier pseudo-al�atoire dont la valeur
	 * est comprise entre les valeurs donn�es.
	 *
	 * @param min la valeur minimum de l'entier.
	 * @param max la valeur maximum de l'entier.
	 * @return le grand entier pseudo-al�atoire.
	 */
	public BigInteger getBigInt(BigInteger min, BigInteger max) {
		int t = min.compareTo(max);
		if (t > 0)
			throw new IndexOutOfBoundsException();
		if (t == 0)
			return min;
		BigInteger d = max.subtract(min).add(BigInteger.ONE).abs();
		BigInteger n1 = new BigInteger(1, getBytes((int)StrictMath.ceil((double)d.bitLength() / 8)));
		BigInteger n2 = new BigInteger(1, getBytes((int)StrictMath.ceil((double)d.bitLength() / 8)));
		BigInteger n3 = new BigInteger(1, getBytes((int)StrictMath.ceil((double)d.bitLength() / 8)));
		return n1.multiply(n2).add(n3).mod(d).add(min);
	}
	
	/**
	 * Retourne un grand nombre premier pseudo-al�atoire de la
	 * longueur donn�e.
	 *
	 * @param lmin le nombre minimum de bits du nombre premier.
	 * @param lmax le nombre maximum de bits du nombre premier.
	 * @param certainty la certitude voulu sur la primalit� : l'entier
	 *                  retourn� est premier avec une probabilit�
	 *                  p = (1-1/2<sup>certainty</sup>).
	 * @return le grand nombre premier pseudo-al�atoire ou null si
	 * aucun nombre premier n'est trouv� dans l'interval voulu (ce qui
	 * ne veut pas n�cessairement dire qu'il n'y en a pas).
	 */
	public BigInteger getBigPrime(int lmin, int lmax, int certainty) {
		if (lmin < 0 || lmin > lmax)
			throw new IndexOutOfBoundsException();
		if (lmax < 2)
			return null;
		if (lmin == 0)
			return getBigPrime(BigInteger.ZERO, two.pow(lmax).subtract(BigInteger.ONE), certainty);
 		return getBigPrime(two.pow(lmin-1), two.pow(lmax).subtract(BigInteger.ONE), certainty);
	}
	
	/**
	 * Retourne un grand nombre premier pseudo-al�atoire de la
	 * longueur donn�e.
	 *
	 * @param lmin le nombre minimum de bits du nombre premier.
	 * @param lmax le nombre maximum de bits du nombre premier.
	 * @return le grand nombre premier pseudo-al�atoire ou null si
	 * aucun nombre premier n'est trouv� dans l'interval voulu (ce qui
	 * ne veut pas n�cessairement dire qu'il n'y en a pas).
	 */
	public BigInteger getBigPrime(int lmin, int lmax) {
 		return getBigPrime(lmin, lmax, DEFAULT_CERTAINTY);
	}
	
	/**
	 * Retourne un grand nombre premier pseudo-al�atoire compris
	 * entre les valeurs donn�es.
	 *
	 * @param min la valeur minimum du nombre premier.
	 * @param max le valeur maximum du nombre premier.
	 * @param certainty la certitude voulu sur la primalit� : l'entier
	 *                  retourn� est premier avec une probabilit�
	 *                  p = (1-1/2<sup>certainty</sup>).
	 * @return le grand nombre premier pseudo-al�atoire ou null si
	 * aucun nombre premier n'est trouv� dans l'interval voulu (ce qui
	 * ne veut pas n�cessairement dire qu'il n'y en a pas).
	 */
	public BigInteger getBigPrime(BigInteger min, BigInteger max, int certainty) {
		if (min.signum() < 0 || min.compareTo(max) > 0)
			throw new IndexOutOfBoundsException();
		if (min.compareTo(max) == 0) {
			if (min.isProbablePrime(certainty)) return min;
			else return null;
		}
		int t = max.compareTo(two);
		if (t == 0) return two;
		if (t < 0) return null;
		if (min.compareTo(two) < 0) min = two;
		for (int i = 0; i < 1000000; i++) {
			BigInteger n = getBigInt(min, max);
			if (n.compareTo(two) == 0) return n;
			n = n.setBit(0);
			if (n.gcd(test).compareTo(BigInteger.ONE) != 0) continue;
			if (n.isProbablePrime(certainty)) return n;
		}
		return null;
	}
	
	/**
	 * Retourne un grand nombre premier pseudo-al�atoire compris
	 * entre les valeurs donn�es (avec une certitude de primalit� � 100).
	 *
	 * @param min la valeur minimum du nombre premier.
	 * @param max le valeur maximum du nombre premier.
	 * @return le grand nombre premier pseudo-al�atoire ou null si
	 * aucun nombre premier n'est trouv� dans l'interval voulu (ce qui
	 * ne veut pas n�cessairement dire qu'il n'y en a pas).
	 */
	public BigInteger getBigPrime(BigInteger min, BigInteger max) {
 		return getBigPrime(min, max, DEFAULT_CERTAINTY);
	}
	
}
