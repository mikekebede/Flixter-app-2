package com.example.flixter2
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val PERSON_EXTRA = "PERSON_EXTRA"
private const val TAG = "flixter2Adapter"

class flixter2Adapter(private val context: Context,  private val people: List<Person>) :
    RecyclerView.Adapter<flixter2Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.artist_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: Get the individual article and bind to holder
        val famous_person = people[position]
        holder.bind(famous_person)
    }

    override fun getItemCount() = people.size


    inner class ViewHolder(personView: View) : RecyclerView.ViewHolder(personView),
        View.OnClickListener {

        var person_item:Person? = null
        val person_name = personView.findViewById<View>(R.id.name) as TextView
        val person_poster = personView.findViewById<View>(R.id.person_image) as ImageView


        init {
            personView.setOnClickListener(this)
        }


        fun bind(famous_person: Person) {
            person_name.text = famous_person.name

            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + famous_person.actorImageUrl)
                .into(person_poster)
        }
        override fun onClick(v: View?) {
            // TODO: Get selected article
       val famous_person = people[adapterPosition]

        val intent = Intent(context,PersonDetail::class.java)
            intent.putExtra(PERSON_EXTRA, famous_person)
            context.startActivity(intent)

            // TODO: Navigate to Details screen and pass selected article*/
        }
    }}





