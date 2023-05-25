package com.example.myownpersonalassistance.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myownpersonalassistance.Utils.Constants.DATABASE_NAME
import com.example.myownpersonalassistance.db.ScheduleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        ScheduleDatabase::class.java,
        DATABASE_NAME
    ).build()


    fun provideDAO(database: ScheduleDatabase) = database.getDAO()
}