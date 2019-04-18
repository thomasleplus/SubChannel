/*
 * SubChannel - A study on subliminal channels in DSA algorithm.
 * Copyright (C) 2016 Thomas Leplus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.leplus.libcrypto;

import java.io.InputStream;
import java.io.IOException;

/**
 * Machine à Hachages Cryptographiques SHA-1.
 *
 * Le <i>Secure Hash Algorithm</i> est défini par la norme
 * <a href="http://www.csrc.nist.gov/publications/fips/fips180-2/fips180-2.pdf">FIPS 180-2</a>
 * (<i>Secure Hash Standard</i>) du NIST.
 *
 * @version $Revision: 1.4 $
 * @author  Thomas Leplus &lt;<a href="mailto:thomas@leplus.org">thomas@leplus.org</a>&gt;
 */
public final class SHA1DigestEngine
	extends DigestEngine {
	
	/**
	 * Le nombre totale d'octets lus.
	 */
	public static final int BLOCK_LENGTH = 64;
	
	/**
	 * Le nombre de bits déjà lus.
	 */
	private long counter;
	
	/**
	 * Le buffer du hachage.
	 */
	private final byte[] buffer;
	
	/**
	 * La position pour le prochain octet lu.
	 */
	private int offset;
	
	/**
	 * Le contexte du hachage.
	 */
	private final int[] context;
	
	/**
	 * Tableau utilisé pour les calculs.
	 */
	private final int[] W;
	
	/**
	 * Lit un octet.
	 */
	public SHA1DigestEngine() {
		context = new int[5];
		buffer = new byte[BLOCK_LENGTH];
		W = new int[80];
		doReset();
	}
	
	/**
	 * Lit un octet.
	 *
	 * @param n l'octet à hacher.
	 */
	protected void doUpdate(byte n) {
		
		counter += 8;
		buffer[offset++] = n;
		
		if (offset < BLOCK_LENGTH) return;
		
		offset = 0;
		
		for (int i = 0; i < BLOCK_LENGTH;)
			W[i >>> 2] = (buffer[i++]       ) << 24 |
				         (buffer[i++] & 0xFF) << 16 |
				         (buffer[i++] & 0xFF) <<  8 |
				         (buffer[i++] & 0xFF);
		
		expand(W);
		
		int A = context[0];
		int B = context[1];
		int C = context[2];
		int D = context[3];
		int E = context[4];
				
		E+=((A<<5)|(A>>>-5))+f1(B,C,D)+W[0];  B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f1(A,B,C)+W[1];  A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f1(E,A,B)+W[2];  E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f1(D,E,A)+W[3];  D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f1(C,D,E)+W[4];  C=((C<<30)|(C>>>-30));
		E+=((A<<5)|(A>>>-5))+f1(B,C,D)+W[5];  B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f1(A,B,C)+W[6];  A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f1(E,A,B)+W[7];  E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f1(D,E,A)+W[8];  D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f1(C,D,E)+W[9];  C=((C<<30)|(C>>>-30));
		E+=((A<<5)|(A>>>-5))+f1(B,C,D)+W[10]; B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f1(A,B,C)+W[11]; A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f1(E,A,B)+W[12]; E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f1(D,E,A)+W[13]; D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f1(C,D,E)+W[14]; C=((C<<30)|(C>>>-30));
		E+=((A<<5)|(A>>>-5))+f1(B,C,D)+W[15]; B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f1(A,B,C)+W[16]; A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f1(E,A,B)+W[17]; E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f1(D,E,A)+W[18]; D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f1(C,D,E)+W[19]; C=((C<<30)|(C>>>-30));
		
		E+=((A<<5)|(A>>>-5))+f2(B,C,D)+W[20]; B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f2(A,B,C)+W[21]; A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f2(E,A,B)+W[22]; E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f2(D,E,A)+W[23]; D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f2(C,D,E)+W[24]; C=((C<<30)|(C>>>-30));
		E+=((A<<5)|(A>>>-5))+f2(B,C,D)+W[25]; B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f2(A,B,C)+W[26]; A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f2(E,A,B)+W[27]; E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f2(D,E,A)+W[28]; D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f2(C,D,E)+W[29]; C=((C<<30)|(C>>>-30));
		E+=((A<<5)|(A>>>-5))+f2(B,C,D)+W[30]; B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f2(A,B,C)+W[31]; A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f2(E,A,B)+W[32]; E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f2(D,E,A)+W[33]; D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f2(C,D,E)+W[34]; C=((C<<30)|(C>>>-30));
		E+=((A<<5)|(A>>>-5))+f2(B,C,D)+W[35]; B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f2(A,B,C)+W[36]; A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f2(E,A,B)+W[37]; E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f2(D,E,A)+W[38]; D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f2(C,D,E)+W[39]; C=((C<<30)|(C>>>-30));
		
		E+=((A<<5)|(A>>>-5))+f3(B,C,D)+W[40]; B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f3(A,B,C)+W[41]; A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f3(E,A,B)+W[42]; E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f3(D,E,A)+W[43]; D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f3(C,D,E)+W[44]; C=((C<<30)|(C>>>-30));
		E+=((A<<5)|(A>>>-5))+f3(B,C,D)+W[45]; B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f3(A,B,C)+W[46]; A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f3(E,A,B)+W[47]; E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f3(D,E,A)+W[48]; D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f3(C,D,E)+W[49]; C=((C<<30)|(C>>>-30));
		E+=((A<<5)|(A>>>-5))+f3(B,C,D)+W[50]; B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f3(A,B,C)+W[51]; A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f3(E,A,B)+W[52]; E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f3(D,E,A)+W[53]; D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f3(C,D,E)+W[54]; C=((C<<30)|(C>>>-30));
		E+=((A<<5)|(A>>>-5))+f3(B,C,D)+W[55]; B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f3(A,B,C)+W[56]; A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f3(E,A,B)+W[57]; E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f3(D,E,A)+W[58]; D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f3(C,D,E)+W[59]; C=((C<<30)|(C>>>-30));
		
		E+=((A<<5)|(A>>>-5))+f4(B,C,D)+W[60]; B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f4(A,B,C)+W[61]; A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f4(E,A,B)+W[62]; E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f4(D,E,A)+W[63]; D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f4(C,D,E)+W[64]; C=((C<<30)|(C>>>-30));
		E+=((A<<5)|(A>>>-5))+f4(B,C,D)+W[65]; B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f4(A,B,C)+W[66]; A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f4(E,A,B)+W[67]; E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f4(D,E,A)+W[68]; D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f4(C,D,E)+W[69]; C=((C<<30)|(C>>>-30));
		E+=((A<<5)|(A>>>-5))+f4(B,C,D)+W[70]; B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f4(A,B,C)+W[71]; A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f4(E,A,B)+W[72]; E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f4(D,E,A)+W[73]; D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f4(C,D,E)+W[74]; C=((C<<30)|(C>>>-30));
		E+=((A<<5)|(A>>>-5))+f4(B,C,D)+W[75]; B=((B<<30)|(B>>>-30));
		D+=((E<<5)|(E>>>-5))+f4(A,B,C)+W[76]; A=((A<<30)|(A>>>-30));
		C+=((D<<5)|(D>>>-5))+f4(E,A,B)+W[77]; E=((E<<30)|(E>>>-30));
		B+=((C<<5)|(C>>>-5))+f4(D,E,A)+W[78]; D=((D<<30)|(D>>>-30));
		A+=((B<<5)|(B>>>-5))+f4(C,D,E)+W[79]; C=((C<<30)|(C>>>-30));
		
		context[0] += A;
		context[1] += B;
		context[2] += C;
		context[3] += D;
		context[4] += E;
		
	}
	
	/**
	 * Ajoute le padding avant la finalisation du hachage.
	 *
	 * @return le nombre d'octets du padding.
	 */
	protected int doPadding() {
		long n = counter;
		update((byte)0x80);
		while (offset != 56)
			update((byte)0);
		update((byte)(n >>> 56));
		update((byte)(n >>> 48));
		update((byte)(n >>> 40));
		update((byte)(n >>> 32));
		update((byte)(n >>> 24));
		update((byte)(n >>> 16));
		update((byte)(n >>>  8));
		update((byte)(n));
		return (int)((counter - n) >>> 3);
	}
	
	/**
	 * Génère le hachage des octets lus jusqu'à présent.
	 *
	 * @return le hachage.
	 */
	protected Digest doDigest() {
		byte[] bytes = new byte[SHA1Digest.LENGTH];
		for (int i = 0; i < (SHA1Digest.LENGTH >>> 2); i++)
 			for (int j = 0; j < 4; j++)
				bytes[(i << 2) + (3 - j)] = (byte)(context[i] >>> (j << 3));
		reset();
		return new SHA1Digest(bytes);
	}
	
	/**
	 * Retourne la taille des blocs utilisés lors du hachage (en octets).
	 */
	protected int doGetBlockSize() {
		return BLOCK_LENGTH;
	}
	
	/**
	 * Remet la machine dans son état initial.
	 */
	protected void doReset() {
		counter = 0;
        context[0] = 0x67452301;
        context[1] = 0xefcdab89;
        context[2] = 0x98badcfe;
        context[3] = 0x10325476;
        context[4] = 0xc3d2e1f0;
		offset = 0;
	}
	
    private int f1(int a, int b, int c) {
        return ((a&(b^c))^c) + 0x5A827999;
    }
	
    private int f2(int a, int b, int c) {
        return (a^b^c) + 0x6ED9EBA1;
    }
	
    private int f3(int a, int b, int c) {
        return ((a&b)|((a|b)&c)) + 0x8F1BBCDC;
    }
	
    private int f4(int a, int b, int c) {
        return (a^b^c) + 0xCA62C1D6;
    }
	
    private void expand(int[] W) {
        for (int i = 16; i < 80; i++) {
            int j = W[i-16] ^ W[i-14] ^ W[i-8] ^ W[i-3];
            W[i] = (j << 1) | (j >>> -1);
        }
    }
	
}
