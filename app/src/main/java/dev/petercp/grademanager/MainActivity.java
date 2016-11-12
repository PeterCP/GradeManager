package dev.petercp.grademanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_student) {
            Intent intent = new Intent(this, FormActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSearchButtonClicked(View view) {
        EditText searchInput = (EditText) findViewById(R.id.input_search);

        int id;
        try {
            id = Integer.parseInt(searchInput.getText().toString());
        } catch (NumberFormatException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        DBHelper helper = new DBHelper(getApplicationContext());
        StudentEntry student = helper.getStudentById(id);

        if (student == null) {
            Toast.makeText(this, "No se encontró al estudiante con matrícula " + String.valueOf(id),
                    Toast.LENGTH_SHORT).show();
            return;
        }

//        View displayContainer = findViewById(R.id.content_main);
//        TextView idDisplay = (TextView) displayContainer.findViewById(R.id.display_id),
//                nameDisplay = (TextView) displayContainer.findViewById(R.id.display_name),
//                gradeDisplay = (TextView) displayContainer.findViewById(R.id.display_grade);

        TextView idDisplay = (TextView) findViewById(R.id.display_id),
                nameDisplay = (TextView) findViewById(R.id.display_name),
                gradeDisplay = (TextView) findViewById(R.id.display_grade);

        idDisplay.setText(String.valueOf(student.getId()));
        nameDisplay.setText(student.getName());
        gradeDisplay.setText(String.valueOf(student.getGrade()));
    }
}
