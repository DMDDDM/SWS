package com.dmdddm.sws;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.dmdddm.sws.VerifyStoragePermissions.verifyStoragePermissions;

public class MainActivity extends AppCompatActivity {

    private IndexFragment indexFragment;
    private CenterFragment centerFragment;
    private SwitchFragment switchFragment;
    private int lastShowFragment = 0;   //表示最后一个显示的Fragment
    private Fragment[] fragments;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (lastShowFragment != 0){
                        switchFrament(lastShowFragment,0);
                        lastShowFragment = 0;
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if (lastShowFragment != 1){
                        switchFrament(lastShowFragment,1);
                        lastShowFragment = 1;
                    }

                    return true;
                case R.id.navigation_notifications:
                    if (lastShowFragment != 2){
                        switchFrament(lastShowFragment,2);
                        lastShowFragment = 2;
                    }

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragments();
        getSupportActionBar().hide();

        //创建一个MyDbOpenHelper
        //MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(this,null,null,1);

    }
    private void initFragments() {
        indexFragment = new IndexFragment();
        switchFragment = new SwitchFragment();
        centerFragment = new CenterFragment();
        fragments = new Fragment[]{indexFragment, switchFragment, centerFragment};
        lastShowFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment, indexFragment)
                .show(indexFragment)
                .commit();
    }
    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    public void switchFrament(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.fragment, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }


}
