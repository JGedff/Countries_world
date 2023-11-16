package com.example.ac02paisesdelmundo.classes

import com.google.gson.annotations.SerializedName

data class Pais (
    @SerializedName("name_en")
    val nameEn: String,
    @SerializedName("name_es")
    val nameEs: String,
    @SerializedName("continent_en")
    val continentEn: String,
    @SerializedName("continent_es")
    val continentEs: String,
    @SerializedName("capital_en")
    val capitalEn: String,
    @SerializedName("capital_es")
    val capitalEs: String,
    @SerializedName("dial_code")
    val dialCode: String,
    @SerializedName("code_2")
    val code2: String,
    @SerializedName("code_3")
    val code3: String,
    val tld: String,
    val km2: Double,
    val emoji: String,
    var favorite: Boolean
)

data class Paises (
    val countries: ArrayList<Pais>
)