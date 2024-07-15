package com.eclatsol.newadddata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclatsol.noteroomdatabasekotlin.R
import com.eclatsol.noteroomdatabasekotlin.databinding.ActivityAddDataBinding

class AddDataActivity : AppCompatActivity(), IStudentClickListener {

    lateinit var binding: ActivityAddDataBinding

    lateinit var studentAdapter: StudentAdapter

    lateinit var studentViewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        studentAdapter = StudentAdapter(this, this)
        binding.recyclerStudentData.layoutManager = LinearLayoutManager(this)
        binding.recyclerStudentData.adapter = studentAdapter

        studentViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[StudentViewModel::class.java]

        studentViewModel.allStudent.observe(this, Observer { list ->
            list?.let {
                studentAdapter.studentUpdateList(it)
            }
        })

        binding.btnStudentAddData.setOnClickListener {
            val studentName = binding.etStudentName.text.toString().trim()
            if (studentName.isNotEmpty()) {
                studentViewModel.insert(Student(studentName))
                Toast.makeText(this, "$studentName", Toast.LENGTH_SHORT).show()
                binding.etStudentName.text.clear()
            }
        }
    }

    override fun studentClick(student: Student) {
        studentViewModel.delete(student)
    }
}