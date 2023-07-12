package com.example.taskkotlin.ui.onBoar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskkotlin.data.local.Pref
import com.example.taskkotlin.databinding.FragmentOnBordBinding
import com.example.taskkotlin.ui.onBoar.adapter.OnBordAdapter
import me.relex.circleindicator.CircleIndicator3

class OnBordFragment : Fragment() {

    private lateinit var binding: FragmentOnBordBinding

    //    private lateinit var adapter: OnBordAdapter
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = Pref(requireContext())

        val adapter = OnBordAdapter() {
            pref.userSeen()
            findNavController().navigateUp()
        }
        binding.viewPager.adapter = adapter
        val indicator: CircleIndicator3 = binding.indicator
        val viewPager = binding.viewPager
        indicator.setViewPager(viewPager)
    }
}