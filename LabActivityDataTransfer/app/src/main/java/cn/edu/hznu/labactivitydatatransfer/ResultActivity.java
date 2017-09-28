package cn.edu.hznu.labactivitydatatransfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by del on 2017/9/27.
 */

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView name = (TextView)findViewById(R.id.name);
        TextView password = (TextView)findViewById(R.id.password);
        TextView gender = (TextView)findViewById(R.id.gender);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name.setText(bundle.getString("name"));
        password.setText(bundle.getString("password"));
        gender.setText(bundle.getString("gender"));

        Button btn_ret = (Button)findViewById(R.id.returnButton);
        btn_ret.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
