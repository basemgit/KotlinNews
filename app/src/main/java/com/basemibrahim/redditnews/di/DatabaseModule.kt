package com.basemibrahim.redditnews.di

import android.content.Context
import androidx.room.Room
import com.basemibrahim.redditnews.data.local.dp.AppDatabase
import com.basemibrahim.redditnews.data.local.dp.dao.ChildrenDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideLogDao(database: AppDatabase): ChildrenDao {
        return database.childrenDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "kotlinNews.db"
        ).build()
    }
}