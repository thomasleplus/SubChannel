package org.leplus.libcrypto;

import java.io.Serializable;

/**
 * Cl� Cryptographique.
 *
 * @version $Revision: 1.6 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class Key implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -430859166740079170L;
	/**
	 * La longueur de la cl� (en octets).
	 */
	protected int length;

	/**
	 * Compare deux cl�s.
	 *
	 * @param object la cl� � comparer.
	 * @return true si les deux cl�s sont �gales, false sinon.
	 */
	@Override
	public abstract boolean equals(Object object);

	/**
	 * Retourne la longueur de la cl� (en octets).
	 *
	 * @return la longueur de la cl� (en octets).
	 */
	public final int getLength() {
		return length;
	}

	/**
	 * Retourne une valeur de hachage simple pour cette cl�.
	 *
	 * @return la valeur de hachage.
	 */
	@Override
	public abstract int hashCode();

}
