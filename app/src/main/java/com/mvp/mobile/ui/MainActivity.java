package com.mvp.mobile.ui;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import com.example.kituri.demomvp.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import com.mvp.mobile.ui.fragment.Mvp02Fragment;
import com.mvp.mobile.ui.fragment.Mvp00Fragment;
import com.mvp.mobile.ui.fragment.Mvp03Fragment;
import com.mvp.mobile.ui.fragment.Mvp01Fragment;

public class MainActivity extends BaseActivity {

    private BottomBar mBottomBar;
    private TextView tv_submit;
    private Button btn_submit;
    private Fragment mContent;

    private Mvp00Fragment lf;
    private Mvp01Fragment sf;
    private Mvp02Fragment df;
    private Mvp03Fragment mf;

    static final String tags[] = {"", "", "", ""};

    static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiFragment();
        stateCheck(savedInstanceState);
        initBottomBar(savedInstanceState);
    }

    private void initBottomBar(Bundle savedInstanceState) {
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId){
                    case R.id.bb_menu_my_lesson:
                        switchContent(mContent, lf, 0);
                        break;
                    case R.id.bb_menu_schedule:
                        switchContent(mContent, sf, 1);
                        break;
                    case R.id.bb_menu_discover:
                        switchContent(mContent, df, 2);
                        break;
                    case R.id.bb_menu_mine:
                        switchContent(mContent, mf, 3);
                        break;

                    default:
                        switchContent(mContent, lf, 0);
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });

        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.colorPrimary));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        stateCheck(outState);
        if(mBottomBar != null){
            mBottomBar.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mContent = null;
        lf = null;
        sf = null;
        df = null;
        mf = null;
    }

    /**
     * fragment 切换
     *
     * @param from
     * @param to
     */
    public void switchContent(Fragment from, Fragment to, int position) {
        if (mContent != to) {
            mContent = to;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(from)
                        .add(R.id.content_frame, to, tags[position]).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }


    private void intiFragment() {
        lf = new Mvp00Fragment();
        sf = new Mvp01Fragment();
        df = new Mvp02Fragment();
        mf = new Mvp03Fragment();
    }

    /**
     * 状态检测 用于内存不足的时候保证fragment不会重叠
     *
     * @param savedInstanceState
     */
    private void stateCheck(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
            Mvp00Fragment af = new Mvp00Fragment();
            mContent = af;
            fts.add(R.id.content_frame, af);
            fts.commit();
        } else {
            Mvp00Fragment lf = (Mvp00Fragment) getSupportFragmentManager()
                    .findFragmentByTag(tags[0]);
            Mvp01Fragment sf = (Mvp01Fragment) getSupportFragmentManager()
                    .findFragmentByTag(tags[1]);
            Mvp02Fragment df = (Mvp02Fragment) getSupportFragmentManager()
                    .findFragmentByTag(tags[2]);
            Mvp03Fragment mf = (Mvp03Fragment) getSupportFragmentManager()
                    .findFragmentByTag(tags[3]);
            getSupportFragmentManager().beginTransaction().show(lf).hide(sf).hide(df)
                    .hide(mf).commit();
        }
    }

}
