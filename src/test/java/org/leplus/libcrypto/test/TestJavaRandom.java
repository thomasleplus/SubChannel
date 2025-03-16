package org.leplus.libcrypto.test;

import org.leplus.libcrypto.*;

import java.math.BigInteger;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public final class TestJavaRandom
{
	private final JavaPRNGenerator random = new JavaPRNGenerator();
	
    @Test
    public void test()
		throws Exception {
		boolean b = true;
  		b = testBits(1000000) ? b : false;
  		b = testBytes(1000000) ? b : false;
		b = testShorts(10000000) ? b : false;
  		b = testInts(1000000) ? b : false;
  		b = testLongs(1000000) ? b : false;
  		b = testFloats(1000000) ? b : false;
  		b = testDoubles(1000000) ? b : false;
		assertTrue(b);
    }

    private boolean testBits(int samples)
		throws Exception {
		int t = 0;
		for (int i = 0; i < samples; i++)
			if (random.getBit())
				t++;
		System.out.println("Bits: {true, false}");
		System.out.println("   true:  " + (double)t/samples);
		System.out.println("   false: " + (double)(samples-t)/samples);
		return true;
    }

    private boolean testBytes(int samples)
		throws Exception {
		int[] table = new int[Byte.MAX_VALUE - Byte.MIN_VALUE + 1];
		for (int i = 0; i < samples; i++)
			table[random.getByte() - Byte.MIN_VALUE]++;
		System.out.println("Bytes: [" + Byte.MIN_VALUE + ", " + Byte.MAX_VALUE + "] (" + (double)(Byte.MIN_VALUE + Byte.MAX_VALUE) / 2 + ")");
		int min = 0;
		int max = 0;
		double avgv = 0;
		double avgp = 0;
		for (int i = 0; i < table.length; i++) {
			avgv += (double)table[i] * (i + Byte.MIN_VALUE) / samples;
			avgp += (double)table[i] / 256 / samples;
			if (table[i] < table[min]) min = i;
			if (table[i] > table[max]) max = i;
		}
		System.out.println("   arithmetic mean: " + avgv);
		System.out.println("   min probability: " + (double)table[min]/samples + " (0x" + TestUtil.toHex((byte)min) + ")");
		System.out.println("   avg probability: " + avgp);
		System.out.println("   max probability: " + (double)table[max]/samples + " (0x" + TestUtil.toHex((byte)max) + ")");
		return true;
    }
	
    private boolean testShorts(int samples)
		throws Exception {
		int[] table = new int[Short.MAX_VALUE - Short.MIN_VALUE + 1];
		for (int i = 0; i < samples; i++)
			table[random.getShort() - Short.MIN_VALUE]++;
		System.out.println("Shorts: [" + Short.MIN_VALUE + ", " + Short.MAX_VALUE + "] (" + (double)(Short.MIN_VALUE + Short.MAX_VALUE) / 2 + ")");
		int min = 0;
		int max = 0;
		double avgv = 0;
		double avgp = 0;
		for (int i = 0; i < table.length; i++) {
			avgv += (double)table[i] * (i + Short.MIN_VALUE) / samples;
			avgp += (double)table[i] / 65536 / samples;
			if (table[i] < table[min]) min = i;
			if (table[i] > table[max]) max = i;
		}
		System.out.println("   arithmetic mean: " + avgv);
		System.out.println("   min probability: " + (double)table[min]/samples + " (0x" + TestUtil.toHex((short)min) + ")");
		System.out.println("   avg probability: " + avgp);
		System.out.println("   max probability: " + (double)table[max]/samples + " (0x" + TestUtil.toHex((short)max) + ")");
		return true;
    }
	
    private boolean testInts(int samples)
		throws Exception {
		double avg = 0;
		for (int i = 0; i < samples; i++)
			avg += (double)random.getInt() / samples;
		System.out.println("Integers: [" + Integer.MIN_VALUE + ", " + Integer.MAX_VALUE + "] (" + (double)(Integer.MIN_VALUE + Integer.MAX_VALUE) / 2 + ")");
		System.out.println("   arithmetic mean: " + avg);
		return true;
    }
	
    private boolean testLongs(int samples)
		throws Exception {
		double avg = 0;
		for (int i = 0; i < samples; i++)
			avg += (double)random.getLong() / samples;
		System.out.println("Longs: [" + Long.MIN_VALUE + ", " + Long.MAX_VALUE + "] (" + (double)(Long.MIN_VALUE + Long.MAX_VALUE) / 2 + ")");
		System.out.println("   arithmetic mean: " + avg);
		return true;
    }
	
    private boolean testFloats(int samples)
		throws Exception {
		double avg = 0;
		for (int i = 0; i < samples; i++)
			avg += (double)random.getFloat() / samples;
		System.out.println("Floats: [-" + Float.MAX_VALUE + ", " + Float.MAX_VALUE + "]");
		System.out.println("   arithmetic mean: " + avg);
		return true;
    }
	
    private boolean testDoubles(int samples)
		throws Exception {
		double avg = 0;
		for (int i = 0; i < samples; i++)
			avg += random.getDouble() / samples;
		System.out.println("Doubles: [-" + Double.MAX_VALUE + ", " + Double.MAX_VALUE + "]");
		System.out.println("   arithmetic mean: " + avg);
		return true;
    }
}
