package com.example.lab8

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView


class LeftFragment : Fragment() {

    private lateinit var mainContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_left, container, false)
        val listOptions = view.findViewById<ListView>(R.id.list_fruits)
        listOptions.adapter = ArrayAdapter<String>(context!!,android.R.layout.simple_list_item_1,arrayOf("Апельсин", "Вишня", "Арбуз"))
        listOptions.setOnItemClickListener { parent, view, position, id ->
            (mainContext as OnDataListener).onData(position)
        }
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainContext = context
    }
}