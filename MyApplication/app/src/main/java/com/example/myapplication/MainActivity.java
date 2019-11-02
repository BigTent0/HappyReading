package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.myapplication.RequiestTest.RequestService;
import com.example.myapplication.RequiestTest.ResponseRec;
import com.example.myapplication.ResponseModel.User;
import com.example.myapplication.Spider.*;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText et_account;
    private EditText et_passwd;
    private Button bt_login;
    private Button bt_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_account = (EditText) findViewById(R.id.et_account);
        et_passwd = (EditText) findViewById(R.id.et_passwd);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_send = (Button) findViewById(R.id.bt_send);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = et_account.getText().toString();
                String pswd=et_passwd.getText().toString();
                login(account,pswd);
            }
        });

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        test.testLook(et_account.getText().toString());
                        }
                }).start();

            }
        });
    }

    private  void testIntro(){
        Introduction introduction= new IntroductionBuilder().getIntroduction("https://qxs.la","/72918/");
        System.out.println("作者："+introduction.getAuthor());
        System.out.println("小说名："+introduction.getName());
        System.out.println("类型："+introduction.getStyle());
        System.out.println("简介："+introduction.getIntroduction());
        for(Chapter chapter : introduction.getCatalog()){
            System.out.println(chapter.getName()+"链接："+chapter.getContent());
        }
    }

    public void login(String account,String pswd)
    {
        Retrofit retrofit= new Retrofit.Builder()
            .baseUrl("http://10.26.20.111:8080/")
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .build();
        RequestService requestService = retrofit.create(RequestService.class);
        Call<ResponseRec<User>> call = requestService.getCall(account,pswd);
        call.enqueue(new Callback<ResponseRec<User>>() {
            @Override
            public void onResponse(Call<ResponseRec<User>> call, Response<ResponseRec<User>> response) {

                Toast.makeText(MainActivity.this,"访问成功",Toast.LENGTH_SHORT).show();
                ResponseRec<User> responseRec= response.body();
                User user=responseRec.getData();
                System.out.println(responseRec.getMsg()+"  "+responseRec.getStatus());
                System.out.println(user.toString());
            }

            @Override
            public void onFailure(Call<ResponseRec<User>> call, Throwable t) {

                Toast.makeText(MainActivity.this,"访问失败",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
