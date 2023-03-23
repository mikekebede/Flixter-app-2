package com.example.flixter2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers
import com.google.gson.Gson

import android.content.ContentValues.TAG
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

//private const val SEARCH_API_KEY = BuildConfig.API_KEY

private const val PERSON_SEARCH_URL =
    "https://api.themoviedb.org/3/person/popular?api_key=9978500a9e2141acf635792947871c19"

class MainActivity : AppCompatActivity() {

    private val people = mutableListOf<Person>()
    private lateinit var celebrityRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        celebrityRecyclerView = findViewById(R.id.rvFamousPerson)
        // TODO: Set up ArticleAdapter with articles
        val celebrityAdapter = flixter2Adapter(this, people )
        celebrityRecyclerView.adapter = celebrityAdapter

        celebrityRecyclerView.layoutManager = GridLayoutManager(this, 2)

        val client = AsyncHttpClient()
        client.get(PERSON_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e( "main activity parsing:", " failed to pull people")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val resultsJSON = json.jsonObject.getJSONArray("results") as JSONArray
                val celebrityRawJson: String = resultsJSON.toString()
                val gson = Gson()
                val arrayCelebrityType = object : TypeToken<List<Person>>() {}.type
                val models : List<Person> = gson.fromJson(celebrityRawJson, arrayCelebrityType)
                Log.e( "success parsing:", celebrityRawJson)

              models.let { list ->
                    people.addAll(list)

                    // Reload the screen
                    celebrityAdapter.notifyDataSetChanged()
              }


                /*for (i in 0 until resultsJSON.length()) {
                    val result = resultsJSON.getJSONObject(i)
                    val knownForArray = result.getJSONArray("known_for")
                    val knownForList = mutableListOf<KnownFor>()
                    for (j in 0 until knownForArray.length()) {
                        val knownForJSON = knownForArray.getJSONObject(j)
                        val knownFor = gson.fromJson(knownForJSON.toString(), KnownFor::class.java)
                        knownForList.add(knownFor)

                        Log.d("PeopleFragment", knownFor.toString())
                    }
                    val person = gson.fromJson(result.toString(), Person::class.java)
                    person.known_for = knownForList
                    models.add(person)
                }

                recyclerView.adapter = PeopleRecyclerViewAdadpter(models, this@PeopleFragment)

             */
            }

        })

    }
}

