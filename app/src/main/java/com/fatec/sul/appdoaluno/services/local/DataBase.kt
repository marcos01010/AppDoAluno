package com.fatec.sul.appdoaluno.services.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fatec.sul.appdoaluno.model.Aluno
import com.fatec.sul.appdoaluno.model.Horario
import com.fatec.sul.appdoaluno.model.Materia

@Database(entities = [Aluno::class,Materia::class,Horario::class], version = 10, exportSchema = false)
abstract class DataBase: RoomDatabase() {
    abstract fun alunoDao(): AlunoDao
    abstract fun materiaDao(): MateriaDao
    abstract fun horarioDao(): HorarioDao

    companion object{
        private lateinit var INSTANCE: DataBase

        fun getDatabase(context: Context): DataBase{
            if(!Companion::INSTANCE.isInitialized){
                synchronized(DataBase::class){
                    INSTANCE = Room.databaseBuilder(context, DataBase::class.java, "AppDoAluno")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}