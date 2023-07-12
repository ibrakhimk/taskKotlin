package com.example.taskkotlin.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskkotlin.App
import com.example.taskkotlin.R
import com.example.taskkotlin.data.model.Home
import com.example.taskkotlin.databinding.FragmentHomeBinding
import com.example.taskkotlin.ui.home.adapter.HomeAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HomeAdapter(this::onLongClickListener,this::onClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.btnPlus.setOnClickListener {
            findNavController().navigate(R.id.addTaskFragment)
        }
        setData()
    }

    private fun onClick(home: Home) {
        findNavController().navigate(R.id.addTaskFragment, bundleOf(TV_NAME to home))
        setData()
    }

    private fun onLongClickListener(home: Home){
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle("Delete?")
        alert.setPositiveButton("Yes"){d,_,->
            App.db.dao().delete(home)
            setData()
            d.dismiss()
        }
        alert.setNegativeButton("No"){d,_,->
            d.dismiss()
        }
        alert.create().show()
    }

    private fun setData() {
        val tasks = App.db.dao().getAll()
        adapter.addTasks(tasks)
    }

    companion object {
        const val TV_NAME = "name"
        const val TV_NUMBER = "number"
    }
}