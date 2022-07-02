package br.com.fiap.affily.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crianca")
data class Child (
    @PrimaryKey val id: Int,
    val nome: String
)
