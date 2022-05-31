package org.leplus.libcrypto;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Hachage Cryptographique.
 *
 * @version $Revision: 1.5 $
 * @author Thomas Leplus
 *         &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public abstract class Digest {

	/**
	 * La valeur du hachage.
	 */
	protected byte[] value;

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Digest other = (Digest) obj;
        return Arrays.equals(value, other.value);
    }

	/**
	 * Retourne la valeur du hachage sous forme d'octets.
	 *
	 * @return le tableau d'octets.
	 */
	public final byte[] getBytes() {
		final byte[] copy = new byte[value.length];
		System.arraycopy(value, 0, copy, 0, value.length);
		return copy;
	}

	/**
	 * Retourne la repr�sentation h�xad�cimal du hachage.
	 *
	 * @return la cha�ne de caract�re.
	 */
	public final String getHex() {
		String s = getInt().toString(16);
		while (s.length() < getLength() << 1) {
			s = "0" + s;
		}
		return s;
	}

	/**
	 * Retourne la valeur du hachage sous forme d'un grand entier.
	 *
	 * @return le grand entier.
	 */
	public final BigInteger getInt() {
		return new BigInteger(1, getBytes());
	}

	/**
	 * Retourne la longueur du hachage (en octets).
	 *
	 * @return la longueur du hachage (en octets).
	 */
	public final int getLength() {
		return value.length;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(value);
        return result;
    }

	/**
	 * �crit les octets du hachage sur le flot.
	 *
	 * @param output le flot de sortie.
	 * @return le nombre d'octets �crits.
	 * @throws IOException si une erreure se produit lors de l'�criture sur le flot.
	 */
	public final int writeBytes(final OutputStream output) throws IOException {
		final byte[] bytes = getBytes();
		output.write(bytes);
		return bytes.length;
	}

	/**
	 * �crit la repr�sentation h�xad�cimale du hachage sur le flot.
	 *
	 * @param output le flot de sortie.
	 * @return le nombre de caract�res �crits.
	 * @throws IOException si une erreure se produit lors de l'�criture sur le flot.
	 */
	public final int writeHex(final OutputStream output) throws IOException {
		final String hex = getHex();
		final PrintStream print = new PrintStream(output, true, StandardCharsets.UTF_8.name());
		print.print(hex);
		return hex.length();
	}

	/**
	 * �crit la repr�sentation d�cimale du hachage sur le flot.
	 *
	 * @param output le flot de sortie.
	 * @return le nombre de caract�res �crits.
	 * @throws IOException si une erreure se produit lors de l'�criture sur le flot.
	 */
	public final int writeInt(final OutputStream output) throws IOException {
		final String dec = getInt().toString();
		final PrintStream print = new PrintStream(output, true, StandardCharsets.UTF_8.name());
		print.print(dec);
		return dec.length();
	}
}
