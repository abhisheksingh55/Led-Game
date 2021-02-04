package com.led.domain.model

data class LedModel(
    var light: LedLight,
    val name: String
){
    companion object{
        fun create(name: String): LedModel{
            return LedModel(light = LedLight.OFF, name = name)
        }
    }
}