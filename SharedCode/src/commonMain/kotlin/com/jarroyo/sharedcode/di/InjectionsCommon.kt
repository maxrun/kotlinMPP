package com.jarroyo.sharedcode.di

import com.jarroyo.kotlinmultiplatform.source.network.WeatherApi
import com.jarroyo.sharedcode.domain.GetPerson
import com.jarroyo.sharedcode.domain.usecase.location.deleteLocation.DeleteLocationUseCase
import com.jarroyo.sharedcode.domain.usecase.location.getLocationList.GetLocationMPPListUseCase
import com.jarroyo.sharedcode.domain.usecase.location.saveLocation.SaveLocationUseCase
import com.jarroyo.sharedcode.domain.usecase.weather.getWeather.GetWeatherByNameUseCase
import com.jarroyo.sharedcode.domain.usecase.weather.getWeatherList.GetWeatherListUseCase
import com.jarroyo.sharedcode.presentation.ProfilePresenter
import com.jarroyo.sharedcode.presentation.WeatherPresenter
import com.jarroyo.sharedcode.presentation.profile.PersonPresenter
import com.jarroyo.sharedcode.repository.LocationRepository
import com.jarroyo.sharedcode.repository.WeatherRepository
import com.jarroyo.sharedcode.source.disk.DbArgs
import com.jarroyo.sharedcode.source.network.PersonApi
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object InjectorCommon {

    /**
     * WEATHER
     */
    private val weatherApi: WeatherApi by lazy { WeatherApi() }

    private val personApi : PersonApi  by lazy { PersonApi() }

    val getPerson : GetPerson
        get() = GetPerson(personApi)

    private val weatherRepository: WeatherRepository by lazy {
        WeatherRepository(weatherApi)
    }

    fun provideGetWeatherListUseCase(): GetWeatherListUseCase {
        return GetWeatherListUseCase(weatherRepository)
    }

    fun provideGetWeatherUseCase(): GetWeatherByNameUseCase {
        return GetWeatherByNameUseCase(weatherRepository)
    }

    /**
     * LOCATION
     */
    lateinit var mDbArgs: DbArgs

    private val locationRepository: LocationRepository by lazy {
        LocationRepository(mDbArgs)
    }

    fun provideGetLocationMPPUseCase(dbArgs: DbArgs): GetLocationMPPListUseCase {
        mDbArgs = dbArgs
        return GetLocationMPPListUseCase(locationRepository)
    }

    fun provideDeleteLocationUseCase(dbArgs: DbArgs): DeleteLocationUseCase {
        mDbArgs = dbArgs
        return DeleteLocationUseCase(locationRepository)
    }

    fun provideSaveLocationUseCase(dbArgs: DbArgs): SaveLocationUseCase {
        mDbArgs = dbArgs
        return SaveLocationUseCase(locationRepository)
    }

    /**
     * PRESENTER
     */
    fun provideProfilePresenter(dbArgs: DbArgs): ProfilePresenter {
        return ProfilePresenter(provideGetLocationMPPUseCase(dbArgs), provideGetWeatherUseCase(), provideSaveLocationUseCase(dbArgs))
    }

    fun provideWeatherPresenter(): WeatherPresenter {
        return WeatherPresenter(provideGetWeatherUseCase())
    }

    fun providePersonPresenter() : PersonPresenter {
        return PersonPresenter(getPerson)
    }

}
