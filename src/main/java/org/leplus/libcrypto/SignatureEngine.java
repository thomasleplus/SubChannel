/* $Id: SignatureEngine.java,v 1.3 2003/04/19 07:55:23 leplusth Exp $ */

package org.leplus.libcrypto;

import java.io.InputStream;
import java.io.IOException;

/**
 * Machine à Signatures.
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
	 * Lit les octets sur le flot jusqu'à sa fin.
	 *
	 * @param input le flot d'entrée.
	 * @return le nombre d'octets lus.
	 * @throws IOException si une erreure se produit lors de
	 *         la lecture du flot.
	 */
	public final int update(InputStream input)
		throws IOException {
		return digest.update(input);
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
	 * Lit au plus le nombre donné d'octets du tableau en commençant à
	 * l'indice donné.
	 *
	 * @param bytes le tableau d'octets.
	 * @param offset l'indice de départ.
	 * @param length le nombre d'octets à lire.
	 * @return le nombre d'octets lus.
	 */
	public final int update(byte[] bytes, int offset, int length) {
		return digest.update(bytes, offset, length);
	}
	
	/**
	 * Génère la signature des octets lus et remet la machine dans son
	 * état initial (garde en mémoire le mode génération ou
	 * vérification et la clé correspondante).
	 *
	 * @param key la clé privée.
	 * @return la signature.
	 */
	public final Signature sign(PrivateKey key) {
		return doSign(digest.digest(), key);
	}
	
	/**
	 * Génère la signature des octets lus.
	 *
	 * @param digest le hachage du message.
	 * @param key la clé privée.
	 * @return la signature.
	 */
	protected abstract Signature doSign(Digest digest, PrivateKey key);
	
	/**
	 * Vérifie la signature des octets lus et remet la machine dans son
	 * état initial (garde en mémoire le mode génération ou
	 * vérification et la clé correspondante).
	 *
	 * @param key la clé publique.
	 * @param signature the signature.
	 * @return true si la signature est valide, false sinon.
	 */
	public final boolean verify(PublicKey key, Signature signature) {
		return doVerify(digest.digest(), key, signature);
	}
	
	/**
	 * Vérifie la signature des octets lus.
	 *
	 * @param digest le hachage du message.
	 * @param key la clé publique.
	 * @param signature the signature.
	 * @return true si la signature est valide, false sinon.
	 */
	protected abstract boolean doVerify(Digest digest, PublicKey key, Signature signature);
	
	/**
	 * Remet la machine dans son état initial (garde en mémoire le
	 * mode génération ou vérification et la clé correspondante).
	 */
	public final void reset() {
		digest.reset();
	}
	
}
