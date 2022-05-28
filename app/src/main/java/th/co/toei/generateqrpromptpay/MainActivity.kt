package th.co.toei.generateqrpromptpay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import th.co.toei.generate.qr.prompt.pay.PromptPayGenerate
import th.co.toei.generate.qr.prompt.pay.extensions.toQrPromptPay
import th.co.toei.generateqrpromptpay.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultBitmap = PromptPayGenerate.generate("0991237283", "1000.00").toQrPromptPay(600)

        binding.imgQr.setImageBitmap(resultBitmap)
    }
}
