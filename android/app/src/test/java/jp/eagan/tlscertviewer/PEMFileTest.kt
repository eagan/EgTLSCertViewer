package jp.eagan.tlscertviewer

import org.junit.Test
import org.junit.Assert.*

import java.io.BufferedReader

class PEMFileTest {
    @Test
    fun readSingleEntry() {
        val testinput: String = """-----BEGIN PUBLIC KEY-----
                                  |AAEC
                                  |-----END PUBLIC KEY-----""".trimMargin()
        val pem = PEMFile(BufferedReader(testinput.reader()))
        assertEquals(1, pem.length())
        val entry = pem.getEntry(0)
        assertEquals("PUBLIC KEY", entry.startLabel)
        assertEquals("PUBLIC KEY", entry.endLabel)
        assertEquals(0, entry.body[0])
        assertEquals(1, entry.body[1])
        assertEquals(2, entry.body[2])
    }
}