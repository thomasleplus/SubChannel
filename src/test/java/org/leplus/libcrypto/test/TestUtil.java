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
