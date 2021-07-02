package com.example.noteapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * this is first class in room because
 * we define database table (@Entity) and primaryKey (id variable).
 */
@Entity(tableName = "note_table") // is a room annotation for object table
public class Note {

    /**
     * room db automatically create rows for these variables.
     * so every row have unique id room will create it by using id variable.
     */
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    // @ColumnInfo(name = "priority_column") >>this used for rename column by default name of column in db is the same name of variable
    private int priority;

    //constructor
    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }
    // setter method to pass id for db
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
