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
            arrayOf(0b00111111, 0b00110000, 0b00000000, 0b00000000),
            arrayOf(0b00000000, 0b00001111, 0b00111100, 0b00000000),
            arrayOf(0b00000000, 0b00000000, 0b00000011, 0b00111111)
        )
        private val decodeShift = arrayOf(
            arrayOf(2, -4, 0, 0),
            arrayOf(0, 4, -2, 0),
            arrayOf(0, 0, 6, 0)
        )

        /**
         *  ASCII 文字 1 文字を、6 ビット数値 1 個に変換する。
         *
         *  @param input 変換元となる ASCII 文字
         *  @return input に対応する 6 ビット数値
         *  @exception IllegalArgumentException input が Base64 で未定義の文字の場合
         */
        fun c2b(input: Char): Byte {
            for (i in base64CodeTable) {
                if (i.charMin <= input && input <= i.charMax) {
                    return (i.codeMin + (input - i.charMin)).toByte()
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
         * 6 ビット数値 4 個を、8 ビット数値 3 個に変換する。
         *
         * @param input 6 ビット数値列（4 個）
         * @return 変換結果の 8 ビット数値列（3 個）
         */
        fun pack64(input: Array<Byte>): Array<Byte> {
            if (input.size != decodeMask[0].size){
                throw IllegalArgumentException("illegal input length: %d".format(input.size))
            }
            val inputInt = input.map { it.toInt() }
            val output = arrayOf<Byte>(0, 0, 0)

            for (i in 0 until output.size) {
                var outputInt = 0
                for (j in 0 until input.size) {
                    val masked = inputInt[j] and decodeMask[i][j]
                    val shifted = if (decodeShift[i][j] >= 0)
                                            masked shl decodeShift[i][j]
                                    else
                                            masked ushr -decodeShift[i][j]
                    outputInt = outputInt or shifted
                }
                output[i] = outputInt.toByte()
            }
           return output
        }

        /**
         * 入力を Base64 デコードして得られるバイト列を返す。
         *
         * @param input Base64 文字列
         * @param flags 未実装（無視される）
         * @return input に対応するバイト列
         */
        fun decode(input: String, flags: Int): ByteArray {
            val inputCode = arrayOf<Byte>(0, 0, 0, 0)
            val output = mutableListOf<Byte>()

            for ((index, c) in input.withIndex()) {
                inputCode[index % inputCode.size] = c2b(c)
                if ((index+1) % inputCode.size == 0) {
                    pack64(inputCode).forEach { output.add(it) }
                }
            }
            return output.toByteArray()
        }

    }

}