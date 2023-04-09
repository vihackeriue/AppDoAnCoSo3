package com.example.appdoancoso3.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class MWorkCopyDatabase(private val context: Context) {

    companion object{
        private val DB_NAME = "ManageWorkDB.db"
    }

    fun openDatabase(): SQLiteDatabase {
        val dbFile = context.getDatabasePath(DB_NAME)
        val file = File(dbFile.toString())
        if(file.exists()){
            Log.e("TB","file tồn tại")
        }else {
            moveDatabase(dbFile)
        }
        return SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READWRITE)
    }

    private fun moveDatabase(dbFile: File?) {
        val openDB = context.assets.open(DB_NAME)
        val outputStream = FileOutputStream(dbFile)
        val buffer = ByteArray(1024)
        while(openDB.read(buffer)>0){
            outputStream.write(buffer)
            Log.wtf("DB", "đang ghi dữ liệu")
        }
        outputStream.flush()
        outputStream.close()
        openDB.close()
        Log.wtf("DB", "sao chép dữ liệu thành công")
    }

}