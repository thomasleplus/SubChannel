package org.leplus.libcrypto;

/**
 * Cl� Publique.
 *
 * @version $Revision: 1.3 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class PublicKey extends Key {

	public PublicKey(int length) {
        super(length);
    }

    /**
	 *
	 */
	private static final long serialVersionUID = 6621426086644699156L;

    @Override
    @SuppressWarnings("PMD.UselessOverridingMethod")
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }
}
