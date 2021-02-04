package com.example.lab81

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView


class LeftFragment : Fragment() {

    private lateinit var operationChanger: OperationChanger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_left, container, false)
        val listOptions = view.findViewById<ListView>(R.id.list_operations)
        listOptions.adapter = ArrayAdapter<String>(context!!,android.R.layout.simple_list_item_1,arrayOf("Умножение", "Сложение", "Вычетание"))
        listOptions.setOnItemClickListener { parent, view, position, id ->
            operationChanger.changeOperation(position)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        operationChanger = context as OperationChanger
    }
}