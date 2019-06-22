package com.thernat.starter.vo

import androidx.room.Entity

/**
 * Created by m.rafalski on 07/06/2019.
 */
@Entity(primaryKeys = ["id"])
data class BasicElement(val id: Int, val trait: String)