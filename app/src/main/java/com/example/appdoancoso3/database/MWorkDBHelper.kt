package com.example.appdoancoso3.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.appdoancoso3.R
import com.example.appdoancoso3.list_work.HomeListWorkFragment
import com.example.appdoancoso3.model.DetailWorkModel
import com.example.appdoancoso3.model.WorkModel
import java.util.*
import kotlin.collections.ArrayList


class MWorkDBHelper(context: Context): SQLiteOpenHelper(context, "ManageWorkDB1", null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_NAME = "ManageWorkDB"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "users"
        private const val TABLE_NAME1 = "Works"
        private const val TABLE_NAME2 = "DetailWorks"
        private const val TABLE_NAME3 = "NoteWorks"
        private const val COLUMN_ID = "ID"
        private const val COLUMN_TITLE = "NameWork"
        private const val COLUMN_CONTENT = "Content"
        private const val COLUMN_STATUS = "status"
        private const val COLUMN_IDWORKS = "IDWorks"
        private const val COLUMN_TIME_HOURS = "Time_hours"
        private const val COLUMN_TIME_MINUTES = "Time_minutes"
        private const val COLUMN_DATE_CREATED = "date_created"

    }


    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE "+TABLE_NAME+ "(\n" +
                "    ID       INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                     NOT NULL,\n" +
                "    FullName TEXT,\n" +
                "    Username TEXT    NOT NULL,\n" +
                "    Password TEXT    NOT NULL\n" +
                ");\n")

        val CREATE_TABLE1 = ("CREATE TABLE "+TABLE_NAME1+" (\n" +
                "    ID          INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                        NOT NULL,\n" +
                "    Title       TEXT    NOT NULL,\n" +
                "    Date_created TEXT    NOT NULL,\n" +
                "    IDUser      INTEGER REFERENCES users (ID) ON DELETE CASCADE\n" +
                ");")
        val CREATE_TABLE2 = ("CREATE TABLE "+TABLE_NAME2+" (\n" +
                "    ID           INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                         NOT NULL,\n" +
                "    NameWork     TEXT    NOT NULL,\n" +
                "    date_created         TEXT    NOT NULL,\n" +
                "    Time_hours         TEXT    NOT NULL,\n" +
                "    Time_minutes         TEXT    NOT NULL,\n" +
                "    Content      TEXT,\n" +
                "    status       INTEGER NOT NULL,\n" +
                "    IDWorks      INTEGER REFERENCES Works (ID) ON DELETE CASCADE\n" +
                "                         NOT NULL\n" +
                ")\n")
        val CREATE_TABLE3 = ("CREATE TABLE "+TABLE_NAME3+" (\n" +
                "    ID           INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                         NOT NULL,\n" +
                "    NameNote     TEXT    NOT NULL,\n" +
                "    content      TEXT,\n" +
                "    IDWorkDetail INTEGER REFERENCES DetailWorks (ID) ON DELETE CASCADE\n" +
                ");\n")
        if (p0 != null) {
            p0.execSQL(CREATE_TABLE)
            p0.execSQL(CREATE_TABLE1)
            p0.execSQL(CREATE_TABLE2)
            p0.execSQL(CREATE_TABLE3)
        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p0 != null) {
            p0.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
            p0.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1)
            p0.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2)
            p0.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3)
        }
        onCreate(p0)
    }
    fun insertDataWorkDetail(
        title: String, content: String,
        date: String?,
        hours: String,
        minutes: String, status: Int, idworks: Int): Boolean {
        val values = ContentValues()
        values.put(COLUMN_TITLE, title)
        values.put(COLUMN_CONTENT, content)
        values.put(COLUMN_DATE_CREATED, date)
        values.put(COLUMN_TIME_HOURS, hours)
        values.put(COLUMN_TIME_MINUTES, minutes)
        values.put(COLUMN_STATUS, status)
        values.put(COLUMN_IDWORKS, idworks)
        val db = this.writableDatabase
        val result = db.insert(TABLE_NAME2, null, values)
//        db.close()
        return result > -1
    }

    fun insertDataWorks(title: String,date: String, iduser: Int): Boolean {
        val values = ContentValues()
        values.put("Title", title)
        values.put("Date_created", date)
        values.put("IDUser", iduser)
        val db = this.writableDatabase
        val result = db.insert(TABLE_NAME1, null, values)
//        db.close()
        return result > -1
    }
    fun createAccount(FullName: String,Username: String, Password: String): Boolean {
        val values = ContentValues()
        values.put("FullName", FullName)
        values.put("Username", Username)
        values.put("Password", Password)
        val db = this.writableDatabase
        val result = db.insert(TABLE_NAME, null, values)
//        db.close()
        return result > -1
    }


