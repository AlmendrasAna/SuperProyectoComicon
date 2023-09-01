package com.example.superproyectocomicon.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName="hero_table")
data class HeroEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val origin: String,
    val img: String,
    val power: String,
    val yearCreation: String
)
@Entity (tableName="hero_details_table")
data class HeroDetailsEntity(
    @PrimaryKey  val id: Int,
    val name: String,
    val origin: String,
    val img: String,
    val power: String,
    val yearCreation: String,
    val color :String,
    val translation : Boolean
)
