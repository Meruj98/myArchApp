package co.realizeit.myarchapp.remote

import co.realizeit.myarchapp.model.Article
import co.realizeit.myarchapp.model.ArticlesResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMap

open class WebRepo(private val apiInterface: WebApiService.WebApiInterface) : BaseRepository() {

    fun getEveryThingAboutTesla(
        q: String,
        from: String,
        sortBy: String,
        apiKey: String
    ): Observable<ArticlesResponse> {
        return  apiInterface.getEverythingAboutTeslaAsync(q, from, sortBy, apiKey)
    }
}