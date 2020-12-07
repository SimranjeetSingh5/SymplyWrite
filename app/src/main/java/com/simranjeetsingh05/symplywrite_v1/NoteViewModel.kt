package com.simranjeetsingh05.symplywrite_v1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class NoteViewModel(application: Application): AndroidViewModel(application) {

    val allnotes: LiveData<List<Note>>
    private val repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allnotes = repository.allNotes
    }

    //Coroutine scope....A suspend can only be called using suspend or other coroutineSCope
    fun deleteNode(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(note)
    }
}