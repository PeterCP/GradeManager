package dev.petercp.grademanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "GradeManager.db";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StudentEntry.Table.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(StudentEntry.Table.DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    public StudentEntry getStudentById(int id) {
        String[] queryArgs = { String.valueOf(id) };
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                StudentEntry.Table.TABLE_NAME,
                StudentEntry.Table.PROJECTION,
                StudentEntry.Table.COL_ID + " = ?",
                queryArgs, null, null, null
        );

        cursor.moveToFirst();
        StudentEntry student = null;

        if (cursor.getCount() == 1)
            student = new StudentEntry(cursor);

        cursor.close();

        return student;
    }

    public void storeStudent(StudentEntry student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentEntry.Table.COL_ID, student.getId());
        values.put(StudentEntry.Table.COL_NAME, student.getName());
        values.put(StudentEntry.Table.COL_GRADE, student.getGrade());
        long id = db.insert(StudentEntry.Table.TABLE_NAME, null, values);
    }
}
