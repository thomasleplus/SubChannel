package org.leplus.channel;

import java.math.BigInteger;

import org.leplus.libcrypto.PRNGenerator;

/**
 * G�n�rateur de Nombres Pseudo-Al�atoires bias�.
 */
public final class BiasedPRNGenerator extends PRNGenerator {

	private final BigInteger integer;

	/**
	 * Construit le g�n�rateur pseudo-al�atoire.
	 */
	public BiasedPRNGenerator(final BigInteger i) {
		integer = i;
	}

	/**
	 * Retourne le grand entier fix�.
	 *
	 * @return le grand entier fix�.
	 */
	public BigInteger getBigInt() {
		return integer;
	}

	/**
	 * Retourne le grand entier fix� si sa valeur est comprise entre les valeurs
	 * donn�es.
	 *
	 * @param min la valeur minimum de l'entier.
	 * @param max la valeur maximum de l'entier.
	 * @return le grand entier.
	 */
	@Override
	public BigInteger getBigInt(final BigInteger min, final BigInteger max) {
		if (integer.compareTo(min) < 0 || integer.compareTo(max) > 0) {
			throw new IndexOutOfBoundsException();
		}
		return integer;
	}

	/**
	 * Retourne le grand entier fix� si sa taille est comprise entre les valeurs
	 * donn�es.
	 *
	 * @param lmin le nombre minimum de bits de l'entier.
	 * @param lmax le nombre maximum de bits de l'entier.
	 * @return le grand entier.
	 */
	@Override
	public BigInteger getBigInt(final int lmin, final int lmax) {
		if (lmin > integer.bitLength() || lmax < integer.bitLength()) {
			throw new IndexOutOfBoundsException();
		}
		return integer;
	}

	/**
	 * Retourne 0.
	 *
	 * @return 0.
	 */
	@Override
	public byte getByte() {
		return 0;
	}

}
