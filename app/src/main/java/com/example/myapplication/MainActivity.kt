package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val query = ParseQuery.getQuery<ParseObject>("Feed")
        query.findInBackground(object : FindCallback<ParseObject> {

            var recycler = findViewById<RecyclerView>(R.id.item_material)


            override fun done(objects: MutableList<ParseObject>?, e: ParseException?) {
                if (e == null) {
                    recycler.adapter = AdapterR(objects)
                    recycler.LayoutManager = LinearLayoutManager(this)


                }
            }
        })

    }
}
