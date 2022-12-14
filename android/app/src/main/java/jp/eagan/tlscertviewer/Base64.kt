package jp.eagan.tlscertviewer

class Base64 {
    class CodeRange(val charMin: Char, val charMax: Char, val codeMin: Byte, val codeMax: Byte)

    companion object {
        private val base64CodeTable = arrayOf(
            CodeRange('A', 'Z', 0b000000, 0b011001),
            CodeRange('a', 'z', 0b011010, 0b110011),
            CodeRange('0', '9', 0b110100, 0b111101),
            CodeRange('+', '+', 0b111110, 0b111110),
            CodeRange('/', '/', 0b111111, 0b111111)
        )

        private val decodeMask = arrayOf(
            arrayOf(0b00000000, 0b00000000),
            arrayOf(0b00111111, 0b00110000),
            arrayOf(0b00001111, 0b00111100),
            arrayOf(0b00000011, 0b00111111)
        )
        private val decodeShift = arrayOf(
            arrayOf(0, 0),
            arrayOf(2, 4),
            arrayOf(4, 2),
            arrayOf(6, 0)
        )

        /**
         *  ASCII 文字 1 文字を、6 ビット数値 1 個に変換する。
         *
         *  @param input 変換元となる ASCII 文字
         *  @return input に対応する 6 ビット数値
         *  @exception IllegalArgumentException input が Base64 で未定義の文字の場合
         */
        fun c2b(input: Char): Int {
            for (i in base64CodeTable) {
                if (i.charMin <= input && input <= i.charMax) {
                    return (i.codeMin + (input - i.charMin))
                }
            }
            throw IllegalArgumentException("illegal character for base64: %c".format(input))
        }

        /**
         * 6 ビット数値 1 個を、ASCII 文字に変換する。
         *
         * @param input 変換元となる 6 ビット数値
         * @return input に対応する ASCII 文字
         * @exception IllegalArgumentException input が 6 ビット数値の範囲外だった場合
         */
        fun b2c(input: Byte): Char {
            for (i in base64CodeTable) {
                if (i.codeMin <= input && input <= i.codeMax) {
                    return (i.charMin + (input - i.codeMin))
                }
            }
            throw IllegalArgumentException("illegal code for base64: %d".format(input))
        }

        /**
         * 入力を Base64 デコードして得られるバイト列を返す。
         *
         * @param input Base64 文字列
         * @param flags 未実装（無視される）
         * @return input に対応するバイト列
         */
        fun decode(input: String, flags: Int): ByteArray {
            val output = mutableListOf<Byte>()

            for ((index, c) in input.withIndex()) {
                if (index % 4 != 0 && c != '=') {
                    // 4 文字単位の 1 文字目は出力せず、そのまま（1 文字目 +）2 文字目の処理に移る
                    // = の場合は出力せずそのままスキップ（末尾の端数処理に使われる）
                    val b1 = c2b(input[index - 1])
                    val b2 = c2b(input[index])
                    val b = ((b1 and decodeMask[index % 4][0]) shl decodeShift[index % 4][0]) or
                            ((b2 and decodeMask[index % 4][1]) ushr decodeShift[index % 4][1])
                    output.add(b.toByte())
                }
            }
            return output.toByteArray()
        }

    }

}