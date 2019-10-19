package com.jarroyo.sharedcode.presentation.profile

import com.jarroyo.sharedcode.ApplicationDispatcher
import com.jarroyo.sharedcode.domain.GetPerson
import com.jarroyo.sharedcode.domain.model.Person
import com.jarroyo.sharedcode.domain.usecase.base.UseCase
import com.jarroyo.sharedcode.presentation.BasePresenter
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PersonPresenter(private val getPerson: GetPerson, coroutineContext: CoroutineContext = ApplicationDispatcher) : BasePresenter<PersonView>(coroutineContext){

    override fun onViewAttached(view: PersonView) {}

    fun getPerson() {
        scope.launch {
            getPerson(
                UseCase.None,
                onSuccess = {
                    val msg = "Hello Mr. ${it.name}"
                    view?.showMessage(msg)
                },
                onFailure = {
                    val msg = it.message
                    view?.showMessage(msg!!)
                }
            )
        }
    }
}

interface PersonView {
    fun showMessage(msg : String)
}