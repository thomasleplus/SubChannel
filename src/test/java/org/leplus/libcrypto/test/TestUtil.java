/* $Id: TestUtil.java,v 1.2 2003/03/23 23:38:52 leplusth Exp $ */

package org.leplus.libcrypto.test;

public final class TestUtil
{
	private static final String table[] = { "0", "1", "2", "3",
											"4", "5", "6", "7",
											"8", "9", "a", "b",
											"c", "d", "e", "f" };
	
	public static String toHex(byte n) {
		return table[(n >>> 4) & 0x0F] + table[n & 0x0F];
	}
	
	public static String toHex(short n) {
		return toHex((byte)(n >>> 8)) + toHex((byte)n);
	}
	
	public static String toHex(int n) {
		return toHex((short)(n >>> 16)) + toHex((short)n);
	}
	
	public static String toHex(long n) {
		return toHex((int)(n >>> 32)) + toHex((int)n);
	}
	
	public static String toBin(byte n) {
		String s = "";
		for (int i = 7; i >= 0; i--)
			s += (n >> i) & 0x01;
		return s;
	}
	
	public static String toBin(short n) {
		return toBin((byte)(n >>> 8)) + toBin((byte)n);
	}
	
	public static String toBin(int n) {
		return toBin((short)(n >>> 16)) + toBin((short)n);
	}
	
	public static String toBin(long n) {
		return toBin((int)(n >>> 32)) + toBin((int)n);
	}
}
