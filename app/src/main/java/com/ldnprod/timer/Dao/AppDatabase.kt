package com.ldnprod.timer.Dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ldnprod.timer.Entities.Exercise
import com.ldnprod.timer.Entities.Training

@Database(entities = [Exercise::class, Training::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract val trainingDao: TrainingDao
    abstract val exerciseDao: ExerciseDao
}