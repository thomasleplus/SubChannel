package org.leplus.libcrypto;

import java.io.IOException;
import java.io.InputStream;

/**
 * Machine � Hachages Cryptographiques.
 */
public abstract class DigestEngine {

	/**
	 * La taille du buffer utilis� ( en octets).
	 */
	public static final int BUFFER_SIZE = 1024;

	/**
	 * G�n�re le hachage des octets lus jusqu'� pr�sent et remet la machine dans son
	 * �tat initial.
	 *
	 * @return le hachage.
	 */
	public final Digest digest() {
		doPadding();
		final Digest d = doDigest();
		doReset();
		return d;
	}

	/**
	 * G�n�re le hachage des octets lus jusqu'� pr�sent.
	 *
	 * @return le hachage.
	 */
	protected abstract Digest doDigest();

	/**
	 * G�n�re le padding avant la finalisation du hachage.
	 *
	 * @return le nombre d'octets n�cessaires au padding.
	 */
	protected abstract int doPadding();

	/**
	 * Remet la machine dans son �tat initial.
	 */
	protected abstract void doReset();

	/**
	 * Lit un octet.
	 *
	 * @param n l'octet � hacher.
	 */
	protected abstract void doUpdate(byte n);

	/**
	 * Remet la machine dans son �tat initial.
	 */
	public final void reset() {
		doReset();
	}

	/**
	 * Lit un octet.
	 *
	 * @param n l'octet � hacher.
	 */
	public final void update(final byte n) {
		doUpdate(n);
	}

	/**
	 * Lit tous les octets du tableau.
	 *
	 * @param bytes le tableau d'octets.
	 * @return le nombre d'octets lus.
	 */
	public final int update(final byte[] bytes) {
		return update(bytes, 0, bytes.length);
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
		for (int i = 0; i < length; i++) {
			update(bytes[offset + i]);
		}
		return length;
	}

	/**
	 * Lit les octets sur le flot jusqu'� sa fin.
	 *
	 * @param input le flot d'entr�e.
	 * @return le nombre d'octets lus.
	 * @throws IOException si une erreure se produit lors de la lecture du flot.
	 */
	public final int update(final InputStream input) throws IOException {
		int n = 0;
		int p = 0;
		final byte[] buffer = new byte[BUFFER_SIZE];
		while ((p = input.read(buffer)) > 0) {
			n += update(buffer, 0, p);
		}
		return n;
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
		int n = 0;
		int p = 0;
		final byte[] buffer = new byte[BUFFER_SIZE];
		while (n < length && (p = input.read(buffer, 0, StrictMath.min(1024, length - n))) > 0) {
			n += update(buffer, 0, p);
		}
		return n;
	}

}
