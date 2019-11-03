package injection.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import injection.RxJava2SchedulerCallAdapterFactory
import io.reactivex.schedulers.Schedulers
import network.PostApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import utils.AUTH
import utils.BASE_URL

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    /**
     * Provides the a client which introduces the required authorization header
     */
    private val client = OkHttpClient.Builder().addNetworkInterceptor {
        it.proceed(it.request().newBuilder().addHeader("Authorization", AUTH).build())
    }.build()

    /**
     * Calls an adapter for Json parsing in Kotlin
     */
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    /**
     * Builds and provides the Retrofit interface
     * @return the retrofit interface.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2SchedulerCallAdapterFactory(client))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}