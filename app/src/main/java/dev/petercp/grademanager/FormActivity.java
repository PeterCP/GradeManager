package dev.petercp.grademanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSubmitButtonClicked(View view) {
        EditText idInput = (EditText) findViewById(R.id.input_id),
                nameInput = (EditText) findViewById(R.id.input_name),
                gradeInput = (EditText) findViewById(R.id.input_grade);

        int id;
        String name;
        double grade;

        try {
            id = Integer.parseInt(idInput.getText().toString());
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Invalid value for field id", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!nameInput.getText().toString().isEmpty()) {
            name = nameInput.getText().toString();
        } else {
            Toast.makeText(this, "Invalid value for field name", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            grade = Double.parseDouble(gradeInput.getText().toString());
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Invalid value for field grade", Toast.LENGTH_SHORT).show();
            return;
        }

        DBHelper helper = new DBHelper(getApplicationContext());
        helper.storeStudent(new StudentEntry(id, name, grade));

        finish();
    }

}
