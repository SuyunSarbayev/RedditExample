package kz.suyun.redditexample.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kz.suyun.redditexample.data.Api
import kz.suyun.redditexample.view.posts.PostsRepository
import kz.suyun.redditexample.view.posts.PostsViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        PostsViewModel(get())
    }
}

val repositoryModule = module {
    single {
        PostsRepository(get())
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
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder().build()
    }

    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), get()) }
}