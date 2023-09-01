package com.example.superproyectocomicon.data

import com.example.superproyectocomicon.data.local.HeroDetailsEntity
import com.example.superproyectocomicon.data.local.HeroEntity
import com.example.superproyectocomicon.data.remote.HeroData
import com.example.superproyectocomicon.data.remote.HeroDetailsData

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
