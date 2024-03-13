package com.thecocktailapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drink_table")
data class Drink(@PrimaryKey(autoGenerate = false) val drinkId: Int)