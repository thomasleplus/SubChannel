package org.leplus.libcrypto;

import java.math.BigInteger;

/** Générateur de Nombres Pseudo-Aléatoires. */
public abstract class PRNGenerator {

  /** Produit des nombres premiers impairs jusqu'à 101. */
  private static final BigInteger test = new BigInteger("116431182179248680450031658440253681535");

  /** 2 en BigInteger. */
  private static final BigInteger two = BigInteger.valueOf(2);

  /**
   * Certitude de primalité par défaut. La certitude de primalité est définie comme suit : l'entier
   * retourné est premier avec une probabilité p = (1-1/2<sup>certainty</sup>).
   */
  public static final int DEFAULT_CERTAINTY = 100;

  /**
   * Retourne un entier pseudo-aléatoire dont la valeur est comprise entre les valeurs données.
   *
   * @param min la valeur minimum de l'entier.
   * @param max la valeur maximum de l'entier.
   * @return le grand entier pseudo-aléatoire.
   */
  public BigInteger getBigInt(final BigInteger min, final BigInteger max) {
    final int t = min.compareTo(max);
    if (t > 0) {
      throw new IndexOutOfBoundsException();
    }
    if (t == 0) {
      return min;
    }
    final BigInteger d = max.subtract(min).add(BigInteger.ONE).abs();
    final BigInteger n1 =
        new BigInteger(1, getBytes((int) StrictMath.ceil((double) d.bitLength() / 8)));
    final BigInteger n2 =
        new BigInteger(1, getBytes((int) StrictMath.ceil((double) d.bitLength() / 8)));
    final BigInteger n3 =
        new BigInteger(1, getBytes((int) StrictMath.ceil((double) d.bitLength() / 8)));
    return n1.multiply(n2).add(n3).mod(d).add(min);
  }

  /**
   * Retourne un entier pseudo-aléatoire dont la longueur en bits est comprise entre les valeurs
   * données.
   *
   * @param lmin le nombre minimum de bits de l'entier.
   * @param lmax le nombre maximum de bits de l'entier.
   * @return le grand entier pseudo-aléatoire.
   */
  public BigInteger getBigInt(final int lmin, final int lmax) {
    if (lmin < 0 || lmin > lmax) {
      throw new IndexOutOfBoundsException();
    }
    if (lmax == 0) {
      return BigInteger.ZERO;
    }
    if (lmin == 0) {
      return getBigInt(BigInteger.ZERO, two.pow(lmax).subtract(BigInteger.ONE));
    }
    return getBigInt(two.pow(lmin - 1), two.pow(lmax).subtract(BigInteger.ONE));
  }

  /**
   * Retourne un grand nombre premier pseudo-aléatoire compris entre les valeurs données (avec une
   * certitude de primalité à 100).
   *
   * @param min la valeur minimum du nombre premier.
   * @param max le valeur maximum du nombre premier.
   * @return le grand nombre premier pseudo-aléatoire ou null si aucun nombre premier n'est trouvé
   *     dans l'interval voulu (ce qui ne veut pas nécessairement dire qu'il n'y en a pas).
   */
  public BigInteger getBigPrime(final BigInteger min, final BigInteger max) {
    return getBigPrime(min, max, DEFAULT_CERTAINTY);
  }

  /**
   * Retourne un grand nombre premier pseudo-aléatoire compris entre les valeurs données.
   *
   * @param min la valeur minimum du nombre premier.
   * @param max le valeur maximum du nombre premier.
   * @param certainty la certitude voulu sur la primalité : l'entier retourné est premier avec une
   *     probabilité p = (1-1/2<sup>certainty</sup>).
   * @return le grand nombre premier pseudo-aléatoire ou null si aucun nombre premier n'est trouvé
   *     dans l'interval voulu (ce qui ne veut pas nécessairement dire qu'il n'y en a pas).
   */
  public BigInteger getBigPrime(BigInteger min, final BigInteger max, final int certainty) {
    if (min.signum() < 0 || min.compareTo(max) > 0) {
      throw new IndexOutOfBoundsException();
    }
    if (min.compareTo(max) == 0) {
      if (min.isProbablePrime(certainty)) {
        return min;
      } else {
        return null;
      }
    }
    final int t = max.compareTo(two);
    if (t == 0) {
      return two;
    }
    if (t < 0) {
      return null;
    }
    if (min.compareTo(two) < 0) {
      min = two;
    }
    for (int i = 0; i < 1000000; i++) {
      BigInteger n = getBigInt(min, max);
      if (n.compareTo(two) == 0) {
        return n;
      }
      n = n.setBit(0);
      if (n.gcd(test).compareTo(BigInteger.ONE) != 0) {
        continue;
      }
      if (n.isProbablePrime(certainty)) {
        return n;
      }
    }
    return null;
  }

