package com.example.taskkotlin.ui.proFile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.taskkotlin.data.local.Pref
import com.example.taskkotlin.databinding.FragmentProFileBinding
import com.example.taskkotlin.utils.loadImage
import com.google.firebase.auth.FirebaseAuth


class ProFileFragment : Fragment() {

    private lateinit var binding: FragmentProFileBinding
    private lateinit var pref: Pref
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                val uri: Uri? = it.data?.data
                pref.saveImage(uri.toString())
                binding.imgView.loadImage(uri.toString())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProFileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPref()
        saveEditText()
        saveImage()
        binding.btnExit.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            activity?.finish()
        }
    }

    private fun saveImage() {
        binding.imgView.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }
        binding.imgView.loadImage(pref.getImage())
    }

    private fun saveEditText() {
        binding.etText.setText(pref.getUserEdit())
        binding.btnGet.setOnClickListener {
            pref.setUserEdit(binding.etText.text.toString())
        }
    }

    private fun initPref() {
        pref = Pref(requireContext())
    }
}
