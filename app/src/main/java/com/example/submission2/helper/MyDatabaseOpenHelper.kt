package com.example.submission2.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.support.annotation.IntegerRes
import com.example.submission2.Favorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx : Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {

    companion object{
        private var instance : MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context):MyDatabaseOpenHelper{
            if (instance == null){
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.MATCH_ID  to TEXT + UNIQUE,
            Favorite.TEAM_HOME_NAME to TEXT,
            Favorite.TEAM_HOME_SCORE to TEXT,
            Favorite.TEAM_AWAY_NAME to TEXT,
            Favorite.TEAM_AWAY_SCORE to TEXT,
            Favorite.DATE_MATCH to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }

}

val Context.database : MyDatabaseOpenHelper
get() = MyDatabaseOpenHelper.getInstance(applicationContext)