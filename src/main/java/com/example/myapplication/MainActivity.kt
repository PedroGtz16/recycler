package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery

class AdapterR(private val names: List<ParseObject>): RecyclerView.Adapter<NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_itesogram, parent, false)
        return NameViewHolder(view)
    }

    override fun getItemCount(): Int = names.size

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bind(names[position])
    }

}

class NameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nameTitle: TextView = view.findViewById(R.id.item_itesogram_name)
    // private val lastNameTitle: TextView = view.findViewById(R.id.item_title_last_name)
    //private val fecha: TextView = view.findViewById(R.id.item_title_fecha)
    private val userImage: TextView = view.findViewById(R.id.item_itesogram_image)

    fun bind(user: ParseObject) {
        nameTitle.text = user.getString("name")
        //lastNameTitle.text = user.getString("lastName")
        //fecha.text = user.getString("birthDate")
    }
}
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val query = ParseQuery.getQuery<ParseObject>("Feed")
        query.findInBackground(object : FindCallback<ParseObject> {


            override fun done(objects: List<ParseObject>, e: ParseException?) {
                var recycler = findViewById<RecyclerView>(R.id.item_material)
                if (e == null) {
                    recycler.adapter = AdapterR(objects)
                    recycler.LayoutManager = LinearLayoutManager(this)

                }
            }
        })

    }
}
