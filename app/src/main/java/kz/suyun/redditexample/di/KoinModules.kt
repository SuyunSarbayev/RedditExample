package kz.suyun.redditexample.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kz.suyun.redditexample.data.Api
import kz.suyun.redditexample.view.posts.CharactersRepository
import kz.suyun.redditexample.view.posts.CharacterViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        CharacterViewModel(get())
    }
}

val repositoryModule = module {
    single {
        CharactersRepository(get())
    }
}

val apiModule = module {
    fun provideApi(retrofit: Retrofit): Api{
        return retrofit.create(Api::class.java)
    }

    single { provideApi(get()) }
}

val retrofitModule = module {
    fun provideGson(): Gson{
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .serializeNulls()
            .setLenient()
            .create()
    }

    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder().apply {
            interceptors().add(httpLoggingInterceptor)
        }.build()
    }

    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    single { provideHttpLoggingInterceptor() }
    single { provideGson() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get(), get()) }
}