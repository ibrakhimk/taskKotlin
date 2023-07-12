package com.example.taskkotlin.ui.onBoar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskkotlin.data.model.OnBord
import com.example.taskkotlin.databinding.ItemOnBordBinding
import com.example.taskkotlin.utils.loadImage

class OnBordAdapter(private val onStartClick: () -> Unit) :
    Adapter<OnBordAdapter.OnBordViewHolder>() {

    private val data = arrayListOf<OnBord>(
        OnBord(
            "asd",
            "https://w7.pngwing.com/pngs/546/572/png-transparent-onboarding-computer-icons-human-resource-management-others-miscellaneous-service-logo.png"
        ),
        OnBord(
            "title2",
            "https://e7.pngegg.com/pngimages/682/792/png-clipart-organization-management-onboarding-chapelle-notre-dame-de-beauregard-d-orgon-new-hire.png"
        ),
        OnBord(
            "title3",
            "https://p1.hiclipart.com/preview/208/861/268/baby-baby-on-board-car-sticker-infant-text-cartoon-line-png-clipart.jpg"
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBordViewHolder {
        return OnBordViewHolder(
            ItemOnBordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size;
    }

    override fun onBindViewHolder(holder: OnBordViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    inner class OnBordViewHolder(private val binding: ItemOnBordBinding) :
        ViewHolder(binding.root) {
        fun onBind(onBord: OnBord) {
            binding.btnSkip.isVisible = adapterPosition == 2
            binding.btnSkip.setOnClickListener {
                onStartClick()
            }
            binding.tvTile.text = onBord.title
            binding.imgBord.loadImage(onBord.img)
        }
    }
}