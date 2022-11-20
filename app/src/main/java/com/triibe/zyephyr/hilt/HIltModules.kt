package com.triibe.zyephyr.hilt

import com.triibe.zyephyr.common.Constants
import com.triibe.zyephyr.data.remote.EcomAPI
import com.triibe.zyephyr.data.repository.OrderRepositoryImpl
import com.triibe.zyephyr.data.repository.ProductListRepositoryImpl
import com.triibe.zyephyr.data.repository.StoreListRepositoryImpl
import com.triibe.zyephyr.domain.repository.OrderRepository
import com.triibe.zyephyr.domain.repository.ProductListRepository
import com.triibe.zyephyr.domain.repository.StoreListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object HIltModules {


    @Provides
    @Singleton
    fun provideEcomAPI(): EcomAPI {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(EcomAPI::class.java)
    }

    @Provides
    fun provideStoreListRepository(ecomAPI: EcomAPI): StoreListRepository {
        return StoreListRepositoryImpl(ecomAPI)
    }
    @Provides
    fun provideProductListRepository(ecomAPI: EcomAPI): ProductListRepository {
        return ProductListRepositoryImpl(ecomAPI)
    }
    @Provides
    fun provideOrderRepository(ecomAPI: EcomAPI): OrderRepository {
        return OrderRepositoryImpl(ecomAPI)
    }
//
//


}