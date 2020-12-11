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

import java.io.Serializable;

/**
 * Cl� Cryptographique.
 *
 * @version $Revision: 1.6 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class Key implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -430859166740079170L;
	/**
	 * La longueur de la cl� (en octets).
	 */
	protected int length;

	/**
	 * Compare deux cl�s.
	 *
	 * @param object la cl� � comparer.
	 * @return true si les deux cl�s sont �gales, false sinon.
	 */
	@Override
	public abstract boolean equals(Object object);

	/**
	 * Retourne la longueur de la cl� (en octets).
	 *
	 * @return la longueur de la cl� (en octets).
	 */
	public final int getLength() {
		return length;
	}

	/**
	 * Retourne une valeur de hachage simple pour cette cl�.
	 *
	 * @return la valeur de hachage.
	 */
	@Override
	public abstract int hashCode();

}
