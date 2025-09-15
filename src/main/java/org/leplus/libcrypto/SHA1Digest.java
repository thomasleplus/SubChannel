package org.leplus.libcrypto;

/**
 * Hachage Cryptographique SHA-1.
 *
 * <p>Le <i>Secure Hash Algorithm</i> est défini par la norme <a
 * href="https://www.csrc.nist.gov/publications/fips/fips180-2/fips180-2.pdf">FIPS180-2</a>
 * (<i>Secure Hash Standard</i>) du NIST.
 */
public final class SHA1Digest extends Digest {

  /** La longueur du hachage (en octets). */
  public static final int LENGTH = 20;

  /**
   * Construit un hachage SHA-1.
   *
   * @param bytes la valeur du hachage.
   */
  protected SHA1Digest(final byte[] bytes) {
    value = bytes;
  }
}
