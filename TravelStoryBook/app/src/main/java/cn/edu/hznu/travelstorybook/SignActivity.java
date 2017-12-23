package cn.edu.hznu.travelstorybook;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SignActivity extends AppCompatActivity {
    private ImageButton closeBtn;
    private Button signBtn;
    private EditText numberET;
    private EditText passwordET;
    private EditText sureET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        setContentView(R.layout.activity_sign);

        closeBtn = (ImageButton)findViewById(R.id.sign_close_button);
        signBtn = (Button)findViewById(R.id.sign_button);
        numberET = (EditText)findViewById(R.id.sign_number_edit);
        passwordET = (EditText)findViewById(R.id.sign_password_edit);
        sureET = (EditText)findViewById(R.id.sign_sure_password_edit);

        //关闭按钮点击事件
        closeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
