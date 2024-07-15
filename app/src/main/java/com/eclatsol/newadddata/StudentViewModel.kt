package com.eclatsol.newadddata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    var repository: StudentRepository
    var allStudent: LiveData<List<Student>>

    init {
        var studentDao =
            StudentDatabase.getInstance(application.applicationContext).getStudentData()
        repository = StudentRepository(studentDao)
        allStudent = repository.allStudent
    }

    fun insert(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(student)
    }

    fun delete(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(student)
    }

}