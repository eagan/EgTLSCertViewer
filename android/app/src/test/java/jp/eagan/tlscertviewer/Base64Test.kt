package jp.eagan.tlscertviewer

import org.junit.Test
import org.junit.Assert.assertEquals

class Base64Test {
    @Test fun c2b_00() { assertEquals(0x00, Base64.c2b('A')) }
    @Test fun c2b_01() { assertEquals(0x01, Base64.c2b('B')) }
    @Test fun c2b_02() { assertEquals(0x02, Base64.c2b('C')) }
    @Test fun c2b_03() { assertEquals(0x03, Base64.c2b('D')) }
    @Test fun c2b_04() { assertEquals(0x04, Base64.c2b('E')) }
    @Test fun c2b_05() { assertEquals(0x05, Base64.c2b('F')) }
    @Test fun c2b_06() { assertEquals(0x06, Base64.c2b('G')) }
    @Test fun c2b_07() { assertEquals(0x07, Base64.c2b('H')) }
    @Test fun c2b_08() { assertEquals(0x08, Base64.c2b('I')) }
    @Test fun c2b_09() { assertEquals(0x09, Base64.c2b('J')) }
    @Test fun c2b_0a() { assertEquals(0x0a, Base64.c2b('K')) }
    @Test fun c2b_0b() { assertEquals(0x0b, Base64.c2b('L')) }
    @Test fun c2b_0c() { assertEquals(0x0c, Base64.c2b('M')) }
    @Test fun c2b_0d() { assertEquals(0x0d, Base64.c2b('N')) }
    @Test fun c2b_0e() { assertEquals(0x0e, Base64.c2b('O')) }
    @Test fun c2b_0f() { assertEquals(0x0f, Base64.c2b('P')) }
    @Test fun c2b_10() { assertEquals(0x10, Base64.c2b('Q')) }
    @Test fun c2b_11() { assertEquals(0x11, Base64.c2b('R')) }
    @Test fun c2b_12() { assertEquals(0x12, Base64.c2b('S')) }
    @Test fun c2b_13() { assertEquals(0x13, Base64.c2b('T')) }
    @Test fun c2b_14() { assertEquals(0x14, Base64.c2b('U')) }
    @Test fun c2b_15() { assertEquals(0x15, Base64.c2b('V')) }
    @Test fun c2b_16() { assertEquals(0x16, Base64.c2b('W')) }
    @Test fun c2b_17() { assertEquals(0x17, Base64.c2b('X')) }
    @Test fun c2b_18() { assertEquals(0x18, Base64.c2b('Y')) }
    @Test fun c2b_19() { assertEquals(0x19, Base64.c2b('Z')) }
    @Test fun c2b_1a() { assertEquals(0x1a, Base64.c2b('a')) }
    @Test fun c2b_1b() { assertEquals(0x1b, Base64.c2b('b')) }
    @Test fun c2b_1c() { assertEquals(0x1c, Base64.c2b('c')) }
    @Test fun c2b_1d() { assertEquals(0x1d, Base64.c2b('d')) }
    @Test fun c2b_1e() { assertEquals(0x1e, Base64.c2b('e')) }
    @Test fun c2b_1f() { assertEquals(0x1f, Base64.c2b('f')) }
    @Test fun c2b_20() { assertEquals(0x20, Base64.c2b('g')) }
    @Test fun c2b_21() { assertEquals(0x21, Base64.c2b('h')) }
    @Test fun c2b_22() { assertEquals(0x22, Base64.c2b('i')) }
    @Test fun c2b_23() { assertEquals(0x23, Base64.c2b('j')) }
    @Test fun c2b_24() { assertEquals(0x24, Base64.c2b('k')) }
    @Test fun c2b_25() { assertEquals(0x25, Base64.c2b('l')) }
    @Test fun c2b_26() { assertEquals(0x26, Base64.c2b('m')) }
    @Test fun c2b_27() { assertEquals(0x27, Base64.c2b('n')) }
    @Test fun c2b_28() { assertEquals(0x28, Base64.c2b('o')) }
    @Test fun c2b_29() { assertEquals(0x29, Base64.c2b('p')) }
    @Test fun c2b_2a() { assertEquals(0x2a, Base64.c2b('q')) }
    @Test fun c2b_2b() { assertEquals(0x2b, Base64.c2b('r')) }
    @Test fun c2b_2c() { assertEquals(0x2c, Base64.c2b('s')) }
    @Test fun c2b_2d() { assertEquals(0x2d, Base64.c2b('t')) }
    @Test fun c2b_2e() { assertEquals(0x2e, Base64.c2b('u')) }
    @Test fun c2b_2f() { assertEquals(0x2f, Base64.c2b('v')) }
    @Test fun c2b_30() { assertEquals(0x30, Base64.c2b('w')) }
    @Test fun c2b_31() { assertEquals(0x31, Base64.c2b('x')) }
    @Test fun c2b_32() { assertEquals(0x32, Base64.c2b('y')) }
    @Test fun c2b_33() { assertEquals(0x33, Base64.c2b('z')) }
    @Test fun c2b_34() { assertEquals(0x34, Base64.c2b('0')) }
    @Test fun c2b_35() { assertEquals(0x35, Base64.c2b('1')) }
    @Test fun c2b_36() { assertEquals(0x36, Base64.c2b('2')) }
    @Test fun c2b_37() { assertEquals(0x37, Base64.c2b('3')) }
    @Test fun c2b_38() { assertEquals(0x38, Base64.c2b('4')) }
    @Test fun c2b_39() { assertEquals(0x39, Base64.c2b('5')) }
    @Test fun c2b_3a() { assertEquals(0x3a, Base64.c2b('6')) }
    @Test fun c2b_3b() { assertEquals(0x3b, Base64.c2b('7')) }
    @Test fun c2b_3c() { assertEquals(0x3c, Base64.c2b('8')) }
    @Test fun c2b_3d() { assertEquals(0x3d, Base64.c2b('9')) }
    @Test fun c2b_3e() { assertEquals(0x3e, Base64.c2b('+')) }
    @Test fun c2b_3f() { assertEquals(0x3f, Base64.c2b('/')) }

