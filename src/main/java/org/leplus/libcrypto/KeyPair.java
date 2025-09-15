package org.leplus.libcrypto;

import java.util.Objects;

/** Pair Clé Privée / Clé Publique. */
public abstract class KeyPair extends Key {

  /** */
  private static final long serialVersionUID = 9054280212735531426L;

  /** La clé privée. */
  private final PrivateKey prvKey;

  /** La clé publique. */
  private final PublicKey pubKey;

  /**
   * Retourne la clé privée.
   *
   * @return la clé privée.
   */
  public final PrivateKey getPrivateKey() {
    return prvKey;
  }

  /**
   * Retourne la clé publique.
   *
   * @return la clé publique.
   */
  public final PublicKey getPublicKey() {
    return pubKey;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(prvKey, pubKey);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!super.equals(obj)) return false;
    if (getClass() != obj.getClass()) return false;
    KeyPair other = (KeyPair) obj;
    return Objects.equals(prvKey, other.prvKey) && Objects.equals(pubKey, other.pubKey);
  }

  public KeyPair(final PrivateKey pvk, final PublicKey pbk) {
    super(pbk.getLength());
    prvKey = pvk;
    pubKey = pbk;
  }
}
