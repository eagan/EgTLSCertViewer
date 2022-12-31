package jp.eagan.tlscertviewer

import java.io.InputStream

/**
 * @see https://www.itu.int/ITU-T/studygroups/com17/languages/
 */

class ASN1 (val tag: Int, val value: Any) {
    class TagEntry(val code: Int, val name: String) {
        companion object {
            val BOOLEAN = TagEntry(1, "Boolean")
            val INTEGER = TagEntry(2, "Integer")
            val BITSTRING = TagEntry(3, "Bitstring")
            val OCTETSTRING = TagEntry(4, "Octetstring")
            val NULL = TagEntry(5, "Null")
            val OBJECT_IDENTIFIER = TagEntry(6, "Object identifier")
            val OBJECT_DESCRIPTOR = TagEntry(7, "Object descriptor")
            val EXTERNAL_TYPE_AND_INSTANCE_OF_TYPE = TagEntry(8, "External type and instance-of type")
            val REAL = TagEntry(9, "Real")
            val ENUMERATED = TagEntry(10, "Enumerated")
            val EMBEDDED_PDB = TagEntry(11, "Embedded-pdb")
            val UTF8STRING = TagEntry(12, "UTF8String")
            val RELATIVE_OBJECT_IDENTIFIER = TagEntry(13, "Relative object identifier")
            val TIME = TagEntry(14, "The time")
            val PRINTABLE_STRING = TagEntry(19, "PrintableString")
        }
    }

    companion object {
        /**
         * tag が Constucted か判定する。
         * ITU-T X.690 の 8.1.2.3 Identifier Octet 参照。
         *
         * @param tag 判定対象のタグ
         * @return tag が Constructed なら True、Primitive なら False
         */
        fun isConstructed(tag: Int): Boolean {
            return (tag and 0x20) == 0x20
        }
        /**
         * DER 形式のストリームから length octets を取得する。
         *
         * @param input 入力ストリーム
         * @return 「length octets をデコードして得られた長さ」と「length octets 自体の長さ」の Pair
         */
        fun decodeDERLength(input: InputStream): Pair<Int, Int> {
            val len0 = input.read()
            return when (len0.toByte()) {
                in 0x00.toByte() .. 0x7f.toByte() -> {
                    // short form
                    Pair(len0, 1)
                }
                in 0x81.toByte() .. 0xff.toByte() -> {
                    // long form
                    var len = 0
                    for (i in 1 .. (len0 and 0x7f)) {
                        len = len * 256 + input.read()
                    }
                    Pair(len, (len0 and 0x7f) + 1)
                }
                else -> {
                    // indefinite form
                    Pair(0, 1)
                }
            }
        }

        /**
         * DER 形式のストリームから ASN.1 データを読み込む。
         *
         * @param input 入力ストリーム
         * @return 読み込んだ ASN.1 データ, ノード長 の Pair
         */
        fun decodeDERStream(input: InputStream): Pair<ASN1, Int> {
            val tag = input.read()
            val tagLen = 1
            // TODO tag number with subsequent octets (X.690 8.1.2.4.2)
            val (lenValue, lenLen) = decodeDERLength(input)

            if (isConstructed(tag)) {
                val valueList = mutableListOf<ASN1>()
                var len = 0
                while (len < lenValue) {
                    val (node, lenNode) = decodeDERStream(input)
                    valueList.add(node)
                    len += lenNode
                }
                val nodeThis = ASN1(tag, valueList)
                val lenThis = tagLen + lenLen + len
                return Pair(nodeThis, lenThis)
            } else {
                var lenThis = tagLen + lenLen + lenValue
                when (tag and 0x1f) {
                    TagEntry.BOOLEAN.code -> {
                        val value = input.read()
                        val nodeThis = ASN1(tag, (value != 0))
                        return Pair(nodeThis, lenThis)
                    }
                    TagEntry.INTEGER.code -> {
                        var value = input.read()
                        for (i in 1 until lenValue) {
                            value = (value shl 8) or (input.read() and 0xff)
                        }
                        val nodeThis = ASN1(tag, value)
                        return Pair(nodeThis, lenThis)
                    }
                    TagEntry.BITSTRING.code -> {
                        // TODO Bitstring type
                        throw NotImplementedError("Bitstring type")
                    }
                    TagEntry.OCTETSTRING.code -> {
                        // TODO Octetstring type
                        throw NotImplementedError("Octetstring type")
                    }
                    TagEntry.NULL.code -> {
                        val nodeThis = ASN1(tag, false) // false is dummy
                        return Pair(nodeThis, lenThis)
                    }
                    TagEntry.OBJECT_IDENTIFIER.code -> {
                        val value = mutableListOf<Int>()
                        var val0 = 0
                        var val1 = 0
                        for (i in 0 until lenValue) {
                            val0 = input.read()
                            val1 = (val1 shl 7) or (val0 and 0x7f)
                            if (val0 and 0x80 == 0) {
                                // 最上位ビットが 0 の場合は切れる
                                if (value.size == 0) {
                                    value.add(val1 / 40)
                                    value.add(val1 % 40)
                                } else {
                                    value.add(val1)
                                }
                                val1 = 0
                            }
                        }
                        val nodeThis = ASN1(tag, value.toIntArray())
                        return Pair(nodeThis, lenThis)
                    }
                    else -> {
                        throw NotImplementedError("Unknown type")
                    }
                }
            }
        }
    }
}