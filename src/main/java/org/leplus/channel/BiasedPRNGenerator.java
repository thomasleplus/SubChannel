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

import java.math.BigInteger;

import org.leplus.libcrypto.PRNGenerator;

/**
 * G�n�rateur de Nombres Pseudo-Al�atoires bias�.
 *
 * @version $Revision: 1.3 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
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
