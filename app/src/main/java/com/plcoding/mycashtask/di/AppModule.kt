package com.plcoding.mycashtask.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.plcoding.mycashtask.BuildConfig
import com.plcoding.mycashtask.yjahz.data.api.Api
import com.plcoding.mycashtask.yjahz.data.data_source.AppDatabase
import com.plcoding.mycashtask.yjahz.data.data_source.UserDao
import com.plcoding.mycashtask.yjahz.data.model.user.User
import com.plcoding.mycashtask.yjahz.data.repository.CategoriesRepositoryImpl
import com.plcoding.mycashtask.yjahz.data.repository.LoginRepositoryImpl
import com.plcoding.mycashtask.yjahz.data.repository.PopularSellersRepositoryImpl
import com.plcoding.mycashtask.yjahz.data.repository.ProfileRepositoryImpl
import com.plcoding.mycashtask.yjahz.data.repository.RegisterRepositoryImpl
import com.plcoding.mycashtask.yjahz.data.repository.TrendingSellersRepositoryImpl
import com.plcoding.mycashtask.yjahz.data.util.decryptAES
import com.plcoding.mycashtask.yjahz.domain.repository.CategoriesRepository
import com.plcoding.mycashtask.yjahz.domain.repository.LoginRepository
import com.plcoding.mycashtask.yjahz.domain.repository.PopularSellersRepository
import com.plcoding.mycashtask.yjahz.domain.repository.ProfileRepository
import com.plcoding.mycashtask.yjahz.domain.repository.RegisterRepository
import com.plcoding.mycashtask.yjahz.domain.repository.TrendingSellersRepository
import com.plcoding.mycashtask.yjahz.domain.use_case.CategoriesUseCases
import com.plcoding.mycashtask.yjahz.domain.use_case.GetPopular
import com.plcoding.mycashtask.yjahz.domain.use_case.GetTrending
import com.plcoding.mycashtask.yjahz.domain.use_case.GetUser
import com.plcoding.mycashtask.yjahz.domain.use_case.Login
import com.plcoding.mycashtask.yjahz.domain.use_case.Register
import com.plcoding.mycashtask.yjahz.domain.use_case.SellersUseCases
import com.plcoding.mycashtask.yjahz.domain.use_case.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.ByteString.Companion.decodeHex
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideOkHttp(
        userDao: UserDao,
    ): OkHttpClient = runBlocking {
        val user: User? = userDao.getUserProfile().first()
        OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer ${user?.token?.decryptAES()}"
                )
                .build()
            chain.proceed(request)
        }).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): Api = retrofit.create(Api::class.java)


    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        api: Api,
        userDao: UserDao
    ): LoginRepository {
        return LoginRepositoryImpl(api = api, dao = userDao)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(
        api: Api,
        userDao: UserDao
    ): RegisterRepository {
        return RegisterRepositoryImpl(api = api, dao = userDao)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        userDao: UserDao
    ): ProfileRepository {
        return ProfileRepositoryImpl(dao = userDao)
    }

    @Provides
    @Singleton
    fun providePopularRepository(
        api: Api
    ): PopularSellersRepository {
        return PopularSellersRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideTrendingRepository(
        api: Api
    ): TrendingSellersRepository {
        return TrendingSellersRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCategoriesRepository(
        api: Api
    ): CategoriesRepository {
        return CategoriesRepositoryImpl(api)
    }

    @Provides
    fun provideUserDao(
        db: AppDatabase
    ): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideUserUseCases(
        loginRepo: LoginRepository,
        regRepo: RegisterRepository,
        profileRepository: ProfileRepository
    ): UsersUseCases {
        return UsersUseCases(
            login = Login(loginRepo),
            register = Register(regRepo),
            profile = GetUser(profileRepository)
        )
    }

    @Provides
    @Singleton
    fun provideSellersUseCases(
        popularSellerRepository: PopularSellersRepository,
        trendingSellersRepository: TrendingSellersRepository
    ): SellersUseCases {
        return SellersUseCases(
            getPopular = GetPopular(popularSellerRepository),
            getTrending = GetTrending(trendingSellersRepository)
        )
    }

    @Provides
    @Singleton
    fun provideCategoriesUseCases(
        repository: CategoriesRepository
    ): CategoriesUseCases {
        return CategoriesUseCases(repository)
    }

    @Provides
    @Singleton
    fun provideUrl(): String = BuildConfig.BASE_URL

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope = CoroutineScope(SupervisorJob())

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ApplicationScope
}