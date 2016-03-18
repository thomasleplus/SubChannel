/* $Id: JavaPRNGenerator.java,v 1.4 2003/04/19 07:55:22 leplusth Exp $ */

package org.leplus.libcrypto;

import java.util.Random;

/**
 * Générateur de Nombres Pseudo-Aléatoires fournit par SUN Microsystem.
 *
 * @version $Revision: 1.4 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class JavaPRNGenerator
	extends PRNGenerator {
	
	/**
	 * Le générateur pseudo-aléatoire.
	 */
	private final Random random;
	
	/**
	 * Construit le générateur pseudo-aléatoire.
	 */
	public JavaPRNGenerator() {
		random = new Random();
	}
	
	/**
	 * Retourne un bit pseudo-aléatoire.
	 *
	 * @return le bit pseudo-aléatoire.
	 */
	public boolean getBit() {
		return random.nextBoolean();
	}
	
	/**
	 * Retourne un entier court pseudo-aléatoire.
	 *
	 * @return l'entier court pseudo-aléatoire.
	 */
	public byte getByte() {
		byte[] bytes = new byte[1];
		random.nextBytes(bytes);
		return bytes[0];
	}
	
	/**
	 * Retourne un entier pseudo-aléatoire.
	 *
	 * @return l'entier pseudo-aléatoire.
	 */
	public int getInt() {
		return random.nextInt();
	}
	
	/**
	 * Retourne un entier long pseudo-aléatoire.
	 *
	 * @return l'entier long pseudo-aléatoire.
	 */
	public long getLong() {
		return random.nextLong();
	}
	
	/**
	 * Retourne un nombre réel pseudo-aléatoire
	 * compris entre 0 et 1.
	 *
	 * @return le réel pseudo-aléatoire.
	 */
	public float getFloat() {
		return random.nextFloat();
	}
	
	/**
	 * Retourne un nombre réel double précision pseudo-aléatoire
	 * compris entre 0 et 1.
	 *
	 * @return le réel double précision pseudo-aléatoire.
	 */
	public double getDouble() {
		return random.nextDouble();
	}

	/**
	 * Retourne un tableau d'octets aléatoires de la longueur aléatoire.
	 *
	 * @param length la longueur voulue.
	 * @return le tableau d'octets.
	 */
	public byte[] getBytes(int length) {
		byte[] bytes = new byte[length];
		random.nextBytes(bytes);
		return bytes;
	}
	
}
