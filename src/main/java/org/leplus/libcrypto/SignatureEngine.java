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
 * Machine � Signatures.
 *
 * @version $Revision: 1.3 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class SignatureEngine {
	
	/**
	 * La fonction de hachage.
	 */
	protected DigestEngine digest;
	
	/**
	 * Lit les octets sur le flot jusqu'� sa fin.
	 *
	 * @param input le flot d'entr�e.
	 * @return le nombre d'octets lus.
	 * @throws IOException si une erreure se produit lors de
	 *         la lecture du flot.
	 */
	public final int update(InputStream input)
		throws IOException {
		return digest.update(input);
	}
	
	/**
	 * Lit au plus le nombre donn� d'octets sur le flot.
	 *
	 * @param input le flot d'entr�e.
	 * @param length le nombre d'octets � lire.
	 * @return le nombre d'octets lus.
	 * @throws IOException si une erreure se produit lors de
	 *         la lecture du flot.
	 */
	public final int update(InputStream input, int length)
		throws IOException {
		return digest.update(input, length);
	}
	
	/**
	 * Lit tous les octets du tableau.
	 *
	 * @param bytes le tableau d'octets.
	 * @return le nombre d'octets lus.
	 */
	public final int update(byte[] bytes) {
		return digest.update(bytes, 0, bytes.length);
	}
	
	/**
	 * Lit au plus le nombre donn� d'octets du tableau en commen�ant �
	 * l'indice donn�.
	 *
	 * @param bytes le tableau d'octets.
	 * @param offset l'indice de d�part.
	 * @param length le nombre d'octets � lire.
	 * @return le nombre d'octets lus.
	 */
	public final int update(byte[] bytes, int offset, int length) {
		return digest.update(bytes, offset, length);
	}
	
	/**
	 * G�n�re la signature des octets lus et remet la machine dans son
	 * �tat initial (garde en m�moire le mode g�n�ration ou
	 * v�rification et la cl� correspondante).
	 *
	 * @param key la cl� priv�e.
	 * @return la signature.
	 */
	public final Signature sign(PrivateKey key) {
		return doSign(digest.digest(), key);
	}
	
	/**
	 * G�n�re la signature des octets lus.
	 *
	 * @param digest le hachage du message.
	 * @param key la cl� priv�e.
	 * @return la signature.
	 */
	protected abstract Signature doSign(Digest digest, PrivateKey key);
	
	/**
	 * V�rifie la signature des octets lus et remet la machine dans son
	 * �tat initial (garde en m�moire le mode g�n�ration ou
	 * v�rification et la cl� correspondante).
	 *
	 * @param key la cl� publique.
	 * @param signature the signature.
	 * @return true si la signature est valide, false sinon.
	 */
	public final boolean verify(PublicKey key, Signature signature) {
		return doVerify(digest.digest(), key, signature);
	}
	
	/**
	 * V�rifie la signature des octets lus.
	 *
	 * @param digest le hachage du message.
	 * @param key la cl� publique.
	 * @param signature the signature.
	 * @return true si la signature est valide, false sinon.
	 */
	protected abstract boolean doVerify(Digest digest, PublicKey key, Signature signature);
	
	/**
	 * Remet la machine dans son �tat initial (garde en m�moire le
	 * mode g�n�ration ou v�rification et la cl� correspondante).
	 */
	public final void reset() {
		digest.reset();
	}
	
}
