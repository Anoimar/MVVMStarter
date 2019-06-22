package com.thernat.starter.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thernat.starter.vo.BasicElement

@Dao
interface BasicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(basicElement: BasicElement)

    @Query("SELECT * FROM basicelement WHERE id = (SELECT MAX(id) FROM basicelement)")
    fun getElementWithBiggestId(): BasicElement?
}