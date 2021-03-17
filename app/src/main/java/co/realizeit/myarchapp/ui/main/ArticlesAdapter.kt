package co.realizeit.myarchapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.realizeit.myarchapp.R
import co.realizeit.myarchapp.databinding.ItemArticlesBinding
import co.realizeit.myarchapp.model.Article
import co.realizeit.myarchapp.model.ArticlesResponse
import com.bumptech.glide.Glide

class ArticlesAdapter(private val articles: MutableList<Article>) :
    RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {
    companion object {
        @JvmStatic
        @BindingAdapter("urlToImage")
        fun loadImage(view: ImageView, urlToImage: String) {
            Glide.with(view.context).load(urlToImage).into(view)
        }
    }

    class ArticlesViewHolder(val binding: ItemArticlesBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemArticlesBinding>(
            layoutInflater,
            R.layout.item_articles,
            parent,
            false
        )
        return ArticlesViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.binding.article = articles[position]
        holder.binding.executePendingBindings()

    }

    override fun getItemCount(): Int {
        return articles.size
    }
}