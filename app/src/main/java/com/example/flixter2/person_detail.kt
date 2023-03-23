package com.example.flixter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class PersonDetail : AppCompatActivity() {
    private lateinit var movieImageView: ImageView
    private lateinit var famousWorkTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var descriptionTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_detail)
        movieImageView=findViewById(R.id.movie_image)
        famousWorkTextView= findViewById<TextView>(R.id.person_name)
        nameTextView= findViewById<TextView>(R.id.famous_work)
        descriptionTextView= findViewById<TextView>(R.id.description)



        val celebrity = intent.getSerializableExtra(PERSON_EXTRA, ) as Person
        famousWorkTextView.text=celebrity.known_for?.get(0)!!.original_name
        nameTextView.text=celebrity.name
        descriptionTextView.text=celebrity.known_for?.get(0)!!.overview


       Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" +celebrity.known_for?.get(0)!!.posterPath)
            .into(movieImageView)

    }
}