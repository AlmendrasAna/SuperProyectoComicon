package com.example.superproyectocomicon.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertsListHeroEntity(HeroListEntitys: List<HeroEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertsOneHeroDetailsEntity(heroDetailsEntitys: HeroDetailsEntity)

    @Query("select * from hero_table order by id ASC ")
    fun showListHeroEntity(): LiveData<List<HeroEntity>>

    @Query("select * from hero_details_table where Id = :id ")
    fun ShowDetailsHeroEntityForId(id: Int): LiveData<HeroDetailsEntity>
}