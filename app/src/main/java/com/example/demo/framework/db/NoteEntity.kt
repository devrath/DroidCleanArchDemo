package com.example.demo.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.Note

@Entity(tableName = "note")
data class NoteEntity (
    var title : String,
    var content : String,

    @ColumnInfo(name = "creation_time")
    var creationTime : Long,

    @ColumnInfo(name = "update_time")
    var updateTime : Long,

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
){
    companion object{
        //Convert from note to note entity
        fun fromNote(note : Note) = NoteEntity(note.title,note.content,note.creationTime,note.updateTime)
    }

    //Convert from note entity to note
    fun toNote() = Note(title,content, creationTime, updateTime, id)
}