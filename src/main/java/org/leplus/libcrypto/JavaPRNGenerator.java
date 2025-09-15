package org.leplus.libcrypto;

import java.security.SecureRandom;
import java.util.Random;

/** Générateur de Nombres Pseudo-Aléatoires fournit par SUN Microsystem. */
public final class JavaPRNGenerator extends PRNGenerator {

  /** Le générateur pseudo-aléatoire. */
  private final Random random;

  /** Construit le générateur pseudo-aléatoire. */
  public JavaPRNGenerator() {
    random = new SecureRandom();
  }

  /**
   * Retourne un bit pseudo-aléatoire.
   *
   * @return le bit pseudo-aléatoire.
   */
  @Override
  public boolean getBit() {
    return random.nextBoolean();
  }

  /**
   * Retourne un entier court pseudo-aléatoire.
   *
   * @return l'entier court pseudo-aléatoire.
   */
  @Override
  public byte getByte() {
    final byte[] bytes = new byte[1];
    random.nextBytes(bytes);
    return bytes[0];
  }

  /**
   * Retourne un tableau d'octets aléatoires de la longueur aléatoire.
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
   * Retourne un nombre réel double précision pseudo-aléatoire compris entre 0 et 1.
   *
   * @return le réel double précision pseudo-aléatoire.
   */
  @Override
  public double getDouble() {
    return random.nextDouble();
  }

  /**
   * Retourne un nombre réel pseudo-aléatoire compris entre 0 et 1.
   *
   * @return le réel pseudo-aléatoire.
   */
  @Override
  public float getFloat() {
    return random.nextFloat();
  }

  /**
   * Retourne un entier pseudo-aléatoire.
   *
   * @return l'entier pseudo-aléatoire.
   */
  @Override
  public int getInt() {
    return random.nextInt();
  }

  /**
   * Retourne un entier long pseudo-aléatoire.
   *
   * @return l'entier long pseudo-aléatoire.
   */
  @Override
  public long getLong() {
    return random.nextLong();
  }
}
