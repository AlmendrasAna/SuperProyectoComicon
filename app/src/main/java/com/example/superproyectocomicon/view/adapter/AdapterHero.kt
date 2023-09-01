package com.example.superproyectocomicon.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.superproyectocomicon.R
import com.example.superproyectocomicon.data.local.HeroEntity
import com.example.superproyectocomicon.databinding.ItemBinding

class AdapterHero : RecyclerView.Adapter<AdapterHero.ViewHolder>() {


    private var listHero: List<HeroEntity> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listHero.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero = listHero[position]
        holder.bind(hero)
    }

    fun setData(productsEntity: List<HeroEntity>) {

        this.listHero = productsEntity

        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(heroEntity: HeroEntity) {


            binding.nameHeroTxt.text = heroEntity.name.uppercase()
            binding.powerTxt.text = heroEntity.origin
            binding.imageHero.load(heroEntity.img) {
                crossfade(true)
                placeholder(R.drawable.comic_con_chile_2023)

            }


            binding.cardItem.setOnClickListener {

                var bundle = Bundle()
                bundle.putInt("id", heroEntity.id)

                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_heroListFragment_to_detailHeroFragment, bundle)
            }

        }

    }

}