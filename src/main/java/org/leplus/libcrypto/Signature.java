package org.leplus.libcrypto;

import java.io.Serializable;

/**
 * Signature Cryptographique.
 *
 * @version $Revision: 1.6 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class Signature implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8464504173456723420L;
	/**
	 * La longueur de la signature (en octets).
	 */
	protected int length;

	/**
	 * Retourne la longueur de la signature (en octets).
	 *
	 * @return la longueur de la signature (en octets).
	 */
	protected abstract int doGetLength();

	/**
	 * Compare deux signatures.
	 *
	 * @param object la signature � comparer.
	 * @return true si les deux signatures sont �gales, false sinon.
	 */
	@Override
	public abstract boolean equals(Object object);

	/**
	 * Retourne la longueur de la signature (en octets).
	 *
	 * @return la longueur de la signature (en octets).
	 */
	public final int getLength() {
		return length;
	}

	/**
	 * Retourne une valeur de hachage simple pour cette signature.
	 *
	 * @return la valeur de hachage.
	 */
	@Override
	public abstract int hashCode();

}
