package com.thernat.starter.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thernat.starter.vo.BasicElement

/**
 * Created by m.rafalski on 07/06/2019.
 */
@Database(
    entities = [
        BasicElement::class],
    version = 1,
    exportSchema = false
)
abstract class MVVMDatabase  : RoomDatabase() {

    abstract fun basicDao(): BasicDao


}