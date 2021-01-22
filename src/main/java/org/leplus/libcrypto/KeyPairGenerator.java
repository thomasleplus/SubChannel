package org.leplus.libcrypto;

/**
 * G�n�rateur de Pair de Cl�s Cryptographiques.
 *
 * @version $Revision: 1.3 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class KeyPairGenerator {

	/**
	 * G�n�re une nouvelle pair de cl�s.
	 *
	 * @return la pair de cl�s.
	 */
	protected abstract KeyPair doGenerateKeyPair();

	/**
	 * Retourne la longueur des cl�s g�n�r�es (en octets).
	 *
	 * @return la longueur des cl�s (en octets).
	 */
	protected abstract int doGetLength();

	/**
	 * G�n�re une nouvelle pair de cl�s.
	 *
	 * @return la pair de cl�s.
	 */
	public final KeyPair generateKeyPair() {
		return doGenerateKeyPair();
	}

	/**
	 * Retourne la longueur des cl�s g�n�r�es (en octets).
	 *
	 * @return la longueur des cl�s (en octets).
	 */
	public final int getLength() {
		return doGetLength();
	}

}
