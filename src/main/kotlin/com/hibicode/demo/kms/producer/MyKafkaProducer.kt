package com.hibicode.demo.kms.producer

import com.hibicode.demo.kms.config.BYTE_ARRAY_TOPIC
import com.hibicode.demo.kms.config.STRING_TOPIC
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class MyKafkaProducer(
        private val kafkaTemplate: KafkaTemplate<String, ByteArray>,
        private val byteArrayKafkaTemplate: KafkaTemplate<String, String>
) {

    fun send(payload: ByteArray) {
        kafkaTemplate.send(BYTE_ARRAY_TOPIC, payload)
    }

    fun send(payload: String) {
        byteArrayKafkaTemplate.send(STRING_TOPIC, payload)
    }


}