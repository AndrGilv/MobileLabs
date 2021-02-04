package com.example.lab9

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ScrollView
import android.widget.SimpleAdapter


class ProjectsListFragment(
    private val projectList: MutableList<Map<String, String>> = arrayListOf()
) : Fragment() {

    private lateinit var projectInfoPresenter: ProjectInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_projects_list, container, false)
        val listOptions = view.findViewById<ListView>(R.id.project_list_scroll_view)
        listOptions.adapter = SimpleAdapter(
            view.context,
            projectList,
            R.layout.projects_list_item,
            arrayOf("title", "description"),
            intArrayOf(R.id.projectTitleTextView, R.id.projectDescriptionTextView)
        )
        listOptions.setOnItemClickListener { parent, view, position, id ->
            projectInfoPresenter.showProjectInfo(position)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        projectInfoPresenter = context as ProjectInfoPresenter
    }
}