    @Test(expected = IllegalArgumentException::class)
    fun c2b_Exception() {
        Base64.c2b('!')
    }

    @Test fun b2c_00() { assertEquals('A', Base64.b2c(0x00.toByte())) }
    @Test fun b2c_01() { assertEquals('B', Base64.b2c(0x01.toByte())) }
    @Test fun b2c_02() { assertEquals('C', Base64.b2c(0x02.toByte())) }
    @Test fun b2c_03() { assertEquals('D', Base64.b2c(0x03.toByte())) }
    @Test fun b2c_04() { assertEquals('E', Base64.b2c(0x04.toByte())) }
    @Test fun b2c_05() { assertEquals('F', Base64.b2c(0x05.toByte())) }
    @Test fun b2c_06() { assertEquals('G', Base64.b2c(0x06.toByte())) }
    @Test fun b2c_07() { assertEquals('H', Base64.b2c(0x07.toByte())) }
    @Test fun b2c_08() { assertEquals('I', Base64.b2c(0x08.toByte())) }
    @Test fun b2c_09() { assertEquals('J', Base64.b2c(0x09.toByte())) }
    @Test fun b2c_0a() { assertEquals('K', Base64.b2c(0x0a.toByte())) }
    @Test fun b2c_0b() { assertEquals('L', Base64.b2c(0x0b.toByte())) }
    @Test fun b2c_0c() { assertEquals('M', Base64.b2c(0x0c.toByte())) }
    @Test fun b2c_0d() { assertEquals('N', Base64.b2c(0x0d.toByte())) }
    @Test fun b2c_0e() { assertEquals('O', Base64.b2c(0x0e.toByte())) }
    @Test fun b2c_0f() { assertEquals('P', Base64.b2c(0x0f.toByte())) }
    @Test fun b2c_10() { assertEquals('Q', Base64.b2c(0x10.toByte())) }
    @Test fun b2c_11() { assertEquals('R', Base64.b2c(0x11.toByte())) }
    @Test fun b2c_12() { assertEquals('S', Base64.b2c(0x12.toByte())) }
    @Test fun b2c_13() { assertEquals('T', Base64.b2c(0x13.toByte())) }
    @Test fun b2c_14() { assertEquals('U', Base64.b2c(0x14.toByte())) }
    @Test fun b2c_15() { assertEquals('V', Base64.b2c(0x15.toByte())) }
    @Test fun b2c_16() { assertEquals('W', Base64.b2c(0x16.toByte())) }
    @Test fun b2c_17() { assertEquals('X', Base64.b2c(0x17.toByte())) }
    @Test fun b2c_18() { assertEquals('Y', Base64.b2c(0x18.toByte())) }
    @Test fun b2c_19() { assertEquals('Z', Base64.b2c(0x19.toByte())) }
    @Test fun b2c_1a() { assertEquals('a', Base64.b2c(0x1a.toByte())) }
    @Test fun b2c_1b() { assertEquals('b', Base64.b2c(0x1b.toByte())) }
    @Test fun b2c_1c() { assertEquals('c', Base64.b2c(0x1c.toByte())) }
    @Test fun b2c_1d() { assertEquals('d', Base64.b2c(0x1d.toByte())) }
    @Test fun b2c_1e() { assertEquals('e', Base64.b2c(0x1e.toByte())) }
    @Test fun b2c_1f() { assertEquals('f', Base64.b2c(0x1f.toByte())) }
    @Test fun b2c_20() { assertEquals('g', Base64.b2c(0x20.toByte())) }
    @Test fun b2c_21() { assertEquals('h', Base64.b2c(0x21.toByte())) }
    @Test fun b2c_22() { assertEquals('i', Base64.b2c(0x22.toByte())) }
    @Test fun b2c_23() { assertEquals('j', Base64.b2c(0x23.toByte())) }
    @Test fun b2c_24() { assertEquals('k', Base64.b2c(0x24.toByte())) }
    @Test fun b2c_25() { assertEquals('l', Base64.b2c(0x25.toByte())) }
    @Test fun b2c_26() { assertEquals('m', Base64.b2c(0x26.toByte())) }
    @Test fun b2c_27() { assertEquals('n', Base64.b2c(0x27.toByte())) }
    @Test fun b2c_28() { assertEquals('o', Base64.b2c(0x28.toByte())) }
    @Test fun b2c_29() { assertEquals('p', Base64.b2c(0x29.toByte())) }
    @Test fun b2c_2a() { assertEquals('q', Base64.b2c(0x2a.toByte())) }
    @Test fun b2c_2b() { assertEquals('r', Base64.b2c(0x2b.toByte())) }
    @Test fun b2c_2c() { assertEquals('s', Base64.b2c(0x2c.toByte())) }
    @Test fun b2c_2d() { assertEquals('t', Base64.b2c(0x2d.toByte())) }
    @Test fun b2c_2e() { assertEquals('u', Base64.b2c(0x2e.toByte())) }
    @Test fun b2c_2f() { assertEquals('v', Base64.b2c(0x2f.toByte())) }
    @Test fun b2c_30() { assertEquals('w', Base64.b2c(0x30.toByte())) }
    @Test fun b2c_31() { assertEquals('x', Base64.b2c(0x31.toByte())) }
    @Test fun b2c_32() { assertEquals('y', Base64.b2c(0x32.toByte())) }
    @Test fun b2c_33() { assertEquals('z', Base64.b2c(0x33.toByte())) }
    @Test fun b2c_34() { assertEquals('0', Base64.b2c(0x34.toByte())) }
    @Test fun b2c_35() { assertEquals('1', Base64.b2c(0x35.toByte())) }
    @Test fun b2c_36() { assertEquals('2', Base64.b2c(0x36.toByte())) }
    @Test fun b2c_37() { assertEquals('3', Base64.b2c(0x37.toByte())) }
    @Test fun b2c_38() { assertEquals('4', Base64.b2c(0x38.toByte())) }
    @Test fun b2c_39() { assertEquals('5', Base64.b2c(0x39.toByte())) }
    @Test fun b2c_3a() { assertEquals('6', Base64.b2c(0x3a.toByte())) }
    @Test fun b2c_3b() { assertEquals('7', Base64.b2c(0x3b.toByte())) }
    @Test fun b2c_3c() { assertEquals('8', Base64.b2c(0x3c.toByte())) }
    @Test fun b2c_3d() { assertEquals('9', Base64.b2c(0x3d.toByte())) }
    @Test fun b2c_3e() { assertEquals('+', Base64.b2c(0x3e.toByte())) }
    @Test fun b2c_3f() { assertEquals('/', Base64.b2c(0x3f.toByte())) }

