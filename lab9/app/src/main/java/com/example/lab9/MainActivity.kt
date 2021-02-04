package com.example.lab9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity(), ProjectInfoPresenter {

    private val projectsList: MutableList<Project> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchTextView: EditText = findViewById(R.id.searchTextView)
        val searchButton: Button = findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            val searchQuery = searchTextView.text.toString() + " in:readme"
            GlobalScope.launch {
                val t = URL("https://api.github.com/search/repositories?q=$searchQuery&per_page=5").readText()
                val json = JSONObject(t)
                val jsonArray = json.getJSONArray("items")
                for (i in 0 until jsonArray.length()) {
                    val o = jsonArray.getJSONObject(i)
                    val s = o.getString("name")
                }
                val projects = mutableListOf<Map<String, String>>()
                json.getJSONArray("items").let { array ->
                    projectsList.clear()
                    for (i in 0 until array.length()) {
                        array.getJSONObject(i).let { item ->
                            projects.add(hashMapOf(
                                "title" to item.getString("full_name"),
                                "description" to item.getString("description"),
                            ))
                            projectsList.add(Project(item))
                        }
                    }
                }
                MainScope().launch {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_projects_list, ProjectsListFragment(projects))
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_projects_list, ProjectsListFragment())
            .add(R.id.frame_project_info, ProjectInfoFragment())
            .commit()
    }

    override fun showProjectInfo(listItemIndex: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_project_info, ProjectInfoFragment(projectsList[listItemIndex]))
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null)
            .commit()
    }
}