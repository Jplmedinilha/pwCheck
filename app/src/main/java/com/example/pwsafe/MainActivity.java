package com.example.pwsafe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    EditText senhaDigitada;
    Button btnCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senhaDigitada = (EditText) findViewById(R.id.pwdDigitada);
        btnCheck = (Button) findViewById(R.id.checkBtn);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                checarSenha();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ResourceAsColor")
    public void checarSenha(){
        String pwd = senhaDigitada.getText().toString();
        TextView resultTextView = findViewById(R.id.resultTextView);

        if (isStrongPassword(pwd)) {
            resultTextView.setText("A senha é forte.");
            resultTextView.setTextColor(getResources().getColor(R.color.strongColor));
        } else {
            StringBuilder suggestions = new StringBuilder("A senha é fraca. Sugestões para melhorar:\n");

            if (pwd.length() < 8) {
                suggestions.append("- Use pelo menos 8 caracteres.\n");
            }

            if(!pwd.chars().anyMatch(Character::isLowerCase)){
                suggestions.append("- Inclua pelo menos uma letra minúscula.\n");
            }

            if(!pwd.chars().anyMatch(Character::isUpperCase)){
                suggestions.append("- Inclua pelo menos uma letra maiúscula.\n");
            }

            if(!pwd.chars().anyMatch(Character::isDigit)){
                suggestions.append("- Inclua pelo menos um número.\n");
            }

            if(!pwd.chars().anyMatch(ch -> "!@#$%^&*()_+{}[]|:;<>,.?/-".indexOf(ch) >= 0)){
                suggestions.append("- Inclua pelo menos um caractere especial (por exemplo, @, $, !, %, *, ?, &).\\n");
            }

            resultTextView.setText(suggestions.toString());
            resultTextView.setTextColor(getResources().getColor(R.color.weakColor));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean isStrongPassword(String password) {
        // Verifica se a senha tem pelo menos 8 caracteres
        if (password.length() < 8) {
            return false;
        } else if(!password.chars().anyMatch(Character::isLowerCase)){
            return false;
        } else if(!password.chars().anyMatch(Character::isUpperCase)){
            return false;
        } else if(!password.chars().anyMatch(Character::isDigit)){
            return false;
        } else if(!password.chars().anyMatch(ch -> "!@#$%^&*()_+{}[]|:;<>,.?/-".indexOf(ch) >= 0)){
            return false;
        }

        return true;
    }


}