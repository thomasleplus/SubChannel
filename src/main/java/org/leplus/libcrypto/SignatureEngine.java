package org.leplus.libcrypto;

import java.io.IOException;
import java.io.InputStream;

/**
 * Machine à Signatures.
 */
public abstract class SignatureEngine {

	/**
	 * La fonction de hachage.
	 */
	protected DigestEngine digest;

	/**
	 * Génère la signature des octets lus.
	 *
	 * @param digest le hachage du message.
	 * @param key    la clé privée.
	 * @return la signature.
	 */
	protected abstract Signature doSign(Digest digest, PrivateKey key);

	/**
	 * Vérifie la signature des octets lus.
	 *
	 * @param digest    le hachage du message.
	 * @param key       la clé publique.
	 * @param signature the signature.
	 * @return true si la signature est valide, false sinon.
	 */
	protected abstract boolean doVerify(Digest digest, PublicKey key, Signature signature);

	/**
	 * Remet la machine dans son état initial (garde en mémoire le mode génération
	 * ou vérification et la clé correspondante).
	 */
	public final void reset() {
		digest.reset();
	}

	/**
	 * Génère la signature des octets lus et remet la machine dans son état initial
	 * (garde en mémoire le mode génération ou vérification et la clé
	 * correspondante).
	 *
	 * @param key la clé privée.
	 * @return la signature.
	 */
	public final Signature sign(final PrivateKey key) {
		return doSign(digest.digest(), key);
	}

	/**
	 * Lit tous les octets du tableau.
	 *
	 * @param bytes le tableau d'octets.
	 * @return le nombre d'octets lus.
	 */
	public final int update(final byte[] bytes) {
		return digest.update(bytes, 0, bytes.length);
	}

	/**
	 * Lit au plus le nombre donné d'octets du tableau en commençant à l'indice
	 * donné.
	 *
	 * @param bytes  le tableau d'octets.
	 * @param offset l'indice de départ.
	 * @param length le nombre d'octets à lire.
	 * @return le nombre d'octets lus.
	 */
	public final int update(final byte[] bytes, final int offset, final int length) {
		return digest.update(bytes, offset, length);
	}

	/**
	 * Lit les octets sur le flot jusqu'à sa fin.
	 *
	 * @param input le flot d'entrée.
	 * @return le nombre d'octets lus.
	 * @throws IOException si une erreure se produit lors de la lecture du flot.
	 */
	public final int update(final InputStream input) throws IOException {
		return digest.update(input);
	}

	/**
	 * Lit au plus le nombre donné d'octets sur le flot.
	 *
	 * @param input  le flot d'entrée.
	 * @param length le nombre d'octets à lire.
	 * @return le nombre d'octets lus.
	 * @throws IOException si une erreure se produit lors de la lecture du flot.
	 */
	public final int update(final InputStream input, final int length) throws IOException {
		return digest.update(input, length);
	}

	/**
	 * Vérifie la signature des octets lus et remet la machine dans son état initial
	 * (garde en mémoire le mode génération ou vérification et la clé
	 * correspondante).
	 *
	 * @param key       la clé publique.
	 * @param signature the signature.
	 * @return true si la signature est valide, false sinon.
	 */
	public final boolean verify(final PublicKey key, final Signature signature) {
		return doVerify(digest.digest(), key, signature);
	}

}
