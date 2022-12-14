package com.ldnprod.timer.Interfaces

import androidx.lifecycle.LiveData
import com.ldnprod.timer.Entities.Exercise
import com.ldnprod.timer.Entities.Training

interface IExerciseRepository {
    suspend fun insert(exercise: Exercise) : Long

    suspend fun delete(exercise: Exercise)

    suspend fun update(exercise: Exercise)

    fun getAll(): List<Exercise>

    fun getAllInTraining(training: Training): List<Exercise>

    fun getAllInTrainingByOrder(trainingId: Int): List<Exercise>

    fun getById(id: Int): Exercise
}