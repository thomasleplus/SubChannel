package org.leplus.libcrypto;

/**
 * Pair Cl� Priv�e / Cl� Publique.
 *
 * @version $Revision: 1.3 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class KeyPair extends Key {

	/**
	 *
	 */
	private static final long serialVersionUID = 9054280212735531426L;

	/**
	 * La cl� priv�e.
	 */
	protected PrivateKey prvKey;

	/**
	 * La cl� publique.
	 */
	protected PublicKey pubKey;

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

}
