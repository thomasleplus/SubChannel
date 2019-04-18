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

package org.leplus.channel;

import org.leplus.libcrypto.PRNGenerator;

import java.math.BigInteger;

/**
 * Générateur de Nombres Pseudo-Aléatoires biasé.
 *
 * @version $Revision: 1.3 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class BiasedPRNGenerator
	extends PRNGenerator {
	
	private final BigInteger integer;
	
	/**
	 * Construit le générateur pseudo-aléatoire.
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
	 * Retourne le grand entier fixé.
	 *
	 * @return le grand entier fixé.
	 */
	public BigInteger getBigInt() {
		return integer;
	}
		
	/**
	 * Retourne le grand entier fixé si sa taille est comprise entre les valeurs données.
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
	 * Retourne le grand entier fixé si sa valeur est comprise entre les valeurs données.
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
