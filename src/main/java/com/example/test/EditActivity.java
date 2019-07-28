package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    EditText name;
    EditText group;
    EditText type;

    Button change;

    private static String extra_id = "id";
    private static String extra_type = "type";
    private static String extra_group = "group";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        name = (EditText)findViewById(R.id.add_name);
        group = (EditText)findViewById(R.id.edit_text_title);
        type = (EditText)findViewById(R.id.Type);

        change = (Button)findViewById(R.id.change);

        Intent intent = getIntent();

        if(intent.hasExtra(extra_id)){
            name.setText(intent.getStringExtra("name"));
            group.setText(intent.getStringExtra("group"));
            type.setText(intent.getStringExtra("type"));
        }

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveChanges();
            }
        });
    }
    public void saveChanges(){
        String name_text = name.getText().toString();
        String group_text = group.getText().toString();
        String type_text = type.getText().toString();

        Intent intent = new Intent();

        intent.putExtra("name" , name_text);
        intent.putExtra("group" , group_text);
        intent.putExtra("type" , type_text);

        setResult(1,intent);
        finish();
    }

    public static String getExtra_id() {
        return extra_id;
    }

    public void setExtra_id(String extra_id) {
        this.extra_id = extra_id;
    }

    public static String getExtra_type() {
        return extra_type;
    }

    public void setExtra_type(String extra_type) {
        this.extra_type = extra_type;
    }

    public static String getExtra_group() {
        return extra_group;
    }

    public void setExtra_group(String extra_group) {
        this.extra_group = extra_group;
    }
}
