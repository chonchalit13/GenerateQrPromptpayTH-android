package com.toei.promptpay.extensions

import android.os.Build
import com.google.zxing.BinaryBitmap
import com.google.zxing.LuminanceSource
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.qrcode.QRCodeReader
import com.toei.promptpay.PromptPayGenerate
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(
    sdk = [Build.VERSION_CODES.S]
)
@RunWith(RobolectricTestRunner::class)
class StringExtensionUnitTest {

    @Test
    fun testConvertToPhoneNumberStart66NoDash_Case1() {
        val result = "099-111-2222".convertToPhoneNumberCountryCode()
        assertEquals("66991112222", result)
    }

    @Test
    fun testConvertToPhoneNumberStart66NoDash_Case2() {
        val result = "099-1112222".convertToPhoneNumberCountryCode()
        assertEquals("66991112222", result)
    }

    @Test
    fun testConvertToPhoneNumberStart66NoDash_Case3() {
        val result = "099111-2222".convertToPhoneNumberCountryCode()
        assertEquals("66991112222", result)
    }

    @Test
    fun testConvertToPhoneNumberStart66NoDash_Case4() {
        val result = "0991112222".convertToPhoneNumberCountryCode()
        assertEquals("66991112222", result)
    }

    @Test
    fun testConvertToPhoneNumberNoDash() {
        val result = "6699-111-2222".convertToPhoneNumberCountryCode()
        assertEquals("66991112222", result)
    }

    @Test
    fun testConvertToPhoneNumber() {
        val result = "66991112222".convertToPhoneNumberCountryCode()
        assertEquals("66991112222", result)
    }

    @Test
    fun testConvertStringToQrPromptPayBitmap() {
        val resultData = PromptPayGenerate.generate("0991237283", "1000.00")
        val resultBitmap = resultData.toQrPromptPay(600)

        val intArray = IntArray(resultBitmap.width * resultBitmap.height)
        resultBitmap.getPixels(
            intArray,
            0,
            resultBitmap.width,
            0,
            0,
            resultBitmap.width,
            resultBitmap.height
        )

        val source: LuminanceSource =
            RGBLuminanceSource(resultBitmap.width, resultBitmap.height, intArray)

        val bitmap = BinaryBitmap(HybridBinarizer(source))
        val result = QRCodeReader().decode(bitmap).toString()

        assertEquals(resultData, result)
        assertEquals(600, resultBitmap.width)
        assertEquals(600, resultBitmap.height)
    }
}
