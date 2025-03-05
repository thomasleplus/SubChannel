package org.leplus.libcrypto;

import java.math.BigInteger;

/**
 * Machine � Signatures DSA.
 *
 * Le <i>Digital Signature Algorithm</i> est d�fini par la norme <a href=
 * "http://csrc.nist.gov/publications/fips/fips186-2/fips186-2-change1.pdf">FIPS
 * 186-2</a> (<i>Digital Signature Scheme</i>) du NIST.
 */
public final class DSASignatureEngine extends SignatureEngine {

	/**
	 * Le g�n�rateur de nombres pseudo-al�atoires.
	 */
	private final PRNGenerator random;

	/**
	 * Construit une machine � signatures DSA.
	 *
	 * @param prng le g�n�rateur de nombres pseudo-al�atoires utilis� par la machine
	 *             de signatures DSA.
	 */
	public DSASignatureEngine(final PRNGenerator prng) {
		random = prng;
		digest = new SHA1DigestEngine();
	}

	/**
	 * G�n�re la signature des octets lus.
	 *
	 * @param digest le hachage du message.
	 * @param key    la cl� priv�e.
	 * @return la signature.
	 */
	@Override
	protected Signature doSign(final Digest digest, final PrivateKey key) {
		final DSAPrivateKey pk = (DSAPrivateKey) key;
		BigInteger k, t, r, s;
		do {
			do {
				k = random.getBigInt(BigInteger.ONE, pk.getQ().subtract(BigInteger.ONE));
				r = pk.getG().modPow(k, pk.getP()).mod(pk.getQ());
				t = digest.getInt().add(pk.getX().multiply(r));
			} while (r.signum() == 0);
			s = k.modInverse(pk.getQ()).multiply(t).mod(pk.getQ());
		} while (s.signum() == 0);
		return new DSASignature(r, s);
	}

	/**
	 * V�rifie la signature des octets lus.
	 *
	 * @param digest    le hachage du message.
	 * @param key       la cl� publique.
	 * @param signature the signature.
	 * @return true si la signature est valide, false sinon.
	 */
	@Override
	protected boolean doVerify(final Digest digest, final PublicKey key, final Signature signature) {
		final DSAPublicKey pk = (DSAPublicKey) key;
		final DSASignature sg = (DSASignature) signature;
		if (sg.getR().signum() <= 0 || sg.getR().compareTo(pk.getQ()) >= 0) {
			return false;
		}
		if (sg.getS().signum() <= 0 || sg.getS().compareTo(pk.getQ()) >= 0) {
			return false;
		}
		final BigInteger w = sg.getS().modInverse(pk.getQ());
		final BigInteger u1 = digest.getInt().multiply(w).mod(pk.getQ());
		final BigInteger u2 = sg.getR().multiply(w).mod(pk.getQ());
		final BigInteger v = pk.getG().modPow(u1, pk.getP()).multiply(pk.getY().modPow(u2, pk.getP())).mod(pk.getP())
				.mod(pk.getQ());
		return v.compareTo(sg.getR()) == 0;
	}

}
