package com.bawei.fersco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.ContentView;

@ContentView(R.layout.activity_base)
public class BaseActivity extends AppCompatActivity {

    @BindView(R.id.base_text)
    public TextView base_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        base_text.setText("sssssss");
    }
}
