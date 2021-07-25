package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hello.util.ToastUtil;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "";
    private Button mBtnLogin;
    private EditText mEtUser;
    private  EditText mEtpasswd;
    private  EditText mEtpasswdAgain;
    private String username="";
    private String passwd="";
    private String passwd_again;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //找到控件
        mBtnLogin = findViewById(R.id.mBtnLogin);
        mEtUser = findViewById(R.id.et_1);
        mEtpasswd = findViewById(R.id.et_2);
        mEtpasswdAgain = findViewById(R.id.et_3);

        //实现跳转
//        mBtnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = null;
//                intent = new Intent(MainActivity.this, FunctionActivity.class);
//                startActivity(intent);
//            }
//        });
        mBtnLogin.setOnClickListener((View.OnClickListener) this);
    }
    public void onClick(View v){
        get_username();
        switch(v.getId()) {
            case R.id.mBtnLogin:
                int ret = check_in();
                //if(ret == 1) {
                    //regist();
                //}
                break;
            default:
        }
    }
    public void regist(){

        // 1、拿到SharedPreference对象
        SharedPreferences loginInfoSP = getSharedPreferences("login_info", MODE_PRIVATE);

        // 2、获取Editor对象
        SharedPreferences.Editor writer = loginInfoSP.edit();
        // 3、通过Editor存储数据
        writer.putString(username, passwd);
        // 3、调用提交方法
        boolean commit = writer.commit();
        Log.i(TAG, "userLogin: commitRet = " + commit);

        ToastUtil.showMsg(RegisterActivity.this,this.getString(R.string.REGISTOK));
//        Intent intent = null;
//        intent = new Intent(RegisterActivity.this, MainActivity.class);
//        startActivity(intent);

    }

    public void get_username(){
        //需要获取用户名密码
        username     = mEtUser.getText().toString();
        passwd       = mEtpasswd.getText().toString();
        passwd_again = mEtpasswdAgain.getText().toString();
    }
    public int check_in() {
        if (username.isEmpty() || passwd.isEmpty()) {
            ToastUtil.showMsg(RegisterActivity.this, this.getString(R.string.NullNOTE));
            return 0;
        } else if (!passwd.equals(passwd_again)) {
            ToastUtil.showMsg(RegisterActivity.this, this.getString(R.string.DIFFERENT));
            return 0;
        }

        SharedPreferences reder = getSharedPreferences("login_info", MODE_WORLD_READABLE);
        String value = reder.getString(username, "");
        if (value.isEmpty()){
            ToastUtil.showMsg(RegisterActivity.this, this.getString(R.string.REGIST_FAIL));
            return 0;
        }
        return 1;

    }
}