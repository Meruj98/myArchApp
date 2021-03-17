package co.realizeit.myarchapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.realizeit.myarchapp.R
import co.realizeit.myarchapp.model.Article
import co.realizeit.myarchapp.viewmodel.MainViewModel
import co.realizeit.myarchapp.viewmodel.MainViewModelProvider

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var rvArticles: RecyclerView? = null
    private val articles: MutableList<Article> = ArrayList()
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelProvider = MainViewModelProvider()
        viewModel = viewModelProvider.create(MainViewModel::class.java)
        getArticles()
        val adapter = ArticlesAdapter(articles)
        rvArticles = view?.findViewById(R.id.rvArticles)
        rvArticles?.layoutManager = LinearLayoutManager(context)
        rvArticles?.adapter = adapter
        viewModel.articlesLiveData.observe(viewLifecycleOwner, {
            if (it != null) {
                articles.addAll(it.articles)
                adapter.notifyDataSetChanged()
            }
        })

    }

    private fun getArticles() {
        viewModel.getArticles(
            "tesla",
            "2021-02-17",
            "publishedAt",
            "8fe92aeca42341e1a97f68365677de1d"
        )
    }

}