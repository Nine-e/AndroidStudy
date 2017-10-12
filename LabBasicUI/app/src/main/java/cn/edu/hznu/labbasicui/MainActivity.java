package cn.edu.hznu.labbasicui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressBar progressBar;
    private EditText txtNumber;
    private Button btnChange;
    private Button btnWatch;
    private int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取布局中的组件
        progressBar=(ProgressBar)findViewById(R.id.progressBar1);
        txtNumber=(EditText)findViewById(R.id.txt_number);
        btnChange=(Button)findViewById(R.id.btn_change);
        btnChange.setOnClickListener(this);
        btnWatch=(Button)findViewById(R.id.btn_watch);
        btnWatch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //设置点击事件
        int id = view.getId();
        switch (id){
            case R.id.btn_change://点击“修改”按钮
                String strValue=txtNumber.getText().toString();
                if (strValue!=null){
                    value=Integer.parseInt(strValue);
                    if (value>=0&&value<=100){
                        progressBar.setProgress(value);
                        //修改进度条当前值
                    }
                    else{
                        //弹出消息提示框
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("Indication");
                        dialog.setMessage("输入的数字不合法！");
                        dialog.setCancelable(false);//表示不能通过Back键取消
                        dialog.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {

                            }
                        });
                        dialog.show();//显示提示对话框

                    }
                }
                txtNumber.setText("");
                break;
            case R.id.btn_watch://点击“查看”按钮
                int progress = progressBar.getProgress();
                //获取进度条当前值
                android.support.v7.app.AlertDialog.Builder dialog = new
                        android.support.v7.app.AlertDialog.Builder(MainActivity.this);
               /*AlertDialog.Builder dialog = new
                        AlertDialog.Builder(MainActivity.this);*/
                dialog.setTitle("Progress Value");
                dialog.setMessage("当前值："+progress);
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
                /*dialog.setNegativeButton("can",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });*/
                dialog.show();
                break;
            default:
                break;

        }
    }
}
