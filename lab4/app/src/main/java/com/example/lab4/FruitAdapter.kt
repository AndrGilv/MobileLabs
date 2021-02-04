package com.example.lab4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FruitAdapter(val fruits: List<Fruit>) : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
    // Внутренний класс -содержит ссылку на объект списка
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    // Создание нового объекта списка по требованию
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.my_list_item_advanced, parent, false) as LinearLayout
        return ViewHolder(view)
    }

    // Наполнение существующего объекта holder новым содержимым

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val titleView = holder.view.findViewById<TextView>(R.id.title)
        titleView.text = fruits[position].Title
        val infoView = holder.view.findViewById<TextView>(R.id.info)
        infoView.text = fruits[position].Info}
    // Количество элементов в списке
    override fun getItemCount(): Int {
        return fruits.size
    }
}
