package com.hhu.map.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.map.R;
import com.hhu.map.model.User;
import com.hhu.map.utils.Utils;

import java.util.logging.LogRecord;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm)
    EditText etConfirm;
    @BindView(R.id.bt_female)
    RadioButton btFemale;
    @BindView(R.id.bt_male)
    RadioButton btMale;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;
    @BindView(R.id.bt_register)
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
    }

    @OnClick({R.id.et_phone, R.id.et_password, R.id.et_confirm, R.id.bt_female, R.id.bt_male, R.id.rg_sex, R.id.bt_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_phone:
                break;
            case R.id.et_password:
                break;
            case R.id.et_confirm:
                break;
            case R.id.bt_female:
                break;
            case R.id.bt_male:
                break;
            case R.id.rg_sex:
                break;
            case R.id.bt_register:
                break;
        }

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        });
    }

    public void init(){
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.bt_male:
                        btMale.setBackground(getResources().getDrawable(R.drawable.bg_checked));
                        btMale.setTextColor(getColor(R.color.icon_blue));
                        btFemale.setBackground(getResources().getDrawable(R.drawable.bg_normal));
                        btFemale.setTextColor((getColor(R.color.gray)));
                        break;
                    case R.id.bt_female:
                        btFemale.setBackground(getResources().getDrawable(R.drawable.bg_checked));
                        btFemale.setTextColor(getColor(R.color.icon_blue));
                        btMale.setBackground(getResources().getDrawable(R.drawable.bg_normal));
                        btMale.setTextColor((getColor(R.color.gray)));
                        break;
                }
            }
        });
    }

    private void verify(){
        if(! Utils.registerLoginPhone(etPhone.getText().toString().trim())) {      //trim()去除空格
            Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if(Utils.isEmpty(etPassword.getText().toString().trim())){
            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(Utils.isEmpty(etConfirm.getText().toString().trim())){
            Toast.makeText(RegisterActivity.this, "请确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(etPassword.getText().toString().trim().length() < 6) {
            Toast.makeText(RegisterActivity.this,"密码不能小于6位",Toast.LENGTH_SHORT).show();
            return;
        }
        if(! etPassword.getText().toString().trim().equals(etConfirm.getText().toString().trim())){
            Toast.makeText(RegisterActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User();
        user.setPhone(etPhone.getText().toString().trim());
        user.setSex(btFemale.isChecked() ? "女" : "男");
        user.setPassword(etPassword.getText().toString().trim());

        Utils.save(RegisterActivity.this,user);
        Intent mIntent = new Intent();
        RegisterActivity.this.setResult(200,mIntent);
        finish();

    }
}
