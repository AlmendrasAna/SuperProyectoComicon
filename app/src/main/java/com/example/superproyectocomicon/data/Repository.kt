package com.example.superproyectocomicon.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.superproyectocomicon.data.local.HeroDao
import com.example.superproyectocomicon.data.local.HeroDetailsEntity
import com.example.superproyectocomicon.data.local.HeroEntity
import com.example.superproyectocomicon.data.remote.HeroApi
import com.example.superproyectocomicon.data.remote.HeroData
import com.example.superproyectocomicon.data.remote.HeroDetailsData

class Repository(private val heroApi: HeroApi, private val heroDao: HeroDao) {

    fun showListHeroEntity(): LiveData<List<HeroEntity>> = heroDao.showListHeroEntity()

    suspend fun loadListHeroEntity() {
        try {

            val response = heroApi.getProductsData()
            if (response.isSuccessful) {
                val bodyResponse = response.body()
                bodyResponse?.let { phoneData ->
                    val listHeroEntity = phoneData.map {
                        it.toHeroEntity()
                    }
                    heroDao.insertsListHeroEntity(listHeroEntity)
                }
            } else {
                Log.e("response", "ERROR")
            }
        } catch (exception: Exception) {
            Log.e("catch", "ERROR")
        }
    }

    fun showDetailsHeroEntityForId(id: Int): LiveData<HeroDetailsEntity> =
        heroDao.showDetailsHeroEntityForId(id)

    suspend fun loadDetailsHero(id: Int) {
        try {

            val response = heroApi.getDetailsData(id)
            if (response.isSuccessful) {
                val bodyResponse = response.body()

                bodyResponse?.let { heroDetailsData ->
                    val heroDetailsEntity = heroDetailsData.toHeroDetailsEntity()
                    heroDao.insertsOneHeroDetailsEntity(heroDetailsEntity)
                }
            }
        } catch (exception: Exception) {
            Log.e("catch", "Error")
        }
    }


}

fun HeroData.toHeroEntity(): HeroEntity = HeroEntity(
    this.id,
    this.name,
    this.origin,
    this.img,
    this.power,
    this.yearCreation
)

fun HeroDetailsData.toHeroDetailsEntity(): HeroDetailsEntity = HeroDetailsEntity(
    this.id,
    this.name,
    this.origin,
    this.img,
    this.power,
    this.yearCreation,
    this.color,
    this.translation
)
