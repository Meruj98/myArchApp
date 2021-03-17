package co.realizeit.myarchapp.remote


import co.realizeit.myarchapp.model.ArticlesResponse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

object WebApiService {
    private var logging = HttpLoggingInterceptor()
    private const val AUTH_HEADER_NAME = "Authorization"

    private val interceptor = Interceptor { chain ->
        //AppPreferences.accessToken
        val token = ""
        val url = chain.request().url.newBuilder().build()
        var request: Request? = null
        request = if (token != null) {
            chain.request()
                .newBuilder()
                .addHeader(AUTH_HEADER_NAME, "Bearer $token")
                .url(url)
                .build()
        } else {
            chain.request()
                .newBuilder()
                .url(url)
                .build()
        }


        chain.proceed(request)
    }

    private fun getRetrofit(): Retrofit {

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val apiClient =
            OkHttpClient().newBuilder().addInterceptor(interceptor).addNetworkInterceptor(
                logging
            ).build()
        return Retrofit.Builder().client(apiClient)
            .baseUrl(AppPreferences.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val webApi: WebApiInterface = getRetrofit().create(WebApiInterface::class.java)

    interface WebApiInterface {
        @GET("everything")
        fun getEverythingAboutTeslaAsync(
            @Query("q")q:String,
            @Query("from")from:String,
            @Query("sortBy")sortBy:String,
            @Query("apiKey")apiKey:String,
        ):Deferred<Response<ArticlesResponse>>
    }
}