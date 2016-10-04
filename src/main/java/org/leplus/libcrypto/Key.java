/* $Id: Key.java,v 1.6 2003/04/19 07:55:22 leplusth Exp $ */

package org.leplus.libcrypto;

import java.io.Serializable;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Clé Cryptographique.
 *
 * @version $Revision: 1.6 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class Key
	implements Serializable {
	
	/**
	 * La longueur de la clé (en octets).
	 */
	protected int length;
	
	/**
	 * Retourne la longueur de la clé (en octets).
	 *
	 * @return la longueur de la clé (en octets).
	 */
	public final int getLength() {
		return length;
	}
	
	/**
	 * Compare deux clés.
	 *
	 * @param object la clé à comparer.
	 * @return true si les deux clés sont égales, false sinon.
	 */
	public abstract boolean equals(Object object);
	
	/**
	 * Retourne une valeur de hachage simple pour cette clé.
	 *
	 * @return la valeur de hachage.
	 */
	public abstract int hashCode();
	
}
