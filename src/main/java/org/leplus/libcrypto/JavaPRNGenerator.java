package org.leplus.libcrypto;

import java.util.Random;

/**
 * G�n�rateur de Nombres Pseudo-Al�atoires fournit par SUN Microsystem.
 *
 * @version $Revision: 1.4 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class JavaPRNGenerator extends PRNGenerator {

	/**
	 * Le g�n�rateur pseudo-al�atoire.
	 */
	private final Random random;

	/**
	 * Construit le g�n�rateur pseudo-al�atoire.
	 */
	public JavaPRNGenerator() {
		random = new Random();
	}

	/**
	 * Retourne un bit pseudo-al�atoire.
	 *
	 * @return le bit pseudo-al�atoire.
	 */
	@Override
	public boolean getBit() {
		return random.nextBoolean();
	}

	/**
	 * Retourne un entier court pseudo-al�atoire.
	 *
	 * @return l'entier court pseudo-al�atoire.
	 */
	@Override
	public byte getByte() {
		final byte[] bytes = new byte[1];
		random.nextBytes(bytes);
		return bytes[0];
	}

	/**
	 * Retourne un tableau d'octets al�atoires de la longueur al�atoire.
	 *
	 * @param length la longueur voulue.
	 * @return le tableau d'octets.
	 */
	@Override
	public byte[] getBytes(final int length) {
		final byte[] bytes = new byte[length];
		random.nextBytes(bytes);
		return bytes;
	}

	/**
	 * Retourne un nombre r�el double pr�cision pseudo-al�atoire compris entre 0 et
	 * 1.
	 *
	 * @return le r�el double pr�cision pseudo-al�atoire.
	 */
	@Override
	public double getDouble() {
		return random.nextDouble();
	}

	/**
	 * Retourne un nombre r�el pseudo-al�atoire compris entre 0 et 1.
	 *
	 * @return le r�el pseudo-al�atoire.
	 */
	@Override
	public float getFloat() {
		return random.nextFloat();
	}

	/**
	 * Retourne un entier pseudo-al�atoire.
	 *
	 * @return l'entier pseudo-al�atoire.
	 */
	@Override
	public int getInt() {
		return random.nextInt();
	}

	/**
	 * Retourne un entier long pseudo-al�atoire.
	 *
	 * @return l'entier long pseudo-al�atoire.
	 */
	@Override
	public long getLong() {
		return random.nextLong();
	}

}
