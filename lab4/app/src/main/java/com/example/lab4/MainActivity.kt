package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fruits = ArrayList<Fruit>()
        fruits.add(Fruit("Яблоко", "Зеленое и сочное"))
        fruits.add(Fruit("Лимон", "Желтый и витаминный"))
        fruits.add(Fruit("Апельсин", "Оранжевый и радостный"))
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = FruitAdapter(fruits)
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fruits = ArrayList<Fruit>()
        fruits.add(Fruit("Яблоко", "Зеленое и сочное"))
        fruits.add(Fruit("Лимон", "Желтый и витаминный"))
        fruits.add(Fruit("Апельсин", "Оранжевый и радостный"))
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = FruitAdapter(fruits)
    }*/

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val fruits = arrayOf("Апельсин", "Яблоко", "Груша", "Арбуз", "Манго")
        //val adapter = ArrayAdapter<String>(this,R.layout.my_list_item, fruits)
        val listView: ListView = findViewById(R.id.listView)
        *//*listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice, fruits)*//*

        // Подготовкаданных
        val fruits = ArrayList<Map<String, Any>>()
        var item: MutableMap<String, Any>
        item = HashMap()
        item["title"] = "Яблоко"
        item["info"] = "Зеленое и сочное"
        fruits.add(item)
        item = HashMap()
        item["title"] = "Лимон"
        item["info"] = "Желтыйивитаминный"
        fruits.add(item)
        item = HashMap()
        item["title"] = "Апельсин"
        item["info"] = "Оранжевый и радостный"
        fruits.add(item)

        // Подготовка адаптера
        val from = arrayOf<String>("title", "info")
        val to = intArrayOf(R.id.title, R.id.info)
        val adapter = SimpleAdapter(this,fruits,R.layout.my_list_item_advanced,from, to)

        listView.adapter= adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val textView= view.findViewById<TextView>(R.id.title)
            Toast.makeText(this,"Выбранфрукт" + textView.text,Toast.LENGTH_SHORT).show()
        }
    }*/
}