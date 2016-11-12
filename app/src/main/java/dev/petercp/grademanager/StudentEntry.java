package dev.petercp.grademanager;

import android.database.Cursor;

public class StudentEntry {

    static class Table {

        private Table() {} // Not to be instantiated.

        static final String
                TABLE_NAME = "students",
                COL_ID = "_id",
                COL_NAME = "name",
                COL_GRADE = "grade";

        static final String[] PROJECTION = { COL_ID, COL_NAME, COL_GRADE };

        static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY, " +
                COL_NAME + " TEXT NOT NULL, " +
                COL_GRADE + " DOUBLE NOT NULL" +
        ")";

        static final String DELETE_TABLE = "DELETE TABLE IF EXISTS " + TABLE_NAME;
    }

    private Integer id;
    private String name;
    private double grade;

    public StudentEntry(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public StudentEntry(Cursor cursor) {
        id = (int) cursor.getLong(0);
        name = cursor.getString(1);
        grade = cursor.getDouble(2);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }
}
