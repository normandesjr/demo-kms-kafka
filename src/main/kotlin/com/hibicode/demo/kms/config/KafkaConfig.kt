package com.hibicode.demo.kms.config

import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.common.serialization.ByteArraySerializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate

const val STRING_TOPIC = "my-string-topic"
const val BYTE_ARRAY_TOPIC = "my-bytearray-topic"

@Configuration
class KafkaConfig(
        private val properties: KafkaProperties
) {

    @Bean
    fun myByteArrayTopic() = NewTopic(BYTE_ARRAY_TOPIC, 1, 1)

    @Bean
    fun myStringTopic() = NewTopic(STRING_TOPIC, 1, 1)

    @Bean
    @Primary
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        val factory: DefaultKafkaProducerFactory<String, String>
                = DefaultKafkaProducerFactory(this.properties.buildProducerProperties())
        return KafkaTemplate<String, String>(factory)
    }

    @Bean
    fun byteArrayKafkaTemplate(): KafkaTemplate<String, ByteArray> {
        val factory: DefaultKafkaProducerFactory<String, ByteArray>
                = DefaultKafkaProducerFactory(this.properties.buildProducerProperties())
        factory.setValueSerializer(ByteArraySerializer())
        return KafkaTemplate<String, ByteArray>(factory)
    }

}