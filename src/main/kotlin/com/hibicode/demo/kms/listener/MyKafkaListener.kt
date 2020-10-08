package com.hibicode.demo.kms.listener

import com.hibicode.demo.kms.config.BYTE_ARRAY_TOPIC
import com.hibicode.demo.kms.crypto.DecryptAdapter
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class MyKafkaListener(
        private val decryptAdapter: DecryptAdapter
) {

    @KafkaListener(topics = [BYTE_ARRAY_TOPIC], groupId = "my-group")
    fun listenerByteArray(@Payload consumerRecord: ConsumerRecord<String, ByteArray>) {
        try {
            val value = consumerRecord.value()
            val resultado = decryptAdapter.apply(value)
            println("resultado: $resultado")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}