package com.example.luisfernandomedinallorenti.daggerlogin.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luisfernandomedinallorenti.daggerlogin.R;
import com.example.luisfernandomedinallorenti.daggerlogin.root.ApplicationDagger;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements LoginActivityMVP.View{
    @Inject
    LoginActivityMVP.Presenter presenter;

    EditText nombre, apellido;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inyeccion Dagger
        //((ApplicationDagger) getApplication()).getComponent().inject(this);

        nombre=findViewById(R.id.editText_Nombre);
        apellido=findViewById(R.id.editText_Apellido);
        loginButton=findViewById(R.id.button_Login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginButttonCLicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getCurrentUser();
    }

    @Override
    public String getFirstName() {
        return this.nombre.getText().toString();
    }

    @Override
    public String getLastName() {
        return this.apellido.getText().toString();
    }

    @Override
    public void showUserNotAvailable() {
        Toast.makeText(this,"Error, Usuario no disponible",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this,"Error, campos de Usuario vacios",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSaved() {
        Toast.makeText(this,"Usuario Guardado",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFirstName(String firstName) {
        this.nombre.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        this.apellido.setText(lastName);
    }
}
