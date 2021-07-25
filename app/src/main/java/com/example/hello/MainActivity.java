package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hello.util.ToastUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnLogin;
    private EditText mEtUser;
    private  EditText mEtpasswd;
    private TextView mTvRegist;
    private TextView mTvForgetPasswd;
    private String ADMIN="admin";
    private String PASSWD="111111";
    private String username="";
    private String passwd="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到控件
        mBtnLogin = findViewById(R.id.mBtnLogin);
        mEtUser = findViewById(R.id.et_1);
        mEtpasswd = findViewById(R.id.et_2);
        mTvRegist = findViewById(R.id.tv_regist);
        mTvForgetPasswd = findViewById(R.id.tv_forget);

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
        mTvRegist.setOnClickListener((View.OnClickListener) this);
        mTvForgetPasswd.setOnClickListener((View.OnClickListener) this);
    }
    public void onClick(View v){
        get_username();
        switch(v.getId()) {
            case R.id.mBtnLogin:
                login();
                break;
            case R.id.tv_regist:
                regist_main();
                break;
            case R.id.tv_forget:
                forget();
                break;
            default:
        }
    }
    public void login(){

        //判断是否匹配
        if (username.isEmpty() || passwd.isEmpty()){
            ToastUtil.showMsg(MainActivity.this, this.getString(R.string.NullNOTE));
        }else if(username.equals("xujb") && passwd.equals("123456")){
            //正确匹配到
            //Toast.makeText(getApplicationContext(),OK,Toast.LENGTH_SHORT).show();
            //封装好的
            //ToastUtil.showMsg(getApplicationContext(),OK);
            ToastUtil.showMsg(MainActivity.this,this.getString(R.string.loginOK));
            Intent intent = null;
            intent = new Intent(MainActivity.this, FunctionActivity.class);
            startActivity(intent);
        }else if(username.equals(ADMIN) && passwd.equals(PASSWD)){
            ToastUtil.showMsg(MainActivity.this,this.getString(R.string.ADMIN_OK));
            Intent intent = null;
            intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }else {
            //匹配失败
//            Toast tostcenter = Toast.makeText(getApplicationContext(),FAIL,Toast.LENGTH_SHORT);
//            tostcenter.setGravity(Gravity.CENTER,0,0);
//            tostcenter.show();
            ToastUtil.showMsg(MainActivity.this, this.getString(R.string.loginFAIL));
        }
    }
    public void regist_main(){
        ToastUtil.showMsg(MainActivity.this, this.getString(R.string.REGIST_NOTE));
    }
    public void forget(){
        ToastUtil.showMsg(MainActivity.this, this.getString(R.string.FORGET_NOTE));
    }
    public void get_username(){
        //需要获取用户名密码
        username = mEtUser.getText().toString();
        passwd   = mEtpasswd.getText().toString();
    }
}