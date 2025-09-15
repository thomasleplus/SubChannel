package org.leplus.libcrypto;

/** Clé Publique. */
public abstract class PublicKey extends Key {

  public PublicKey(int length) {
    super(length);
  }

  /** */
  private static final long serialVersionUID = 6621426086644699156L;

  @Override
  @SuppressWarnings("PMD.UselessOverridingMethod")
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!super.equals(obj)) return false;
    if (getClass() != obj.getClass()) return false;
    return true;
  }
}
