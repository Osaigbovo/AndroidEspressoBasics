package com.sqisland.android.espresso.hello

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Verify that a piece of data is shown. Scroll until an itemView containing that piece of data is displayed on the screen
class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val footer = findViewById<TextView>(R.id.footer)
        footer.setBackgroundColor(Color.LTGRAY)
        footer.visibility = View.GONE

        val listView = findViewById<ListView>(R.id.list)

        val items = arrayOfNulls<Item>(COUNT)
        for (i in 0 until COUNT) {
            items[i] = Item(i)
        }
        val adapter = ArrayAdapter<Item>(this,
                android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            footer.text = items[position].toString()
            footer.visibility = View.VISIBLE
        }
    }

    class Item(private val value: Int) {
        override fun toString(): String {
            return value.toString()
        }
    }

    companion object {
        private val COUNT = 30
    }
}
