package co.realizeit.myarchapp.remote

import android.util.Log
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

open class BaseRepository {
   public suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, error: String): T? {
        return try {


            val result = apiOutPut(call, error)
            var output: T? = null
            when (result) {
                is Output.Success ->
                    output = result.output
                is Output.Error -> Log.e("Error", "The $error and the ${result.exception}")
            }
            output
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }

    private suspend fun <T : Any> apiOutPut(
        call: suspend () -> Response<T>,
        error: String
    ): Output<T> {
        val response = call.invoke()
        return if (response.isSuccessful) {
            if (response.body() != null) {
                Output.Success(response.body()!!)
            } else {
                Output.Success(response.code() as T)
            }
        } else
            Output.Error(IOException("OOps .. Something went wrong due to  $error"))
    }
}