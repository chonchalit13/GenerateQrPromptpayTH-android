package com.toei.promptpay.extensions

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

fun String.convertToPhoneNumberCountryCode(): String {
    return when {
        this.startsWith("0") -> {
            this.replaceFirst("0", "66").replace("-", "")
        }
        this.contains("-") -> {
            this.replace("-", "")
        }
        else -> {
            this
        }
    }
}

fun String.toQrPromptPay(size: Int): Bitmap {
    val bitMatrix = QRCodeWriter().encode(this, BarcodeFormat.QR_CODE, size, size)
    val bitMatrixWidth = bitMatrix.width
    val bitMatrixHeight = bitMatrix.height

    val rawBitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_8888)

    for (y in 0 until bitMatrixHeight) {
        for (x in 0 until bitMatrixWidth) {
            rawBitmap.setPixel(
                x,
                y,
                if (bitMatrix.get(x, y)) Color.parseColor("#000000")
                else Color.parseColor("#FFFFFF")
            )
        }
    }

    val w = rawBitmap.width - 140
    val h = rawBitmap.height - 140


    return Bitmap.createBitmap(
        rawBitmap,
        70,
        70,
        w,
        h
    )
}