  /**
   * Retourne un grand nombre premier pseudo-aléatoire de la longueur donnée.
   *
   * @param lmin le nombre minimum de bits du nombre premier.
   * @param lmax le nombre maximum de bits du nombre premier.
   * @return le grand nombre premier pseudo-aléatoire ou null si aucun nombre premier n'est trouvé
   *     dans l'interval voulu (ce qui ne veut pas nécessairement dire qu'il n'y en a pas).
   */
  public BigInteger getBigPrime(final int lmin, final int lmax) {
    return getBigPrime(lmin, lmax, DEFAULT_CERTAINTY);
  }

  /**
   * Retourne un grand nombre premier pseudo-aléatoire de la longueur donnée.
   *
   * @param lmin le nombre minimum de bits du nombre premier.
   * @param lmax le nombre maximum de bits du nombre premier.
   * @param certainty la certitude voulu sur la primalité : l'entier retourné est premier avec une
   *     probabilité p = (1-1/2<sup>certainty</sup>).
   * @return le grand nombre premier pseudo-aléatoire ou null si aucun nombre premier n'est trouvé
   *     dans l'interval voulu (ce qui ne veut pas nécessairement dire qu'il n'y en a pas).
   */
  public BigInteger getBigPrime(final int lmin, final int lmax, final int certainty) {
    if (lmin < 0 || lmin > lmax) {
      throw new IndexOutOfBoundsException();
    }
    if (lmax < 2) {
      return null;
    }
    if (lmin == 0) {
      return getBigPrime(BigInteger.ZERO, two.pow(lmax).subtract(BigInteger.ONE), certainty);
    }
    return getBigPrime(two.pow(lmin - 1), two.pow(lmax).subtract(BigInteger.ONE), certainty);
  }

  /**
   * Retourne un bit pseudo-aléatoire.
   *
   * @return le bit pseudo-aléatoire.
   */
  public boolean getBit() {
    final byte b = getByte();
    return (b & 0x01
            ^ (b & 0x02) >>> 1
            ^ (b & 0x04) >>> 2
            ^ (b & 0x08) >>> 3
            ^ (b & 0x10) >>> 4
            ^ (b & 0x20) >>> 5
            ^ (b & 0x40) >>> 6
            ^ (b & 0x80) >>> 7)
        == 0;
  }

  /**
   * Retourne un octet pseudo-aléatoire.
   *
   * @return l'octet pseudo-aléatoire.
   */
  public abstract byte getByte();

  /**
   * Retourne un tableau d'octets aléatoires de la longueur aléatoire.
   *
   * @param length la longueur voulue.
   * @return le tableau d'octets.
   */
  public byte[] getBytes(final int length) {
    final byte[] bytes = new byte[length];
    for (int i = 0; i < length; i++) {
      bytes[i] = getByte();
    }
    return bytes;
  }

  /**
   * Retourne un nombre réel double précision pseudo-aléatoire compris entre 0 et 1.
   *
   * @return le réel double précision pseudo-aléatoire.
   */
  public double getDouble() {
    return (((long) (getInt() & 0x03FFFFFF) << 27) + (getInt() & 0x07FFFFFF)) / (double) (1L << 53);
  }

  /**
   * Retourne un nombre réel pseudo-aléatoire compris entre 0 et 1.
   *
   * @return le réel pseudo-aléatoire.
   */
  public float getFloat() {
    return (getInt() & 0x00FFFFFF) / (float) (1 << 24);
  }

  /**
   * Retourne un entier pseudo-aléatoire.
   *
   * @return l'entier pseudo-aléatoire.
   */
  public int getInt() {
    return (getShort() << 16) + getShort();
  }

  /**
   * Retourne un entier pseudo-aléatoire dont la valeur est comprise entre les valeurs données.
   *
   * @param min la valeur minimum de l'entier.
   * @param max la valeur maximum de l'entier.
   * @return l'entier pseudo-aléatoire.
   */
  public int getInt(final int min, final int max) {
    return getBigInt(BigInteger.valueOf(min), BigInteger.valueOf(max)).intValue();
  }

  /**
   * Retourne un entier long pseudo-aléatoire.
   *
   * @return l'entier long pseudo-aléatoire.
   */
  public long getLong() {
    return ((long) getInt() << 32) + getInt();
  }

  /**
   * Retourne un entier long pseudo-aléatoire dont la valeur est comprise entre les valeurs données.
   *
   * @param min la valeur minimum de l'entier long.
   * @param max la valeur maximum de l'entier long.
   * @return l'entier long pseudo-aléatoire.
   */
  public long getLong(final long min, final long max) {
    return getBigInt(BigInteger.valueOf(min), BigInteger.valueOf(max)).longValue();
  }

  /**
   * Retourne un entier court pseudo-aléatoire.
   *
   * @return l'entier court pseudo-aléatoire.
   */
  public short getShort() {
    return (short) ((getByte() << 8) + (getByte() & 0xff));
  }
}
