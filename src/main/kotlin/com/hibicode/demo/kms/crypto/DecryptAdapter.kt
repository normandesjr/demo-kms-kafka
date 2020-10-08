package com.hibicode.demo.kms.crypto

import com.amazonaws.encryptionsdk.AwsCrypto
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider
import org.springframework.stereotype.Component
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

@Component
class DecryptAdapter(
        private val crypto: AwsCrypto,
        private val masterKeyProvider: KmsMasterKeyProvider) {

    fun apply(encryptedData: ByteArray): String =
            crypto.decryptData(masterKeyProvider, encryptedData)
                    .let { it.result.toString(StandardCharsets.UTF_8) }

}