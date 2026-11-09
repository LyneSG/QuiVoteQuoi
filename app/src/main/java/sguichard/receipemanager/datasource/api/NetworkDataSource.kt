package sguichard.receipemanager.datasource.api

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import sguichard.receipemanager.data.Depute
import java.util.concurrent.TimeUnit
import kotlin.jvm.java

object NetworkDataSource {

    private const val BASE_URL = "https://www.civix.fr/api/v1/"

    val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val json = Json { ignoreUnknownKeys = true }

    val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
            // time out setting
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(25,TimeUnit.SECONDS)

    }.build()

    val groupesPolitiquesService: GroupesPolitiqueService by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(GroupesPolitiqueService::class.java)

    }

    val deputesService: DeputesService by lazy{
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(DeputesService::class.java)
    }

}
