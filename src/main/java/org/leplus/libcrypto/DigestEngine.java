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

import java.io.InputStream;
import java.io.IOException;

/**
 * Machine à Hachages Cryptographiques.
 *
 * @version $Revision: 1.4 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class DigestEngine {
	
	/**
	 * La taille du buffer utilisé ( en octets).
	 */
	public static final int BUFFER_SIZE = 1024;
	
	/**
	 * Lit les octets sur le flot jusqu'à sa fin.
	 *
	 * @param input le flot d'entrée.
	 * @return le nombre d'octets lus.
	 * @throws IOException si une erreure se produit lors de
	 *         la lecture du flot.
	 */
	public final int update(InputStream input)
		throws IOException {
		int n = 0;
		int p = 0;
		byte[] buffer = new byte[BUFFER_SIZE];
		while ((p = input.read(buffer)) > 0)
			n += update(buffer, 0, p);
		return n;
	}
	
	/**
	 * Lit au plus le nombre donné d'octets sur le flot.
	 *
	 * @param input le flot d'entrée.
	 * @param length le nombre d'octets à lire.
	 * @return le nombre d'octets lus.
	 * @throws IOException si une erreure se produit lors de
	 *         la lecture du flot.
	 */
	public final int update(InputStream input, int length)
		throws IOException {
		int n = 0;
		int p = 0;
		byte[] buffer = new byte[BUFFER_SIZE];
		while (n < length && (p = input.read(buffer, 0, StrictMath.min(1024, length - n))) > 0)
			n += update(buffer, 0, p);
		return n;
	}
	
	/**
	 * Lit tous les octets du tableau.
	 *
	 * @param bytes le tableau d'octets.
	 * @return le nombre d'octets lus.
	 */
	public final int update(byte[] bytes) {
		return update(bytes, 0, bytes.length);
	}
	
	/**
	 * Lit au plus le nombre donné d'octets du tableau en commençant à
	 * l'indice donné.
	 *
	 * @param bytes le tableau d'octets.
	 * @param offset l'indice de départ.
	 * @param length le nombre d'octets à lire.
	 * @return le nombre d'octets lus.
	 */
	public final int update(byte[] bytes, int offset, int length) {
		for (int i = 0; i < length; i++)
			update(bytes[offset + i]);
		return length;
	}
	
	/**
	 * Lit un octet.
	 *
	 * @param n l'octet à hacher.
	 */
	public final void update(byte n) {
		doUpdate(n);
	}

	/**
	 * Lit un octet.
	 *
	 * @param n l'octet à hacher.
	 */
	protected abstract void doUpdate(byte n);
	
	/**
	 * Génère le hachage des octets lus jusqu'à présent et remet la
	 * machine dans son état initial.
	 *
	 * @return le hachage.
	 */
	public final Digest digest() {
		doPadding();
		Digest d = doDigest();
		doReset();
		return d;
	}
	
	/**
	 * Génère le padding avant la finalisation du hachage.
	 *
	 * @return le nombre d'octets nécessaires au padding.
	 */
	protected abstract int doPadding();
	
	/**
	 * Génère le hachage des octets lus jusqu'à présent.
	 *
	 * @return le hachage.
	 */
	protected abstract Digest doDigest();
	
	/**
	 * Remet la machine dans son état initial.
	 */
	public final void reset() {
		doReset();
	}
	
	/**
	 * Remet la machine dans son état initial.
	 */
	protected abstract void doReset();
	
}
