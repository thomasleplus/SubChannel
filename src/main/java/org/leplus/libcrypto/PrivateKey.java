package org.leplus.libcrypto;

/**
 * Cl� Priv�e.
 *
 * @version $Revision: 1.3 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class PrivateKey extends Key {

	public PrivateKey(int length) {
        super(length);
    }

    /**
	 *
	 */
	private static final long serialVersionUID = -2033029705071751526L;

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
