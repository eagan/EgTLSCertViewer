package jp.eagan.tlscertviewer

import android.util.Base64
import java.io.BufferedReader
import java.io.ByteArrayOutputStream

class PEMFile {
    class PEMEntry(val body: ByteArray, val startLabel: String, val endLabel: String)

    var entries = emptyList<PEMEntry>()

    constructor(rd: BufferedReader) {
        var startLabel: String? = null
        var endLabel: String? = null
        var body = ByteArrayOutputStream()
        rd.lines().forEach {
            if (startLabel == null) {
                val pos1 = it.indexOf("-----BEGIN ")
                if (pos1 >= 0) {
                    val pos2 = it.indexOf("-----", pos1+11)
                    startLabel = it.substring(pos1+11, pos2)
                }
            } else {
                val pos1 = it.indexOf("-----END ")
                if (pos1 >= 0) {
                    val pos2 = it.indexOf("-----", pos1+9)
                    endLabel = it.substring(pos1+9, pos2)
                    entries += PEMEntry(body.toByteArray(),
                                startLabel ?: "",
                                endLabel ?: "")

                    startLabel = null
                    endLabel = null
                    body.reset()
                } else {
                    body.write(Base64.decode(it, Base64.DEFAULT))
                }
            }
        }
    }

    fun length(): Int {
        return this.entries.size
    }

    fun getEntry(pos: Int): PEMEntry {
        return this.entries[pos]
    }
}