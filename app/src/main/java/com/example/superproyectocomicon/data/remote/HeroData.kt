package com.example.superproyectocomicon.data.remote

import com.google.gson.annotations.SerializedName

data class HeroData(
    val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("origen") val origin: String,
    @SerializedName("imagenLink") val img: String,
    @SerializedName("poder") val power: String,
    @SerializedName("Año_creacion") val yearCreation : String,
) {

}

data class HeroDetailsData(
    val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("origen") val origin: String,
    @SerializedName("imagenLink") val img: String,
    @SerializedName("poder") val power: String,
    @SerializedName("año_creacion") val yearCreation : String,
    val color : String,
    @SerializedName("traduccion") val translation : Boolean
)