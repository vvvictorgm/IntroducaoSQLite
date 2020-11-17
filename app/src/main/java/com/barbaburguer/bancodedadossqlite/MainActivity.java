package com.barbaburguer.bancodedadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            //criar banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE,null);

            //criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(nome VARCHAR,idade INT(3))");
            //inserir dados
            bancoDados.execSQL("INSERT INTO pessoas(nome,idade) VALUES ('Victor Augusto',28)");
            bancoDados.execSQL("INSERT INTO pessoas(nome,idade) VALUES ('Mariana Matuo',25)");
            //recuperar pessoas

            Cursor cursor = bancoDados.rawQuery("SELECT nome,idade FROM pessoas",null);

            //indice tabelas
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            //jogar o cursos para o primeiro item da lista
            cursor.moveToFirst();
            while(cursor != null){
                Log.i("RESULTADO - Nome:", cursor.getString(indiceNome));
                Log.i("RESULTADO - Idade:", cursor.getString(indiceIdade));
                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();

        }


    }
}
