package com.jarroyo.sharedcode.domain

import com.jarroyo.sharedcode.domain.model.Person
import com.jarroyo.sharedcode.domain.usecase.base.UseCase
import com.jarroyo.sharedcode.source.network.PersonApi
import de.moviesmpp.domain.model.Either
import de.moviesmpp.domain.model.Failure
import de.moviesmpp.domain.model.Success

class GetPerson(private val personApi: PersonApi) : UseCase<Person, UseCase.None>() {

    override suspend fun run(params: None): Either<Exception, Person> {
        return try {
            val person = personApi.getPerson()
            Success(person)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}