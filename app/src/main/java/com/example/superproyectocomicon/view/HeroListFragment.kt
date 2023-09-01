package com.example.superproyectocomicon.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.superproyectocomicon.R
import com.example.superproyectocomicon.databinding.FragmentHeroListBinding
import com.example.superproyectocomicon.view.adapter.AdapterHero

class HeroListFragment : Fragment() {
    lateinit var  binding: FragmentHeroListBinding
    private val heroVM : HeroVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroListBinding.inflate(inflater,container,false)
        heroVM.getAllHero()
        initAdapter()

        return binding.root
    }

    private fun initAdapter() {
      val adapter = AdapterHero()
        binding.recyclerView.adapter = adapter
        heroVM.heroLiveData().observe(viewLifecycleOwner){
            adapter.setData(it)
        }

    }

}
