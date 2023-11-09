package com.example.noteapp.Activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.Adapter.NoteAdapter
import com.example.noteapp.R
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.ViewModel.NoteViewModelFactory
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.databinding.ActivitySearchNoteBinding

class SearchNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchNoteBinding
    private val noteViewModel : NoteViewModel by lazy{
        ViewModelProvider(
            this,
            NoteViewModelFactory(application)
        ).get(NoteViewModel::class.java)
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcvNotesListSearch.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        binding.edtSearchNote.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableRight = binding.edtSearchNote.compoundDrawablesRelative[2]
                if (drawableRight != null && event.rawX >= (binding.edtSearchNote.right - drawableRight.bounds.width())) {
                    // Xử lý sự kiện khi người dùng nhấn vào drawableEnd
                    // Ví dụ: Xóa nội dung của EditText
                    binding.edtSearchNote.text.clear()
                    return@setOnTouchListener true // Đã xử lý sự kiện, không cần xử lý thêm
                }
            }
            return@setOnTouchListener false
        }
        binding.edtSearchNote.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                noteViewModel.searchNote(s.toString()).observe(this@SearchNoteActivity){
                    notes->
                    if(notes.size==0||s.toString().isEmpty()){
                        binding.noItemFoundDisplay.visibility = View.VISIBLE
                        binding.rcvNotesListSearch.visibility = View.GONE
                    }
                    else{
                        binding.noItemFoundDisplay.visibility = View.GONE
                        binding.rcvNotesListSearch.visibility = View.VISIBLE

                        binding.rcvNotesListSearch.adapter = NoteAdapter(notes,{
                            noteViewModel.delete(it)
                        },{note->
                            val intent = Intent(this@SearchNoteActivity,UpdateNoteActivity::class.java)
                                .apply {
                                    putExtra("id",note.id)
                                }
                            startActivity(intent)
                        })

                    }
                }
            }

        })

    }
}