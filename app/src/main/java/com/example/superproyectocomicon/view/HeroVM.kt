package com.example.superproyectocomicon.view

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.superproyectocomicon.R
import com.example.superproyectocomicon.data.Repository
import com.example.superproyectocomicon.data.local.HeroDB
import com.example.superproyectocomicon.data.local.HeroDetailsEntity
import com.example.superproyectocomicon.data.remote.HeroRFC
import kotlinx.coroutines.launch


class HeroVM(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    init {
        val api = HeroRFC.getRetrofitHero()
        val heroDB = HeroDB.getDatabase(application).getDaoHero()
        repository = Repository(api, heroDB)
    }

    fun heroLiveData() = repository.showListHeroEntity()
    fun getOneDetailsHeroId(id: Int) = repository.showDetailsHeroEntityForId(id)

    fun getAllHero() = viewModelScope.launch {

        repository.loadListHeroEntity()
    }

    fun getOneDetailsHero(id: Int) = viewModelScope.launch {

        repository.loadDetailsHero(id)

    }



}