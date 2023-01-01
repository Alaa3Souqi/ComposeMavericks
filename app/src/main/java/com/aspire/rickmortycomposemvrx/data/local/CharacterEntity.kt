package com.aspire.rickmortycomposemvrx.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey
    val name: String,
    val image: String
)