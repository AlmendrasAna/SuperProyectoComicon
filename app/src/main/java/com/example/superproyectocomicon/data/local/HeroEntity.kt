package com.example.superproyectocomicon.data.local

import androidx.room.Entity


@Entity (tableName="hero_table")
data class HeroEntity(
    val id: Int,
    val name: String,
    val origin: String,
    val img: String,
    val power: String,
    val yearCreation: String,
)
@Entity (tableName="hero_details_table")
data class HeroDetailsEntity(
    val id: Int,
    val name: String,
    val origin: String,
    val img: String,
    val power: String,
    val yearCreation: String,
    val color :String,
    val translation : Boolean
)
