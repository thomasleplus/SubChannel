/* $Id: BiasedPRNGenerator.java,v 1.3 2003/04/19 07:55:22 leplusth Exp $ */

package org.leplus.channel;

import org.leplus.libcrypto.PRNGenerator;

import java.math.BigInteger;

/**
 * G�n�rateur de Nombres Pseudo-Al�atoires bias�.
 *
 * @version $Revision: 1.3 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class BiasedPRNGenerator
	extends PRNGenerator {
	
	private final BigInteger integer;
	
	/**
	 * Construit le g�n�rateur pseudo-al�atoire.
	 */
	public BiasedPRNGenerator(BigInteger i) {
		integer = i;
	}
	
	/**
	 * Retourne 0.
	 *
	 * @return 0.
	 */
	public byte getByte() {
		return 0;
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
	 * Retourne le grand entier fix� si sa taille est comprise entre les valeurs donn�es.
	 *
	 * @param lmin le nombre minimum de bits de l'entier.
	 * @param lmax le nombre maximum de bits de l'entier.
	 * @return le grand entier.
	 */
	public final BigInteger getBigInt(int lmin, int lmax) {
		if (lmin > integer.bitLength() || lmax < integer.bitLength())
			throw new IndexOutOfBoundsException();
 		return integer;
	}
	
	/**
	 * Retourne le grand entier fix� si sa valeur est comprise entre les valeurs donn�es.
	 *
	 * @param min la valeur minimum de l'entier.
	 * @param max la valeur maximum de l'entier.
	 * @return le grand entier.
	 */
	public BigInteger getBigInt(BigInteger min, BigInteger max) {
		if (integer.compareTo(min) < 0 || integer.compareTo(max) > 0)
			throw new IndexOutOfBoundsException();
		return integer;
	}
	
}
