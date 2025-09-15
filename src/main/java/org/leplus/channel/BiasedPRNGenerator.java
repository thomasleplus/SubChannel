package org.leplus.channel;

import java.math.BigInteger;
import org.leplus.libcrypto.PRNGenerator;

/** Générateur de Nombres Pseudo-Aléatoires biasé. */
public final class BiasedPRNGenerator extends PRNGenerator {

  private final BigInteger integer;

  /** Construit le générateur pseudo-aléatoire. */
  public BiasedPRNGenerator(final BigInteger i) {
    integer = i;
  }

  /**
   * Retourne le grand entier fixé.
   *
   * @return le grand entier fixé.
   */
  public BigInteger getBigInt() {
    return integer;
  }

  /**
   * Retourne le grand entier fixé si sa valeur est comprise entre les valeurs données.
   *
   * @param min la valeur minimum de l'entier.
   * @param max la valeur maximum de l'entier.
   * @return le grand entier.
   */
  @Override
  public BigInteger getBigInt(final BigInteger min, final BigInteger max) {
    if (integer.compareTo(min) < 0 || integer.compareTo(max) > 0) {
      throw new IndexOutOfBoundsException();
    }
    return integer;
  }

  /**
   * Retourne le grand entier fixé si sa taille est comprise entre les valeurs données.
   *
   * @param lmin le nombre minimum de bits de l'entier.
   * @param lmax le nombre maximum de bits de l'entier.
   * @return le grand entier.
   */
  @Override
  public BigInteger getBigInt(final int lmin, final int lmax) {
    if (lmin > integer.bitLength() || lmax < integer.bitLength()) {
      throw new IndexOutOfBoundsException();
    }
    return integer;
  }

  /**
   * Retourne 0.
   *
   * @return 0.
   */
  @Override
  public byte getByte() {
    return 0;
  }
}
