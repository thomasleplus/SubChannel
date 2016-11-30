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

import org.leplus.libcrypto.*;

public final class TestSHA1
{
	public static final DigestEngine engine = new SHA1DigestEngine();
	
	public static void main(String[] args)
		throws Exception {
		test("", 0, "da39a3ee5e6b4b0d3255bfef95601890afd80709");
		test("", 1, "da39a3ee5e6b4b0d3255bfef95601890afd80709");
		test("abc", 1, "a9993e364706816aba3e25717850c26c9cd0d89d");
		test("abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq", 1, "84983e441c3bd26ebaae4aa1f95129e5e54670f1");
		test("a", 1000000, "34aa973cd4c4daa4f61eeb2bdbad27316534016f");
	}
	
	public static boolean test(String vector, int loop, String result)
		throws Exception {
		for (int i = 0; i < loop; i++)
			engine.update(vector.getBytes());
		Digest digest = engine.digest();
		boolean b = digest.getHex().equalsIgnoreCase(result);
		if (b) {
			System.out.println("Vector \"" + vector + "\" (x" + loop + ") : OK");
			System.out.println("   => " + result);
		} else {
			System.out.println("Vector \"" + vector + "\" (x" + loop + ") : NO");
			System.out.println("   => " + digest.getHex());
			System.out.println("   =X " + result);
		}
		return b;
	}
}
