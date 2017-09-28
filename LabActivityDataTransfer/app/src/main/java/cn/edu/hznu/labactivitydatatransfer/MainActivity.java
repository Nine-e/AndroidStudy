package cn.edu.hznu.labactivitydatatransfer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_reg=(Button)findViewById(R.id.register);
        btn_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText name = (EditText)findViewById(R.id.name);
                EditText password = (EditText)findViewById(R.id.password);
                RadioButton male = (RadioButton)findViewById(R.id.male);
                String gender = male.isChecked()?"男":"女";
                //将数据放到Bundle中
                Bundle bundle = new Bundle();
                bundle.putString("name",name.getText().toString());
                bundle.putString("password",password.getText().toString());
                bundle.putString("gender",gender);
                Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
    /*保存临时数据*/
   /* @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText name = (EditText)findViewById(R.id.name);
        EditText password = (EditText)findViewById(R.id.password);
        RadioButton male = (RadioButton)findViewById(R.id.male);
        String gender = male.isChecked()?"男":"女";

        outState.putString("name",name.getText().toString());
        outState.putString("password",password.getText().toString());
        outState.putString("gender",gender);

    }*/
}
