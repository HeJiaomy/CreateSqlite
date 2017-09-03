package com.createsqlite;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.createsqlite.bean.InfoBean;
import com.createsqlite.dao.InfoDao;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.btn_add)
    Button btnAdd;
    @InjectView(R.id.btn_del)
    Button btnDel;
    @InjectView(R.id.btn_update)
    Button btnUpdate;
    @InjectView(R.id.btn_query)
    Button btnQuery;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        context= this;

    }

    @OnClick({R.id.btn_add, R.id.btn_del, R.id.btn_update, R.id.btn_query})
    public void onViewClicked(View view) {

        InfoDao infoDao= new InfoDao(context);

        switch (view.getId()) {
            case R.id.btn_add:

                InfoBean bean= new InfoBean();

                bean.name= "张三";

                bean.phone= "110";

                boolean result= infoDao.add(bean);

                if (result)
                Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_del:

                int del= infoDao.del("张三");

                Toast.makeText(this,"成功删除"+del+"行",Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_update:

                InfoBean bean2= new InfoBean();

                bean2.name= "张三";

                bean2.phone= "119";

                int update= infoDao.update(bean2);

                Toast.makeText(this,"修改了"+update+"行",Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_query:

                infoDao.query("张三");

                break;
        }
    }
}