//    GetDate
    @SuppressLint("Range")
    fun getDataWorkDetail(): ArrayList<DetailWorkModel> {
        val data = ArrayList<DetailWorkModel>()
        val db = this.readableDatabase
        val dateCreated = getDateNow()
        val idworks = getIDWorksNow()
        if(idworks != -1){
            Log.wtf("aaa", idworks.toString())
            Log.wtf("aaa", dateCreated)

            val cursor: Cursor = db.rawQuery("SELECT * FROM DetailWorks WHERE  IDWorks = $idworks", null)

            if (cursor.moveToFirst()) {
                Log.wtf("aaa", "1")
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                    val title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
                    val content = cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT))
                    val date_created = cursor.getString(cursor.getColumnIndex(COLUMN_DATE_CREATED))
                    val time_hours = cursor.getString(cursor.getColumnIndex(COLUMN_TIME_HOURS))
                    val time_minutes = cursor.getString(cursor.getColumnIndex(COLUMN_TIME_MINUTES))
                    val status = cursor.getInt(cursor.getColumnIndex(COLUMN_STATUS))
                    val idworks = cursor.getInt(cursor.getColumnIndex(COLUMN_IDWORKS))


                    data.add(DetailWorkModel(id, title,date_created ,time_hours,time_minutes,content,status,idworks))
                } while (cursor.moveToNext())
            }
            cursor.close()
//            db.close()
        }


        return data
    }

    @SuppressLint("Range")
    fun getDataWorkHistory(): ArrayList<WorkModel> {
        val data = ArrayList<WorkModel>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM Works", null)

        if (cursor.moveToFirst()) {

            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val title = cursor.getString(cursor.getColumnIndex("Title"))
                val date_created = cursor.getString(cursor.getColumnIndex("Date_created"))
                data.add(WorkModel(id,title,date_created, 0))
            } while (cursor.moveToNext())
        }
        cursor.close()
//        db.close()
        return data
    }

    @SuppressLint("Range")
    fun getIDWorksNow(): Int {
        val db = this.readableDatabase
        val date_now = getDateNow()
        val cursor: Cursor = db.rawQuery("SELECT ID FROM Works WHERE Date_created = '$date_now'", null)
        var id: Int = -1
        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
        }
        cursor.close()
//        db.close()
        return id
    }
    @SuppressLint("Range")
    fun getDataWorkNow(): ArrayList<WorkModel> {
        val data = ArrayList<WorkModel>()
        val db = this.readableDatabase
        val dateCreated = getDateNow()
        val cursor: Cursor = db.rawQuery("SELECT * FROM Works WHERE Date_created = '$dateCreated' ", null)

        if (cursor.moveToFirst()) {
            Log.wtf("aaa", "hay")
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val title = cursor.getString(cursor.getColumnIndex("Title"))
                val date_created = cursor.getString(cursor.getColumnIndex("Date_created"))
                data.add(WorkModel(id,title,date_created, 0))
            } while (cursor.moveToNext())
        }
        cursor.close()
//        db.close()
        return data
    }
// delete
    fun deleteData(id:Int, table: String): Int{
    val db = this.writableDatabase

// Điều kiện để xóa dòng dữ liệu
    val whereClause = "ID = ?"
    val whereArgs = arrayOf(id.toString()) // giá trị của id

// Thực hiện xóa dòng dữ liệu
    val deletedRows = db.delete(table, whereClause,whereArgs )

        return deletedRows
    }


    fun getDateNow(): String {
        val calendar: Calendar = Calendar.getInstance()

        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH] + 1 // Vì tháng bắt đầu từ 0 nên phải cộng thêm 1

        val day = calendar[Calendar.DAY_OF_MONTH]
        val currentDate = "$day/$month/$year"
        return currentDate
    }
}