package com.example.noteapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * third class in room db
 */
//version for if we make any changes on data it take a version num 1,2,3.....
@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    //make this method to access our Dao
    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        //we can't make new form instance because it abstract so use room builder
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        /**
         * onCreate method it used for first time to initiate db for first one.
         * onOpen method it used for every time when you used db.
         */
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1" , "Description 1" , 1));
            noteDao.insert(new Note("Title 2" , "Description 2" , 2));
            noteDao.insert(new Note("Title 3" , "Description 3" , 3));
            return null;
        }
    }
}
