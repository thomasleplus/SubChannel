package org.leplus.libcrypto;

import java.io.IOException;
import java.io.InputStream;

/**
 * Machine � Signatures.
 *
 * @version $Revision: 1.3 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class SignatureEngine {

	/**
	 * La fonction de hachage.
	 */
	protected DigestEngine digest;

	/**
	 * G�n�re la signature des octets lus.
	 *
	 * @param digest le hachage du message.
	 * @param key    la cl� priv�e.
	 * @return la signature.
	 */
	protected abstract Signature doSign(Digest digest, PrivateKey key);

	/**
	 * V�rifie la signature des octets lus.
	 *
	 * @param digest    le hachage du message.
	 * @param key       la cl� publique.
	 * @param signature the signature.
	 * @return true si la signature est valide, false sinon.
	 */
	protected abstract boolean doVerify(Digest digest, PublicKey key, Signature signature);

	/**
	 * Remet la machine dans son �tat initial (garde en m�moire le mode g�n�ration
	 * ou v�rification et la cl� correspondante).
	 */
	public final void reset() {
		digest.reset();
	}

	/**
	 * G�n�re la signature des octets lus et remet la machine dans son �tat initial
	 * (garde en m�moire le mode g�n�ration ou v�rification et la cl�
	 * correspondante).
	 *
	 * @param key la cl� priv�e.
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
	 * Lit au plus le nombre donn� d'octets du tableau en commen�ant � l'indice
	 * donn�.
	 *
	 * @param bytes  le tableau d'octets.
	 * @param offset l'indice de d�part.
	 * @param length le nombre d'octets � lire.
	 * @return le nombre d'octets lus.
	 */
	public final int update(final byte[] bytes, final int offset, final int length) {
		return digest.update(bytes, offset, length);
	}

	/**
	 * Lit les octets sur le flot jusqu'� sa fin.
	 *
	 * @param input le flot d'entr�e.
	 * @return le nombre d'octets lus.
	 * @throws IOException si une erreure se produit lors de la lecture du flot.
	 */
	public final int update(final InputStream input) throws IOException {
		return digest.update(input);
	}

	/**
	 * Lit au plus le nombre donn� d'octets sur le flot.
	 *
	 * @param input  le flot d'entr�e.
	 * @param length le nombre d'octets � lire.
	 * @return le nombre d'octets lus.
	 * @throws IOException si une erreure se produit lors de la lecture du flot.
	 */
	public final int update(final InputStream input, final int length) throws IOException {
		return digest.update(input, length);
	}

	/**
	 * V�rifie la signature des octets lus et remet la machine dans son �tat initial
	 * (garde en m�moire le mode g�n�ration ou v�rification et la cl�
	 * correspondante).
	 *
	 * @param key       la cl� publique.
	 * @param signature the signature.
	 * @return true si la signature est valide, false sinon.
	 */
	public final boolean verify(final PublicKey key, final Signature signature) {
		return doVerify(digest.digest(), key, signature);
	}

}
