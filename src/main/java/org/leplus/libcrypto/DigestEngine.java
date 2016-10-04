/* $Id: DigestEngine.java,v 1.4 2003/04/19 07:55:22 leplusth Exp $ */

package org.leplus.libcrypto;

import java.io.InputStream;
import java.io.IOException;

/**
 * Machine � Hachages Cryptographiques.
 *
 * @version $Revision: 1.4 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class DigestEngine {
	
	/**
	 * La taille du buffer utilis� ( en octets).
	 */
	public static final int BUFFER_SIZE = 1024;
	
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
		int n = 0;
		int p = 0;
		byte[] buffer = new byte[BUFFER_SIZE];
		while ((p = input.read(buffer)) > 0)
			n += update(buffer, 0, p);
		return n;
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
	 * Lit au plus le nombre donn� d'octets du tableau en commen�ant �
	 * l'indice donn�.
	 *
	 * @param bytes le tableau d'octets.
	 * @param offset l'indice de d�part.
	 * @param length le nombre d'octets � lire.
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
	 * @param n l'octet � hacher.
	 */
	public final void update(byte n) {
		doUpdate(n);
	}

	/**
	 * Lit un octet.
	 *
	 * @param n l'octet � hacher.
	 */
	protected abstract void doUpdate(byte n);
	
	/**
	 * G�n�re le hachage des octets lus jusqu'� pr�sent et remet la
	 * machine dans son �tat initial.
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
	 * G�n�re le padding avant la finalisation du hachage.
	 *
	 * @return le nombre d'octets n�cessaires au padding.
	 */
	protected abstract int doPadding();
	
	/**
	 * G�n�re le hachage des octets lus jusqu'� pr�sent.
	 *
	 * @return le hachage.
	 */
	protected abstract Digest doDigest();
	
	/**
	 * Remet la machine dans son �tat initial.
	 */
	public final void reset() {
		doReset();
	}
	
	/**
	 * Remet la machine dans son �tat initial.
	 */
	protected abstract void doReset();
	
}
