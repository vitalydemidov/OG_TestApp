package ru.vitalydemidov.og_testapp.data.remote.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.vitalydemidov.og_testapp.data.remote.api.FixturesApi
import ru.vitalydemidov.og_testapp.di.PerAppScope

@Module
internal class ApiModule {

    @Provides
    @PerAppScope
    internal fun provideFixturesApi(retrofit: Retrofit): FixturesApi =
        retrofit.create<FixturesApi>(FixturesApi::class.java)

    @Provides
    @PerAppScope
    internal fun provideRetrofit(
        converter: Converter.Factory,
        callAdapter: CallAdapter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(converter)
            .addCallAdapterFactory(callAdapter)
            .build()
    }

    @Provides
    @PerAppScope
    internal fun provideGsonConverterFactory(gson: Gson): Converter.Factory =
        GsonConverterFactory.create(gson)

    @Provides
    @PerAppScope
    internal fun provideGson(): Gson = Gson()

    @Provides
    @PerAppScope
    internal fun provideCallAdapterFactory(): CallAdapter.Factory =
        RxJava2CallAdapterFactory.create()

    companion object {
        private const val API_BASE_URL = "https://storage.googleapis.com/cdn-og-test-api/test-task/"
    }

}