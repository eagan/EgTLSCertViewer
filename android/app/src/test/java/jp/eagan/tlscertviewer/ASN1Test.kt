package jp.eagan.tlscertviewer

import org.junit.Test
import org.junit.Assert.assertEquals

import java.io.ByteArrayInputStream
import java.io.InputStream

fun byteInputStreamOf(vararg bytes: Int): InputStream {
    val bytearray = bytes.map{ it.toByte() }.toByteArray()
    return ByteArrayInputStream(bytearray)
}

class ASN1Test {
    @Test
    fun decodeDERLength_short() {
        val input = byteInputStreamOf(
            0x73, 0x55
        )
        val (len1, len0) = ASN1.decodeDERLength(input)
        assertEquals(0x73, len1)
        assertEquals(1, len0)
    }

    @Test
    fun decodeDERLength_long() {
        val input = byteInputStreamOf(
            0x83, 0x01, 0xab, 0xcd
        )
        val (len1, len0) = ASN1.decodeDERLength(input)
        assertEquals(0x1abcd, len1)
        assertEquals(4, len0)
    }

    @Test
    fun decodeDER_boolean_false() {
        val input = byteInputStreamOf(
            0x01, 0x01, 0x00
        )
        val (node, len) = ASN1.decodeDERStream(input)
        assertEquals(0x01, node.tag)
        assertEquals(false, node.value)
    }

    @Test
    fun decodeDER_boolean_true() {
        val input = byteInputStreamOf(
            0x01, 0x01, 0x01
        )
        val (node, len) = ASN1.decodeDERStream(input)
        assertEquals(0x01, node.tag)
        assertEquals(true, node.value)
    }

    @Test
    fun decodeDER_integer_01() {
        val input = byteInputStreamOf(
            0x02, 0x01, 0xab
        )
        val (node, len) = ASN1.decodeDERStream(input)
        assertEquals(0x02, node.tag)
        assertEquals(0xab, node.value)
    }

    @Test
    fun decodeDER_integer_02() {
        val input = byteInputStreamOf(
            0x02, 0x02, 0xab, 0xcd
        )
        val (node,len) = ASN1.decodeDERStream(input)
        assertEquals(0x02, node.tag)
        assertEquals(0xabcd, node.value)
    }

    @Test
    fun decodeDER_object_identifier_01() {
        val input = byteInputStreamOf(
            0x06, 0x03, 0x55, 0x04, 0x06
        )
        val (node, len) = ASN1.decodeDERStream(input)
        assertEquals(0x06, node.tag)
        val array = node.value as IntArray
        assertEquals(4, array.size)
        assertEquals(2, array[0])
        assertEquals(5, array[1])
        assertEquals(4, array[2])
        assertEquals(6, array[3])
    }

    @Test
    fun decodeDER_object_identifier_02() {
        val input = byteInputStreamOf(
            0x06, 0x09, 0x2a, 0x86, 0x48, 0x86, 0xf7, 0x0d,
            0x01, 0x01, 0x01
        )
        val (node, len) = ASN1.decodeDERStream(input)
        assertEquals(0x06, node.tag)
        val array = node.value as IntArray
        assertEquals(7, array.size)
        assertEquals(1, array[0])
        assertEquals(2, array[1])
        assertEquals(840, array[2])
        assertEquals(113549, array[3])
        assertEquals(1, array[4])
        assertEquals(1, array[5])
        assertEquals(1, array[6])
    }
}
