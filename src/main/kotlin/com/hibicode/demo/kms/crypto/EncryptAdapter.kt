package com.hibicode.demo.kms.crypto

import com.amazonaws.encryptionsdk.AwsCrypto
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class EncryptAdapter(
        private val crypto: AwsCrypto,
        private val masterKeyProvider: KmsMasterKeyProvider) {

    fun apply(rawValue: String): ByteArray =
            rawValue.toByteArray(StandardCharsets.UTF_8)
                    .let { crypto.encryptData(masterKeyProvider, it).result }

}