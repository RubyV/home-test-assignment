package com.example.home_test_travel.di

import android.content.Context
import com.example.home_test_travel.data.network.API
import com.example.home_test_travel.data.network.ApiConfig
import com.example.home_test_travel.data.preferences.AppPreferences
import com.google.gson.Gson
import com.hjq.gson.factory.GsonFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences(context)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonFactory.getSingletonGson()

    @Provides
    @Singleton
    fun provideApi(): API {
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .followRedirects(false)

        val baseUrl = ApiConfig.api()
        client.addInterceptor { chain ->
            val request = chain.request()
            val response: Response
            try {
                response = chain.proceed(request)
                if (response !== null) {
                    if (response.code == 302) {
                        Timber.d("exception ${response.body}")
                    }
                }
            } catch (e: UnknownHostException) {
                Timber.d("exception ${e.localizedMessage}")
                throw e
            } catch (e: Exception) {
                Timber.d("exception ${e.localizedMessage}")
                throw e
            }
            response
        }
        val okHttpClient = client
            .build()
        return Retrofit.Builder()
            .baseUrl(ApiConfig.api())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API::class.java)
    }
}