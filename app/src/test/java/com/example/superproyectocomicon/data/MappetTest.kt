package com.example.superproyectocomicon.data

import com.example.superproyectocomicon.data.remote.HeroData
import org.junit.Assert.*

import org.junit.Test

class MappetTest {

    @Test
    fun toHeroEntity() {
        //given
        val image = "http://federico.com/chapulin.jpg"
        val id = 1
        val name = "chapulin colorado"
        val origin = "mexico"
        val power = "volar"
        val yearCreation = "1960"
        val heroData = HeroData(id, name, origin, image, power, yearCreation)

        //when
        val result = heroData.toHeroEntity()

        //then
        assertEquals(id,result.id)
        assertEquals(name,result.name)
        assertEquals(origin,result.origin)
        assertEquals(power,result.power)
        assertEquals(yearCreation,result.yearCreation)
        assertEquals(image,result.img)

    }
}