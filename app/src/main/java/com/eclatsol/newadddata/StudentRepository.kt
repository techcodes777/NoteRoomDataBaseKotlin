package com.eclatsol.newadddata

import androidx.lifecycle.LiveData

class StudentRepository(private val studentDao: StudentDao) {

    val allStudent: LiveData<List<Student>> = studentDao.getAllStudentData()

    suspend fun insert(student: Student) {
        studentDao.insert(student)
    }

    suspend fun delete(student: Student) {
        studentDao.delete(student)
    }
}