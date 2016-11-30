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

/**
 * Générateur de Pair de Clés Cryptographiques.
 *
 * @version $Revision: 1.3 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class KeyPairGenerator {
	
	/**
	 * Retourne la longueur des clés générées (en octets).
	 *
	 * @return la longueur des clés (en octets).
	 */
	public final int getLength() {
		return doGetLength();
	}
	
	/**
	 * Retourne la longueur des clés générées (en octets).
	 *
	 * @return la longueur des clés (en octets).
	 */
	protected abstract int doGetLength();
	
	/**
	 * Génère une nouvelle pair de clés.
	 *
	 * @return la pair de clés.
	 */
	public final KeyPair generateKeyPair() {
		return doGenerateKeyPair();
	}
	
	/**
	 * Génère une nouvelle pair de clés.
	 *
	 * @return la pair de clés.
	 */
	protected abstract KeyPair doGenerateKeyPair();
	
}
