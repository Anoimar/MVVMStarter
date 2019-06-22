package com.thernat.starter.di

import android.app.Application
import androidx.room.Room
import com.thernat.starter.BuildConfig
import com.thernat.starter.api.ApiService
import com.thernat.starter.db.BasicDao
import com.thernat.starter.db.MVVMDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://www.mocky.io/v2/5cfaa84e3000000879e367c2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(createOkHttpClient())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): MVVMDatabase {
        return Room
            .databaseBuilder(app, MVVMDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBasicVoDao(db: MVVMDatabase): BasicDao {
        return db.basicDao()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if(BuildConfig.HTTP_LOGIN){
                addInterceptor(
                    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            }
        }
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

}
