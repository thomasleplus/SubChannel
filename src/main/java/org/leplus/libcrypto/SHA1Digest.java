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
 * Hachage Cryptographique SHA-1.
 *
 * Le <i>Secure Hash Algorithm</i> est défini par la norme
 * <a href="http://www.csrc.nist.gov/publications/fips/fips180-2/fips180-2.pdf">FIPS 180-2</a>
 * (<i>Secure Hash Standard</i>) du NIST.
 *
 * @version $Revision: 1.5 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class SHA1Digest
	extends Digest {
	
	/**
	 * La longueur du hachage (en octets).
	 */
	public static final int LENGTH = 20;
	
	/**
	 * Construit un hachage SHA-1.
	 *
	 * @param bytes la valeur du hachage.
	 */
	protected SHA1Digest(byte[] bytes) {
		value = bytes;
	}
	
}
