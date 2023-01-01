package com.aspire.rickmortycomposemvrx.data.model

import com.aspire.rickmortycomposemvrx.data.local.CharacterEntity
import com.aspire.rickmortycomposemvrx.domain.Character
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    @Json(name = "results")
    val CharactersList: List<CharacterEntity>
)

fun CharacterResponse.toCharacters() =
    CharactersList.map { Character(it.name, it.image, false) }
