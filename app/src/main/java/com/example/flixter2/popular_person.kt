package com.example.flixter2
import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Keep
@Serializable
data class Person (

   @SerializedName("profile_path")
   var actorImageUrl : String? = null,

   @SerializedName("adult")
   var adult: Boolean? = null,

   @SerializedName("id")
   var id: Int? = null,

   @SerializedName("known_for")
   var known_for: List<KnownFor>? = null,

   @SerializedName("name")
   var name: String? = null
)  : java.io.Serializable

@Keep
@Serializable
data class KnownFor (
   @SerializedName("id")
   val id: Int,

   @SerializedName("media_type")
   val mediaType: String,

   @SerializedName("original_name")
   val original_name: String,

   @SerializedName("title")
   var title: String? = null,

   @SerializedName("overview")
   var overview: String? = null,

   @SerializedName("poster_path")
   val posterPath: String?,

   @SerializedName("backdrop_path")
   val backdropPath: String?
)  : java.io.Serializable