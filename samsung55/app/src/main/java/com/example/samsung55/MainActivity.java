package com.example.samsung55;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Controller controller = new Controller();

    EditText name;
    EditText lastName;
    EditText className;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        lastName = (EditText) findViewById(R.id.lastName);
        className = (EditText) findViewById(R.id.className);
        result = (TextView) findViewById(R.id.result);
    }

    public void saveUser(View view){
        Model user = new Model();

        Log.d("TEST", name.getText().toString());

        user.setName(name.getText().toString());
        user.setLastname(lastName.getText().toString());
        user.setClassname(className.getText().toString());

        controller.setUser(user);

        Toast.makeText(getApplicationContext(),
                "Пользователь сохранен!",
                Toast.LENGTH_SHORT);
    }

//    @SuppressLint("SetTextI18n")
    public void getUser(View view){
        LiveData<List<Model>> liveDataUsers = controller.getUser();
//        Log.d("INFO_", users.get(0).getName());

        liveDataUsers.observe(this, new Observer<List<Model>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(List<Model> users) {
                Log.d("INFO_", "USERS_UPDATE");
                System.out.println(liveDataUsers.getValue());
                List<Model> list = liveDataUsers.getValue();
                if (list.size() !=0) {
                    result.setText("");
                    for (Model model: list){
                        result.setText(result.getText() +
                                model.getName() + " " +
                                model.getLastname() + " " +
                                model.getClassname() + "\n");
                    }


                    Toast.makeText(getApplicationContext(),
                            "Пользователь выведен!",
                            Toast.LENGTH_SHORT);
                }
            }
        });


    }
}