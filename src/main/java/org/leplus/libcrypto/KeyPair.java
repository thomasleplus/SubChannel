package org.leplus.libcrypto;

import java.util.Objects;

/**
 * Pair Cl� Priv�e / Cl� Publique.
 */
public abstract class KeyPair extends Key {

	/**
	 *
	 */
	private static final long serialVersionUID = 9054280212735531426L;

	/**
	 * La cl� priv�e.
	 */
	private final PrivateKey prvKey;

	/**
	 * La cl� publique.
	 */
	private final PublicKey pubKey;

	/**
	 * Retourne la cl� priv�e.
	 *
	 * @return la cl� priv�e.
	 */
	public final PrivateKey getPrivateKey() {
		return prvKey;
	}

	/**
	 * Retourne la cl� publique.
	 *
	 * @return la cl� publique.
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
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        KeyPair other = (KeyPair) obj;
        return Objects.equals(prvKey, other.prvKey)
                && Objects.equals(pubKey, other.pubKey);
    }

    public KeyPair(final PrivateKey pvk, final PublicKey pbk) {
        super(pbk.getLength());
        prvKey = pvk;
        pubKey = pbk;
    }

}
