package com.jarroyo.sharedcode.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Person(val id :String, val name :String ,val age :String)