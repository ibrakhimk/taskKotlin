package com.example.taskkotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskkotlin.App
import com.example.taskkotlin.R
import com.example.taskkotlin.data.model.Home
import com.example.taskkotlin.databinding.FragmentAddTaskBinding
import com.example.taskkotlin.ui.home.HomeFragment.Companion.TV_NAME
import com.example.taskkotlin.utils.toast

class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private var notes: Home? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        binding.btnSave.setOnClickListener {
            if (binding.etName.text.isEmpty() || binding.etNum.text.isEmpty()) {
                toast(requireContext(), "Поле не может быть пустым")
            } else {
                dataWork()
            }
        }
    }

    private fun dataWork() {
        if (binding.btnSave.text.equals(getString(R.string.UPDATE))) {
            upDate()
            findNavController().navigateUp()
        } else {
            saveData()
            findNavController().navigateUp()
        }
    }


    private fun upDate() {
        val result = notes?.copy(
            title = binding.etName.text.toString(),
            desc = binding.etNum.text.toString()
        )
        if (result != null) {
            App.db.dao().update(result)
        }
    }

    private fun saveData() {
        val home =
            Home(title = binding.etName.text.toString(), desc = binding.etNum.text.toString())
        App.db.dao().insert(home)
    }

    private fun initData() {
        notes = arguments?.getSerializable(TV_NAME) as Home?
        notes?.let {
            binding.btnSave.text = getString(R.string.UPDATE)
            binding.etName.setText(it.title)
            binding.etNum.setText(it.desc)
        }
    }
}
