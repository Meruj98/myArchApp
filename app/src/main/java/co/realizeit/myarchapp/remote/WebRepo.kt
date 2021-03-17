package co.realizeit.myarchapp.remote

import co.realizeit.myarchapp.model.ArticlesResponse

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