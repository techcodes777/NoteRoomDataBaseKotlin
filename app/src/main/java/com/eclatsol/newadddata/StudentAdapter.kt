package com.eclatsol.newadddata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eclatsol.noteroomdatabasekotlin.R

class StudentAdapter(val context: Context, private val iStudentClickListener: IStudentClickListener) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    var studentList = ArrayList<Student>()

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)
        var studentData: TextView = itemView.findViewById(R.id.text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val viewHolder = StudentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_add_data, parent, false)
        )
        viewHolder.ivDelete.setOnClickListener {
            iStudentClickListener.studentClick(studentList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.studentData.text = studentList[position].studentName
    }

    fun studentUpdateList(newStudent: List<Student>) {
        studentList.clear()
        studentList.addAll(newStudent)
        notifyDataSetChanged()
    }
}

interface IStudentClickListener {
    fun studentClick(student: Student)
}