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

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Hachage Cryptographique.
 *
 * @version $Revision: 1.5 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class Digest {
	
	/**
	 * La valeur du hachage.
	 */
	protected byte[] value;
	
	/**
	 * Retourne la longueur du hachage (en octets).
	 *
	 * @return la longueur du hachage (en octets).
	 */
	public final int getLength() {
		return value.length;
	}
	
	/**
	 * Retourne la valeur du hachage sous forme d'octets.
	 *
	 * @return le tableau d'octets.
	 */
	public final byte[] getBytes() {
		byte[] copy = new byte[value.length];
		System.arraycopy(value, 0, copy, 0, value.length);
		return copy;
	}
	
	/**
	 * Retourne la valeur du hachage sous forme d'un grand entier.
	 *
	 * @return le grand entier.
	 */
	public final BigInteger getInt() {
		return new BigInteger(1, getBytes());
	}
	
	/**
	 * Retourne la représentation héxadécimal du hachage.
	 *
	 * @return la chaîne de caractère.
	 */
	public final String getHex() {
		String s = getInt().toString(16);
		while (s.length() < (getLength() << 1))
			s = "0" + s;
		return s;
	}
	
	/**
	 * Écrit les octets du hachage sur le flot.
	 *
	 * @param output le flot de sortie.
	 * @return le nombre d'octets écrits.
	 * @throws IOException si une erreure se produit lors de
	 *         l'écriture sur le flot.
	 */
	public final int writeBytes(OutputStream output)
		throws IOException {
		byte[] bytes = getBytes();
		output.write(bytes);
		return bytes.length;
	}
	
	/**
	 * Écrit la représentation héxadécimale du hachage sur le flot.
	 *
	 * @param output le flot de sortie.
	 * @return le nombre de caractères écrits.
	 * @throws IOException si une erreure se produit lors de
	 *         l'écriture sur le flot.
	 */
	public final int writeHex(OutputStream output)
		throws IOException {
		String hex = getHex();
		PrintStream print = new PrintStream(output);
		print.print(hex);
		return hex.length();
	}
	
	/**
	 * Écrit la représentation décimale du hachage sur le flot.
	 *
	 * @param output le flot de sortie.
	 * @return le nombre de caractères écrits.
	 * @throws IOException si une erreure se produit lors de
	 *         l'écriture sur le flot.
	 */
	public final int writeInt(OutputStream output)
		throws IOException {
		String dec = getInt().toString();
		PrintStream print = new PrintStream(output);
		print.print(dec);
		return dec.length();
	}
	
	/**
	 * Compare deux hachages.
	 *
	 * @param object le hachage à comparer.
	 * @return true si les deux hachages sont égaux, false sinon.
	 */
	public final boolean equals(Object object) {
		Digest digest = (Digest)object;
		return Arrays.equals(value, digest.value);
	}
	
	/**
	 * Retourne une valeur de hachage simple pour ce hachage.
	 *
	 * @return la valeur de hachage.
	 */
	public final int hashCode() {
		int j = 0;
		for (int i = 0; i < value.length; i++)
			j *= value[i];
		return j;
	}
}
