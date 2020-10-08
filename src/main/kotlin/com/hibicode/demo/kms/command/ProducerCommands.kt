package com.hibicode.demo.kms.command

import com.hibicode.demo.kms.crypto.EncryptAdapter
import com.hibicode.demo.kms.producer.MyKafkaProducer
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@ShellComponent
class ProducerCommands(
        private val myKafkaProducer: MyKafkaProducer,
        private val encryptAdapter: EncryptAdapter
) {

    @ShellMethod("byte")
    fun sendByteArray(@ShellOption payload: String) {
        val encrypted = encryptAdapter.apply(payload)
        myKafkaProducer.send(encrypted)
    }

    @ShellMethod("string")
    fun sendString(@ShellOption payload: String) {
        myKafkaProducer.send(payload)
    }

}