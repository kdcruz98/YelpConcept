package model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import model.Post
import model.PostDao

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}