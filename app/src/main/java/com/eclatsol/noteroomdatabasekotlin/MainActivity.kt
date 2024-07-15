package com.eclatsol.noteroomdatabasekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eclatsol.noteroomdatabasekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INoteClickListener {

    lateinit var noteAdapter: NoteAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteAdapter = NoteAdapter(this, this)
        binding.recyclerData.layoutManager = LinearLayoutManager(this)
        binding.recyclerData.adapter = noteAdapter

        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]
        noteViewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                noteAdapter.updateList(it)
            }
        })

        binding.btnAddData.setOnClickListener {
            val fetchData = binding.etName.text.toString().trim()
            if (fetchData.isNotEmpty()){
                Toast.makeText(this, "$fetchData", Toast.LENGTH_SHORT).show()
                noteViewModel.insert(Note(fetchData))
                binding.etName.text.clear()
            }
        }
    }

    override fun itemClick(note: Note) {
        Toast.makeText(this, "${note.text}", Toast.LENGTH_SHORT).show()
        noteViewModel.delete(note)
    }
}