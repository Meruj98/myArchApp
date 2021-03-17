package co.realizeit.myarchapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.realizeit.myarchapp.model.ArticlesResponse
import co.realizeit.myarchapp.remote.WebApiService
import co.realizeit.myarchapp.remote.WebRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val webRepo: WebRepo = WebRepo(WebApiService.webApi)
    private val parentJob = Job()
    private val coroutineContext = parentJob + Dispatchers.IO
    private val scope = CoroutineScope(coroutineContext)

    val articlesLiveData = MutableLiveData<ArticlesResponse?>()


    fun getArticles(q: String, from: String, sortBy: String, apiKey: String) {
        scope.launch {
            val response = webRepo.getEveryThingAboutTesla(q, from, sortBy, apiKey)
            articlesLiveData.postValue(response)
        }
    }

    // TODO: Implement the ViewModel
}