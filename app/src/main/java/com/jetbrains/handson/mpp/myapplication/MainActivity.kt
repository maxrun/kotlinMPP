package com.jetbrains.handson.mpp.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jarroyo.kotlinmultiplatform.platformName
import com.jarroyo.sharedcode.di.InjectorCommon
import com.jarroyo.sharedcode.domain.model.Person
import com.jarroyo.sharedcode.presentation.profile.PersonView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PersonView {

    private val presenter by lazy { InjectorCommon.providePersonPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val str = platformName()
        txtView.text = str
        initialUI()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun showMessage( message : String ) {
        Toast.makeText(getApplicationContext(), message ,Toast.LENGTH_SHORT).show();
    }

    fun initialUI(){
        button.setOnClickListener { _ ->
            presenter.getPerson()
        }
    }

}
