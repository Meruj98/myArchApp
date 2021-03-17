package co.realizeit.myarchapp.remote

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    public const val API_KEY = "8fe92aeca42341e1a97f68365677de1d"
    public const val BASE_URL = "http://newsapi.org/v2/"
    private const val KEY_ACCESS_TOKEN = "key_access_token"


    @SuppressLint("CommitPrefEdits")
    fun setUp(context: Context) {
        sharedPreferences =
            context.getSharedPreferences("<YOUR_APP_NAME>.sharedprefs", Context.MODE_PRIVATE)
        editor = sharedPreferences!!.edit()

    }

    var accessToken: String?
        get() = sharedPreferences?.getString(KEY_ACCESS_TOKEN, null)
        set(value) = editor?.putString(KEY_ACCESS_TOKEN, value)?.apply()!!
}