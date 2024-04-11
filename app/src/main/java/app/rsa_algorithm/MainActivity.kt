package app.rsa_algorithm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.rsa_algorithm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvMessage.text = getText()

    }

    private fun getText(): String {

       val rsaUtil = RSAUtil()
       val keyPair = rsaUtil.generateKeyPair()
       val publicKey = keyPair.public
       val privateKey = keyPair.private
       val originalMessage = "Omar"
       val encryptedMessage = rsaUtil.encrypt(originalMessage, publicKey)
       val decryptedMessage = rsaUtil.decrypt(encryptedMessage, privateKey)

        return "Original Message --> $originalMessage\n" +
                "encrypted Message --> $encryptedMessage\n" +
                "decrypted Message --> $decryptedMessage"
    }

}