package com.hhu.map.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhu.map.R;
import com.hhu.map.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.Thread.sleep;

public class LoginActivity extends Activity {

    @BindView(R.id.iv_icon_center)
    ImageView ivIconCenter;
    @BindView(R.id.rl_logo)
    RelativeLayout rlLogo;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.delete_username)
    ImageButton deleteUsername;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.delete_password)
    ImageButton deletePassword;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.login_11)
    LinearLayout login11;
    @BindView(R.id.iv_icon_left)
    ImageView ivIconLeft;
    @BindView(R.id.iv_icon_right)
    ImageView ivIconRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ivIconLeft.setImageResource(R.mipmap.ic_22_hide);
                    ivIconRight.setImageResource(R.mipmap.ic_33_hide);
                } else {
                    ivIconLeft.setImageResource(R.mipmap.ic_22);
                    ivIconRight.setImageResource(R.mipmap.ic_33);
                }
            }
        });

        etLoginPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ivIconLeft.setImageResource(R.mipmap.ic_22_hide);
                    ivIconRight.setImageResource(R.mipmap.ic_33_hide);
                } else {
                    ivIconLeft.setImageResource(R.mipmap.ic_22);
                    ivIconRight.setImageResource(R.mipmap.ic_33);
                }
            }
        });

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    deleteUsername.setVisibility(View.VISIBLE);
                } else {
                    deleteUsername.setVisibility(View.INVISIBLE);
                }
            }
        });

        etLoginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    deletePassword.setVisibility(View.VISIBLE);
                } else {
                    deletePassword.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @OnClick({R.id.delete_username, R.id.tv_register, R.id.bt_login, R.id.delete_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete_username:
                etUsername.setText("");
                break;

            case R.id.delete_password:
                etLoginPassword.setText("");
                break;

            case R.id.tv_register:
                if(Utils.prevent()){
                    Toast.makeText(LoginActivity.this,"点击速度过快，请稍后再试",Toast.LENGTH_SHORT);
                    return;
                }
                Intent mIntent = new Intent(LoginActivity.this,RegisterActivity.class);

                startActivityForResult(mIntent,1);
                //startActivity(mIntent);
                //startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.bt_login:
                login();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 &&  resultCode == 200){
            etUsername.setText(Utils.get(LoginActivity.this,"phone"));
            etLoginPassword.setText(Utils.get(LoginActivity.this,"password"));
        }
    }

    private void login(){
        if(Utils.isEmpty(etUsername.getText().toString().trim())){
            Toast.makeText(LoginActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!Utils.registerLoginPhone(etUsername.getText().toString().trim())){
            Toast.makeText(LoginActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Utils.isEmpty(etLoginPassword.getText().toString().trim())){
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(etUsername.getText().toString().trim().equals(Utils.get(LoginActivity.this,"phone"))
         && etLoginPassword.getText().toString().trim().equals(Utils.get(LoginActivity.this,"password"))){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }else {
            Toast.makeText(LoginActivity.this,"手机号或者密码错误，请再次输入",Toast.LENGTH_SHORT).show();
            return;
        }
    }

}
