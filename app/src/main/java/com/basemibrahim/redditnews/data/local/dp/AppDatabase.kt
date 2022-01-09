
package com.basemibrahim.redditnews.data.local.dp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.basemibrahim.redditnews.data.local.dp.dao.ChildrenDao
import com.basemibrahim.redditnews.data.model.api.NewsResponse
import com.basemibrahim.redditnews.utils.Converters

@Database(entities = arrayOf(NewsResponse::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun childrenDao(): ChildrenDao
}
