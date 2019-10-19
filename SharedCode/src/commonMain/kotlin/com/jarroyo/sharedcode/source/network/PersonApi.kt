package com.jarroyo.sharedcode.source.network

import com.jarroyo.sharedcode.domain.model.Person
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class PersonApi {

    private val httpClient = HttpClient()

    suspend fun getPerson() : Person {
        lateinit var person : Person
        val url = "http://www.mocky.io/v2/5daadc2a3100004b00becdcc"
        val json = httpClient.get<String>(url)
        person = Json.parse(Person.serializer(), json)
        return person
    }

}