package co.realizeit.myarchapp.model

import com.squareup.moshi.Json

data class Source(
    @Json(name = "String")
    val id: String,
    @Json(name = "name")
    val name: String,

)
