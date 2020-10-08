package com.hibicode.demo.kms.config

import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.encryptionsdk.AwsCrypto
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider
import com.hibicode.demo.kms.crypto.DecryptAdapter
import com.hibicode.demo.kms.crypto.EncryptAdapter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KmsConfig(
        @Value("\${amazon.kms.arn}") private val kmsArn: String) {

    @Bean
    fun masterKeyProvider(): KmsMasterKeyProvider =
            KmsMasterKeyProvider
                    .builder()
                    .withCredentials(ProfileCredentialsProvider("zup"))
                    .withKeysForEncryption(kmsArn)
                    .build();

    @Bean
    fun crypto() = AwsCrypto()

    @Bean
    fun encrypt(crypto: AwsCrypto, masterKeyProvider: KmsMasterKeyProvider): EncryptAdapter = EncryptAdapter(crypto, masterKeyProvider)

    @Bean
    fun decrypt(crypto: AwsCrypto, masterKeyProvider: KmsMasterKeyProvider): DecryptAdapter = DecryptAdapter(crypto, masterKeyProvider)

}