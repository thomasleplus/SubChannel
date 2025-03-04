package org.leplus.libcrypto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clé Cryptographique.
 */
public abstract class Key implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -430859166740079170L;
	
	/**
	 * La longueur de la clé (en octets).
	 */
	private final int length;

	/**
	 * Retourne la longueur de la clé (en octets).
	 *
	 * @return la longueur de la clé (en octets).
	 */
	public final int getLength() {
		return length;
	}

    public Key(int length) {
        super();
        this.length = length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Key other = (Key) obj;
        return length == other.length;
    }


}
