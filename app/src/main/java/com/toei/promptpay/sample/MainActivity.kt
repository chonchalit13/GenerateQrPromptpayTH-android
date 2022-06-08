package com.toei.promptpay.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.toei.promptpay.PromptPayGenerate
import com.toei.promptpay.extensions.toQrPromptPay
import com.toei.promptpay.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultBitmap = PromptPayGenerate.generate("0991237283", "1000.00")?.toQrPromptPay(600)

        binding.imgQr.setImageBitmap(resultBitmap)
    }
}
