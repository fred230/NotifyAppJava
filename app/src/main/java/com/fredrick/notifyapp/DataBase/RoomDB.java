package com.fredrick.notifyapp.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fredrick.notifyapp.Models.Notes;

import java.net.PortUnreachableException;

@Database(entities = Notes.class,version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
  private static RoomDB Database;
  private static String DATABASE_NAME = "NotifyApp";

  public synchronized static RoomDB getInstance(Context context){
      if (Database==null){
          Database = Room.databaseBuilder(context.getApplicationContext(),RoomDB.class,DATABASE_NAME)
                  .allowMainThreadQueries()
                  .fallbackToDestructiveMigration()
                  .build();

      }

      return Database;

  }

  public abstract mainDAO mainDAO();




}
