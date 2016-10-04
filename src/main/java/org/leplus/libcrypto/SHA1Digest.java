/* $Id: SHA1Digest.java,v 1.5 2003/04/19 07:55:22 leplusth Exp $ */

package org.leplus.libcrypto;

/**
 * Hachage Cryptographique SHA-1.
 *
 * Le <i>Secure Hash Algorithm</i> est défini par la norme
 * <a href="http://www.csrc.nist.gov/publications/fips/fips180-2/fips180-2.pdf">FIPS 180-2</a>
 * (<i>Secure Hash Standard</i>) du NIST.
 *
 * @version $Revision: 1.5 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class SHA1Digest
	extends Digest {
	
	/**
	 * La longueur du hachage (en octets).
	 */
	public static final int LENGTH = 20;
	
	/**
	 * Construit un hachage SHA-1.
	 *
	 * @param bytes la valeur du hachage.
	 */
	protected SHA1Digest(byte[] bytes) {
		value = bytes;
	}
	
}