    @Test(expected = IllegalArgumentException::class)
    fun b2c_Exception() {
        Base64.b2c(0xff.toByte())
    }

    @Test
    fun decode_01() {
        val decoded = Base64.decode("ghijklmnopqr", 0)
        assertEquals(9, decoded.size)
        assertEquals(0b100000_10.toByte(), decoded[0]) // gh
        assertEquals(0b0001_1000.toByte(), decoded[1]) // hi
        assertEquals(0b10_100011.toByte(), decoded[2]) // ij
        assertEquals(0b100100_10.toByte(), decoded[3]) // kl
        assertEquals(0b0101_1001.toByte(), decoded[4]) // lm
        assertEquals(0b10_100111.toByte(), decoded[5]) // mn
        assertEquals(0b101000_10.toByte(), decoded[6]) // op
        assertEquals(0b1001_1010.toByte(), decoded[7]) // pq
        assertEquals(0b10_101011.toByte(), decoded[8]) // qr
    }

    @Test
    fun decode_odd01() {
        val decoded = Base64.decode("ghijklmnopq=", 0)
        assertEquals(8, decoded.size)
        assertEquals(0b100000_10.toByte(), decoded[0]) // gh
        assertEquals(0b0001_1000.toByte(), decoded[1]) // hi
        assertEquals(0b10_100011.toByte(), decoded[2]) // ij
        assertEquals(0b100100_10.toByte(), decoded[3]) // kl
        assertEquals(0b0101_1001.toByte(), decoded[4]) // lm
        assertEquals(0b10_100111.toByte(), decoded[5]) // mn
        assertEquals(0b101000_10.toByte(), decoded[6]) // op
        assertEquals(0b1001_1010.toByte(), decoded[7]) // pq
    }

    @Test
    fun decode_odd02() {
        val decoded = Base64.decode("ghijklmnop==", 0)
        assertEquals(7, decoded.size)
        assertEquals(0b100000_10.toByte(), decoded[0]) // gh
        assertEquals(0b0001_1000.toByte(), decoded[1]) // hi
        assertEquals(0b10_100011.toByte(), decoded[2]) // ij
        assertEquals(0b100100_10.toByte(), decoded[3]) // kl
        assertEquals(0b0101_1001.toByte(), decoded[4]) // lm
        assertEquals(0b10_100111.toByte(), decoded[5]) // mn
        assertEquals(0b101000_10.toByte(), decoded[6]) // op
    }
}
