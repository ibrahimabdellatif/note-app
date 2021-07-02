package com.example.noteapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * it is a java class but is just for
 * best practices.
 */
public class NoteRepository {
    private NoteDao noteDao;

    private LiveData<List<Note>> allNotes;

    // context is subclass of Application class.
    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        //noteDao() is abstract method
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    /**
     * if we make database operation in main thread
     * app will be crashed so we use AsyncTask for
     * every operation.
     */

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    // it takes three parameters
    public static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            //notes with index 0 because it's one notes and start index is zero
            noteDao.insert(notes[0]);
            return null;
        }
    }

    public static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            //notes with index 0 because it's one notes and start index is zero
            noteDao.update(notes[0]);
            return null;
        }
    }

    public static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            //notes with index 0 because it's one notes and start index is zero
            noteDao.delete(notes[0]);
            return null;
        }
    }

    //we make first parameter void because this class doesn't return any thing
    public static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //notes with index 0 because it's one notes and start index is zero
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
