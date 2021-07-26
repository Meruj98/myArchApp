package co.realizeit.myarchapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.realizeit.myarchapp.model.Article
import co.realizeit.myarchapp.model.ArticlesResponse
import co.realizeit.myarchapp.remote.WebApiService
import co.realizeit.myarchapp.remote.WebRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel : ViewModel() {
    private val webRepo: WebRepo = WebRepo(WebApiService.webApi)
    private val parentJob = Job()
    private val coroutineContext = parentJob + Dispatchers.IO
    private val scope = CoroutineScope(coroutineContext)
    private var compositeDisposable = CompositeDisposable()

    val articlesLiveData = MutableLiveData<ArticlesResponse?>()


    fun getArticles(q: String, from: String, sortBy: String, apiKey: String) {
        scope.launch {
            val response = webRepo.getEveryThingAboutTesla(q, from, sortBy, apiKey)
            articlesLiveData.postValue(response)
        }
    }

    // TODO: Implement the ViewModel
}