package jeevan.com.task.utils

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import jeevan.com.task.model.WorldPopulationModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


interface ApiInterface {

    @GET("jsonparsetutorial.txt")
    fun getImages(): io.reactivex.Observable<WorldPopulationModel>


    companion object {
        fun getClient(): ApiInterface {

            val retrofit = Retrofit.Builder()
                    .baseUrl("http://www.androidbegin.com/tutorial/")
                    .client(provideOkHttpClient())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(ApiInterface::class.java)
        }

        private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Log.i("logging text", message) }
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

        private fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                    .addInterceptor(provideHttpLoggingInterceptor())
                    .build()
        }
    }
}