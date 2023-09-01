package com.example.superproyectocomicon.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[HeroEntity::class,HeroDetailsEntity::class], version =1)
abstract class HeroDB : RoomDatabase(){

    abstract fun getDaoHero(): HeroDao

    companion object {
        @Volatile
        private var INSTANCE: HeroDB? = null

        fun getDatabase(context: Context):HeroDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HeroDB::class.java,
                    "hero_db"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}