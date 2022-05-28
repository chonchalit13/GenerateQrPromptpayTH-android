package th.co.toei.generate.qr.prompt.pay

import th.co.toei.generate.qr.prompt.pay.extensions.convertToPhoneNumberCountryCode

class PromptPayGenerate {

    companion object {
        private const val ppPayloadVersion = "000201"
        private const val ppPaymentMethod = "010211"
        private const val ppMerchantAid = "29370016A000000677010111"
        private var ppMerchantAccountId = ""
        private const val ppCountry = "5802TH"
        private const val ppCurrency = "5303764"
        private var ppAmount = ""
        private const val ppCheckSum = "6304"

        fun generate(accountNumber: String, amount: String = ""): String {
            ppMerchantAccountId = generateMerchantAccountId(accountNumber = accountNumber)

            if (amount.isNotEmpty()) {
                ppAmount = String.format("54%02d%s", amount.length, amount)
            }

            val generatePromptPay =
                "$ppPayloadVersion$ppPaymentMethod$ppMerchantAid$ppMerchantAccountId$ppCountry$ppCurrency$ppAmount$ppCheckSum"

            return "$generatePromptPay${checkSum(generatePromptPay)}"
        }

        private fun generateMerchantAccountId(accountNumber: String): String {
            return when (accountNumber.length) {
                13 -> // id card
                    "0213$accountNumber"
                10 -> // mobile phone
                    "011300${accountNumber.convertToPhoneNumberCountryCode()}"
                else -> ""
            }
        }

        private fun checkSum(value: String): String {
            var crc = 0xFFFF          // initial value
            val polynomial = 0x1021   // 0001 0000 0010 0001  (0, 5, 12)
            val bytes = value.toByteArray()
            for (b in bytes) {
                for (i in 0..7) {
                    val bit = b shr 7 - i and 1 == 1
                    val c15 = crc shr 15 and 1 == 1
                    crc = crc shl 1
                    if (c15 xor bit) crc = crc xor polynomial
                }
            }
            crc = crc and 0xffff
            return String.format("%04x", crc).uppercase()
        }

        private infix fun Byte.shr(shift: Int): Int = this.toInt() shr shift
    }
}
