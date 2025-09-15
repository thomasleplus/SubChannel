package org.leplus.libcrypto;

/** Générateur de Pair de Clés Cryptographiques. */
public abstract class KeyPairGenerator {

  /**
   * Génère une nouvelle pair de clés.
   *
   * @return la pair de clés.
   */
  protected abstract KeyPair doGenerateKeyPair();

  /**
   * Retourne la longueur des clés générées (en octets).
   *
   * @return la longueur des clés (en octets).
   */
  protected abstract int doGetLength();

  /**
   * Génère une nouvelle pair de clés.
   *
   * @return la pair de clés.
   */
  public final KeyPair generateKeyPair() {
    return doGenerateKeyPair();
  }

  /**
   * Retourne la longueur des clés générées (en octets).
   *
   * @return la longueur des clés (en octets).
   */
  public final int getLength() {
    return doGetLength();
  }
}
