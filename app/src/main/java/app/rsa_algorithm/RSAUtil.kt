package app.rsa_algorithm

import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.SecureRandom
import java.security.Security
import javax.crypto.Cipher

class RSAUtil {

    init {
        Security.addProvider(BouncyCastleProvider())
    }

    fun generateKeyPair(): KeyPair {
        // BC --> Bouncy Castle
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        // 2048 the size of the key to be generated in bits.
        // determines the strength of the encryption.
        keyPairGenerator.initialize(2048, SecureRandom())
        return keyPairGenerator.generateKeyPair()
    }


    fun encrypt(message : String , publicKey: PublicKey) : ByteArray {
        Security.removeProvider("BC")
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE , publicKey)
        return cipher.doFinal(message.toByteArray())
    }

    fun decrypt(encryptedMessage: ByteArray, privateKey: PrivateKey): String {
        Security.removeProvider("BC")
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding" )
        cipher.init(Cipher.DECRYPT_MODE , privateKey)
        val decryptedByte = cipher.doFinal(encryptedMessage)
        return String(decryptedByte)
    }



}