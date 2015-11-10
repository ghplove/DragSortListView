package com.lr.ghp.dragsortlistview.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.lr.ghp.dragsortlistview.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EditlistActivity extends ActionBarActivity {
    @InjectView(R.id.my_awesome_toolbar)Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editlist);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
    }
    @OnClick(R.id.backBtn) void goBack(){
        this.finish();
    }
}
