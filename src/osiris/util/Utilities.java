package osiris.util;

/*
 * Osiris Emulator
 * Copyright (C) 2011  Garrett Woodard, Blake Beaupain, Travis Burtrum
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import osiris.game.model.Position;
import osiris.io.PacketReader;

// TODO: Auto-generated Javadoc

/**
 * A collection of utility methods.
 * 
 * @author Blake
 * 
 */
public class Utilities {

	/**
	 * Gets the average.
	 * 
	 * @param numbers
	 *            the numbers
	 * @return the average
	 */
	public static int getAverage(int... numbers) {
		int total = 0;
		for (int i : numbers) {
			total += i;
		}
		return total / numbers.length;
	}

	/**
	 * Proper case.
	 * 
	 * @param str
	 *            the str
	 * @return the string
	 */
	public static String toProperCase(String str) {
		return (str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
	}

	/**
	 * Level for exp.
	 * 
	 * @param exp
	 *            the exp
	 * @return the int
	 */
	public static int levelForExp(double exp) {
		int points = 0;
		int output = 0;
		for (int lvl = 1; lvl < 100; lvl++) {
			points += Math.floor((double) lvl + 300.0 * Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if ((output - 1) >= exp) {
				return lvl;
			}
		}
		return 99;
	}

	/**
	 * Ticks to mins.
	 * 
	 * @param ticks
	 *            the ticks
	 * @return the int
	 */
	public static int ticksToMins(int ticks) {
		return (ticks * 600 / 1000 / 60) + 1;
	}

	/**
	 * Exp for level.
	 * 
	 * @param level
	 *            the level
	 * @return the double
	 */
	public static double expForLevel(int level) {
		double points = 0;
		double output = 0;
		for (int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			if (lvl >= level) {
				return output;
			}
			output = (int) Math.floor(points / 4);
		}
		return 0;
	}

	/**
	 * String to long.
	 * 
	 * @param s
	 *            the s
	 * @return the long
	 */
	public static long stringToLong(String s) {
		long l = 0L;
		for (int i = 0; i < s.length() && i < 12; i++) {
			char c = s.charAt(i);
			l *= 37L;
			if (c >= 'A' && c <= 'Z')
				l += (1 + c) - 65;
			else if (c >= 'a' && c <= 'z')
				l += (1 + c) - 97;
			else if (c >= '0' && c <= '9')
				l += (27 + c) - 48;
		}
		while (l % 37L == 0L && l != 0L)
			l /= 37L;
		return l;
	}

	/**
	 * Long to string.
	 * 
	 * @param l
	 *            the l
	 * @return the string
	 */
	public static String longToString(long l) {
		return longToString(l, true);
	}

	/**
	 * Long to string.
	 * 
	 * @param l
	 *            the l
	 * @param proper
	 *            the proper
	 * @return the string
	 */
	public static String longToString(long l, boolean proper) {
		if (l <= 0L || l >= 0x5b5b57f8a98a5dd1L) {
			return null;
		}
		if (l % 37L == 0L) {
			return null;
		}
		int i = 0;
		char ac[] = new char[12];
		while (l != 0L) {
			long l1 = l;
			l /= 37L;
			ac[11 - i++] = VALID_CHARS[(int) (l1 - l * 37L)];
		}
		String out = new String(ac, 12 - i, i);
		return out;
	}

	/**
	 * Encrypt player chat.
	 * 
	 * @param is
	 *            the is
	 * @param i_25_
	 *            the i_25_
	 * @param i_26_
	 *            the i_26_
	 * @param i_27_
	 *            the i_27_
	 * @param is_28_
	 *            the is_28_
	 * @return the int
	 */
	public static int encryptPlayerChat(byte[] is, int i_25_, int i_26_, int i_27_, byte[] is_28_) {
		try {
			i_27_ += i_25_;
			int i_29_ = 0;
			int i_30_ = i_26_ << -2116795453;
			for (; i_27_ > i_25_; i_25_++) {
				int i_31_ = 0xff & is_28_[i_25_];
				int i_32_ = anIntArray233[i_31_];
				int i_33_ = aByteArray235[i_31_];
				int i_34_ = i_30_ >> -1445887805;
				int i_35_ = i_30_ & 0x7;
				i_29_ &= (-i_35_ >> 473515839);
				i_30_ += i_33_;
				int i_36_ = ((-1 + (i_35_ - -i_33_)) >> -1430991229) + i_34_;
				i_35_ += 24;
				is[i_34_] = (byte) (i_29_ = (i_29_ | (i_32_ >>> i_35_)));
				if ((i_36_ ^ 0xffffffff) < (i_34_ ^ 0xffffffff)) {
					i_34_++;
					i_35_ -= 8;
					is[i_34_] = (byte) (i_29_ = i_32_ >>> i_35_);
					if (i_36_ > i_34_) {
						i_34_++;
						i_35_ -= 8;
						is[i_34_] = (byte) (i_29_ = i_32_ >>> i_35_);
						if (i_36_ > i_34_) {
							i_35_ -= 8;
							i_34_++;
							is[i_34_] = (byte) (i_29_ = i_32_ >>> i_35_);
							if (i_34_ < i_36_) {
								i_35_ -= 8;
								i_34_++;
								is[i_34_] = (byte) (i_29_ = i_32_ << -i_35_);
							}
						}
					}
				}
			}
			return -i_26_ + ((7 + i_30_) >> -662855293);
		} catch (RuntimeException runtimeexception) {
		}
		return 0;
	}

	/**
	 * Decrypt player chat.
	 * 
	 * @param str
	 *            the str
	 * @param totalChars
	 *            the total chars
	 * @return the string
	 */
	public static String decryptPlayerChat(PacketReader str, int totalChars) {
		try {
			if (totalChars == 0) {
				return "";
			}
			int charsDecoded = 0;
			int i_4_ = 0;
			String s = "";
			for (;;) {
				byte i_7_ = (byte) str.readByte();
				if (i_7_ >= 0) {
					i_4_++;
				} else {
					i_4_ = anIntArray241[i_4_];
				}
				int i_8_;
				if ((i_8_ = anIntArray241[i_4_]) < 0) {
					s += (char) (byte) (i_8_ ^ 0xffffffff);
					if (totalChars <= ++charsDecoded) {
						break;
					}
					i_4_ = 0;
				}
				if (((i_7_ & 0x40) ^ 0xffffffff) != -1) {
					i_4_ = anIntArray241[i_4_];
				} else {
					i_4_++;
				}
				if ((i_8_ = anIntArray241[i_4_]) < 0) {
					s += (char) (byte) (i_8_ ^ 0xffffffff);
					if (++charsDecoded >= totalChars) {
						break;
					}
					i_4_ = 0;
				}
				if ((0x20 & i_7_) == 0) {
					i_4_++;
				} else {
					i_4_ = anIntArray241[i_4_];
				}
				if ((i_8_ = anIntArray241[i_4_]) < 0) {
					s += (char) (byte) (i_8_ ^ 0xffffffff);
					if (totalChars <= ++charsDecoded) {
						break;
					}
					i_4_ = 0;
				}
				if (((0x10 & i_7_) ^ 0xffffffff) == -1) {
					i_4_++;
				} else {
					i_4_ = anIntArray241[i_4_];
				}
				if ((i_8_ = anIntArray241[i_4_]) < 0) {
					s += (char) (byte) (i_8_ ^ 0xffffffff);
					if (totalChars <= ++charsDecoded) {
						break;
					}

					i_4_ = 0;
				}
				if (((0x8 & i_7_) ^ 0xffffffff) != -1) {
					i_4_ = anIntArray241[i_4_];
				} else {
					i_4_++;
				}
				if ((i_8_ = anIntArray241[i_4_]) < 0) {
					s += (char) (byte) (i_8_ ^ 0xffffffff);
					if (++charsDecoded >= totalChars) {
						break;
					}
					i_4_ = 0;
				}
				if ((0x4 & i_7_) == 0) {
					i_4_++;
				} else {
					i_4_ = anIntArray241[i_4_];
				}
				if ((i_8_ = anIntArray241[i_4_]) < 0) {
					s += (char) (byte) (i_8_ ^ 0xffffffff);
					if (totalChars <= ++charsDecoded) {
						break;
					}
					i_4_ = 0;
				}
				if (((i_7_ & 0x2) ^ 0xffffffff) != -1) {
					i_4_ = anIntArray241[i_4_];
				} else {
					i_4_++;
				}
				if ((i_8_ = anIntArray241[i_4_]) < 0) {
					s += (char) (byte) (i_8_ ^ 0xffffffff);
					if (totalChars <= ++charsDecoded) {
						break;
					}
					i_4_ = 0;
				}
				if (((i_7_ & 0x1) ^ 0xffffffff) != -1) {
					i_4_ = anIntArray241[i_4_];
				} else {
					i_4_++;
				}
				if ((i_8_ = anIntArray241[i_4_]) < 0) {
					s += (char) (byte) (i_8_ ^ 0xffffffff);
					if (++charsDecoded >= totalChars) {
						break;
					}
					i_4_ = 0;
				}
			}
			return s;
		} catch (RuntimeException runtimeexception) {
		}
		return "";
	}

	/**
	 * Gets the reverse.
	 * 
	 * @param src
	 *            the src
	 * @return the reverse
	 */
	public static String getReverse(String src) {
		int i, len = src.length();
		StringBuffer reversed = new StringBuffer(len);
		for (i = (len - 1); i >= 0; i--)
			reversed.append(src.charAt(i));
		return reversed.toString();
	}

	/**
	 * The an int array233.
	 */
	private static int[] anIntArray233 = { 0, 1024, 2048, 3072, 4096, 5120, 6144, 8192, 9216, 12288, 10240, 11264, 16384, 18432, 17408, 20480, 21504, 22528, 23552, 24576, 25600, 26624, 27648, 28672, 29696, 30720, 31744, 32768, 33792, 34816, 35840, 36864, 536870912, 16777216, 37888, 65536, 38912, 131072, 196608, 33554432, 524288, 1048576, 1572864, 262144, 67108864, 4194304, 134217728, 327680, 8388608, 2097152, 12582912, 13631488, 14680064, 15728640, 100663296, 101187584, 101711872, 101974016, 102760448, 102236160, 40960, 393216, 229376, 117440512, 104857600, 109051904, 201326592, 205520896, 209715200, 213909504, 106954752, 218103808, 226492416, 234881024, 222298112, 224395264, 268435456, 272629760, 276824064, 285212672, 289406976, 223346688, 293601280, 301989888, 318767104, 297795584,
			298844160, 310378496, 102498304, 335544320, 299892736, 300941312, 301006848, 300974080, 39936, 301465600, 49152, 1073741824, 369098752, 402653184, 1342177280, 1610612736, 469762048, 1476395008, -2147483648, -1879048192, 352321536, 1543503872, -2013265920, -1610612736, -1342177280, -1073741824, -1543503872, 356515840, -1476395008, -805306368, -536870912, -268435456, 1577058304, -134217728, 360710144, -67108864, 364904448, 51200, 57344, 52224, 301203456, 53248, 54272, 55296, 56320, 301072384, 301073408, 301074432, 301075456, 301076480, 301077504, 301078528, 301079552, 301080576, 301081600, 301082624, 301083648, 301084672, 301085696, 301086720, 301087744, 301088768, 301089792, 301090816, 301091840, 301092864, 301093888, 301094912, 301095936, 301096960, 301097984, 301099008,
			301100032, 301101056, 301102080, 301103104, 301104128, 301105152, 301106176, 301107200, 301108224, 301109248, 301110272, 301111296, 301112320, 301113344, 301114368, 301115392, 301116416, 301117440, 301118464, 301119488, 301120512, 301121536, 301122560, 301123584, 301124608, 301125632, 301126656, 301127680, 301128704, 301129728, 301130752, 301131776, 301132800, 301133824, 301134848, 301135872, 301136896, 301137920, 301138944, 301139968, 301140992, 301142016, 301143040, 301144064, 301145088, 301146112, 301147136, 301148160, 301149184, 301150208, 301151232, 301152256, 301153280, 301154304, 301155328, 301156352, 301157376, 301158400, 301159424, 301160448, 301161472, 301162496, 301163520, 301164544, 301165568, 301166592, 301167616, 301168640, 301169664, 301170688, 301171712,
			301172736, 301173760, 301174784, 301175808, 301176832, 301177856, 301178880, 301179904, 301180928, 301181952, 301182976, 301184000, 301185024, 301186048, 301187072, 301188096, 301189120, 301190144, 301191168, 301193216, 301195264, 301194240, 301197312, 301198336, 301199360, 301201408, 301202432 };

	/**
	 * The a byte array235.
	 */
	private static byte[] aByteArray235 = { 22, 22, 22, 22, 22, 22, 21, 22, 22, 20, 22, 22, 22, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 3, 8, 22, 16, 22, 16, 17, 7, 13, 13, 13, 16, 7, 10, 6, 16, 10, 11, 12, 12, 12, 12, 13, 13, 14, 14, 11, 14, 19, 15, 17, 8, 11, 9, 10, 10, 10, 10, 11, 10, 9, 7, 12, 11, 10, 10, 9, 10, 10, 12, 10, 9, 8, 12, 12, 9, 14, 8, 12, 17, 16, 17, 22, 13, 21, 4, 7, 6, 5, 3, 6, 6, 5, 4, 10, 7, 5, 6, 4, 4, 6, 10, 5, 4, 4, 5, 7, 6, 10, 6, 10, 22, 19, 22, 14, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22,
			22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 21, 22, 21, 22, 22, 22, 21, 22, 22 };

	/**
	 * The an int array241.
	 */
	private static int[] anIntArray241 = { 215, 203, 83, 158, 104, 101, 93, 84, 107, 103, 109, 95, 94, 98, 89, 86, 70, 41, 32, 27, 24, 23, -1, -2, 26, -3, -4, 31, 30, -5, -6, -7, 37, 38, 36, -8, -9, -10, 40, -11, -12, 55, 48, 46, 47, -13, -14, -15, 52, 51, -16, -17, 54, -18, -19, 63, 60, 59, -20, -21, 62, -22, -23, 67, 66, -24, -25, 69, -26, -27, 199, 132, 80, 77, 76, -28, -29, 79, -30, -31, 87, 85, -32, -33, -34, -35, -36, 197, -37, 91, -38, 134, -39, -40, -41, 97, -42, -43, 133, 106, -44, 117, -45, -46, 139, -47, -48, 110, -49, -50, 114, 113, -51, -52, 116, -53, -54, 135, 138, 136, 129, 125, 124, -55, -56, 130, 128, -57, -58, -59, 183, -60, -61, -62, -63, -64, 148, -65, -66, 153, 149, 145, 144, -67, -68, 147, -69, -70, -71, 152, 154, -72, -73, -74, 157, 171, -75, -76, 207, 184, 174,
			167, 166, 165, -77, -78, -79, 172, 170, -80, -81, -82, 178, -83, 177, 182, -84, -85, 187, 181, -86, -87, -88, -89, 206, 221, -90, 189, -91, 198, 254, 262, 195, 196, -92, -93, -94, -95, -96, 252, 255, 250, -97, 211, 209, -98, -99, 212, -100, 213, -101, -102, -103, 224, -104, 232, 227, 220, 226, -105, -106, 246, 236, -107, 243, -108, -109, 231, 237, 235, -110, -111, 239, 238, -112, -113, -114, -115, -116, 241, -117, 244, -118, -119, 248, -120, 249, -121, -122, -123, 253, -124, -125, -126, -127, 259, 258, -128, -129, 261, -130, -131, 390, 327, 296, 281, 274, 271, 270, -132, -133, 273, -134, -135, 278, 277, -136, -137, 280, -138, -139, 289, 286, 285, -140, -141, 288, -142, -143, 293, 292, -144, -145, 295, -146, -147, 312, 305, 302, 301, -148, -149, 304, -150, -151, 309, 308, -152,
			-153, 311, -154, -155, 320, 317, 316, -156, -157, 319, -158, -159, 324, 323, -160, -161, 326, -162, -163, 359, 344, 337, 334, 333, -164, -165, 336, -166, -167, 341, 340, -168, -169, 343, -170, -171, 352, 349, 348, -172, -173, 351, -174, -175, 356, 355, -176, -177, 358, -178, -179, 375, 368, 365, 364, -180, -181, 367, -182, -183, 372, 371, -184, -185, 374, -186, -187, 383, 380, 379, -188, -189, 382, -190, -191, 387, 386, -192, -193, 389, -194, -195, 454, 423, 408, 401, 398, 397, -196, -197, 400, -198, -199, 405, 404, -200, -201, 407, -202, -203, 416, 413, 412, -204, -205, 415, -206, -207, 420, 419, -208, -209, 422, -210, -211, 439, 432, 429, 428, -212, -213, 431, -214, -215, 436, 435, -216, -217, 438, -218, -219, 447, 444, 443, -220, -221, 446, -222, -223, 451, 450, -224, -225,
			453, -226, -227, 486, 471, 464, 461, 460, -228, -229, 463, -230, -231, 468, 467, -232, -233, 470, -234, -235, 479, 476, 475, -236, -237, 478, -238, -239, 483, 482, -240, -241, 485, -242, -243, 499, 495, 492, 491, -244, -245, 494, -246, -247, 497, -248, 502, -249, 506, 503, -250, -251, 505, -252, -253, 508, -254, 510, -255, -256, 0 };

	/**
	 * The valid chars.
	 */
	public static char[] VALID_CHARS = { '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * Gets the distance.
	 * 
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @return the distance
	 */
	public static int getDistance(Position a, Position b) {
		int deltaX = b.getX() - a.getX();
		int deltaY = b.getY() - a.getY();
		return ((int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
	}

	/**
	 * Random.
	 * 
	 * @param high
	 *            the high
	 * @return the int
	 */
	public static int random(int high) {
		return new Random().nextInt(high + 1);
	}

	/**
	 * Seconds to ticks.
	 * 
	 * @param seconds
	 *            the seconds
	 * @return the long
	 */
	public static long secondsToTicks(long seconds) {
		return seconds * 1000 / 600;
	}

	/**
	 * Generate positions in box.
	 * 
	 * @param minX
	 *            the min x
	 * @param minY
	 *            the min y
	 * @param maxX
	 *            the max x
	 * @param maxY
	 *            the max y
	 * @return the array list
	 */
	public static ArrayList<Position> generatePositionsInBox(int minX, int minY, int maxX, int maxY) {
		ArrayList<Position> temp = new ArrayList<Position>();
		int countX = maxX - minX;
		int countY = maxY - minY;
		for (int x = 0; x <= countX; x++) {
			for (int y = 0; y <= countY; y++) {
				int posX = minX + x;
				int posY = minY + y;
				temp.add(new Position(posX, posY));
			}
		}
		return temp;
	}

	/**
	 * Smf hash.
	 * 
	 * @param username
	 *            the username
	 * @param salt
	 *            the salt
	 * @return the string
	 */
	public static String smfHash(String username, Integer salt) {
		try {
			return SHA(SHA(username) + salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * SHA.
	 * 
	 * @param text
	 *            the text
	 * @return the string
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	public static String SHA(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA");
		byte[] md5hash;
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		md5hash = md.digest();
		return convertToHex(md5hash);
	}

	/**
	 * Convert to hex.
	 * 
	 * @param data
	 *            the data
	 * @return the string
	 */
	private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	public static int generateSalt() {
		return new SecureRandom().nextInt();
	}
}
