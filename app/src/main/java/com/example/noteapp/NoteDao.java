package com.example.noteapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @DAO is standard for Data Access Object so
 *  this second class in room in this class we define
 *  the operation that we made in our db .
 * @DAO it should be in interface or abstract class
 *  because we define methods without body .
 */

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    // make specific query to add delete all option
    // so when type DELETE FROM with space that mean delete all items
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    // to return all items .
    // DESC means order items descending (from large num to small) .
    // when use LiveData that means if user make any change it will refreshed auto .
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
}
