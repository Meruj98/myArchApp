package co.realizeit.myarchapp.remote

import co.realizeit.myarchapp.model.Article
import co.realizeit.myarchapp.model.ArticlesResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMap
import retrofit2.Response

open class WebRepo(private val apiInterface: WebApiService.WebApiInterface) : BaseRepository() {

    suspend fun getEveryThingAboutTesla(
        q: String,
        from: String,
        sortBy: String,
        apiKey: String
    ): ArticlesResponse? {
        return safeApiCall(
            call = {
                apiInterface.getEverythingAboutTeslaAsync(q, from, sortBy, apiKey).await()
            },
            error = "Error loading data"
        )
    }
}