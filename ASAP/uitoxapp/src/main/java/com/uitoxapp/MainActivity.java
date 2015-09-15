package com.uitoxapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.uitox.adapter.FragmentAdapter;
import com.uitox.asapapp.GetTopMenu;
import com.uitox.asapapp.TopMenu;
import com.uitox.lib.ShowYourMessage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //導覽列
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    //儲存action bar title
    private CharSequence mTitle;

    //action bar
    private ActionBar actionBar;
    private TextView mActionBarTitle;
    private ImageButton mBtnSearch;

    //存放topmenu和viewpage的LIST
    List<TopMenu> TopMenuList;

    //VIEWPAGER要用的容器
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private ViewPager mPageVp;

    //動態產生tab
    private RadioGroup tab = null;
    private HorizontalScrollView hvChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
    }

    //初始化VIEW
    private void initView() {

        //預設取APP名字
        mTitle = getTitle();

        //設ActionBar
        mActionBarTitle = (TextView) findViewById(R.id.txtTitle);
        mBtnSearch = (ImageButton) findViewById(R.id.btnSearch);
        mBtnSearch.setOnClickListener(btnSearchOnClick);

        //restoreActionBar();

        //產生VIEWPAGER
        mPageVp = (ViewPager) findViewById(R.id.view_pager);

        //tab
        hvChannel = (HorizontalScrollView) findViewById(R.id.hvChannel);
        tab = (RadioGroup) findViewById(R.id.tab);

        //取得topmenu
        TopMenuList = GetTopMenu.getSelectedTopMenu();

        //初始化tab
        initTab();

        //初始化VIEWPAGE
        initViewPager();
    }

    //動態產生topmenu
    private void initTab() {
        for (int i = 0; i < TopMenuList.size(); i++) {
            RadioButton rb = (RadioButton) LayoutInflater.from(this).inflate(R.layout.top_menu_row, null);
            rb.setId(i);
            rb.setText(TopMenuList.get(i).getName());
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.FILL_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            tab.addView(rb, params);
        }

        //tab事件
        tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mPageVp.setCurrentItem(checkedId);
                ShowYourMessage.msgToShowShort(MainActivity.this, "我切換頁面了(" + TopMenuList.get(checkedId).getName() + ")");
            }
        });
    }

    //動態產生VIEWPAGE
    private void initViewPager() {
        for (int i = 0; i < TopMenuList.size(); i++) {
            try {
                //取得entity
                String classname = TopMenuList.get(i).getClassname();

                //動態實例物件
                Fragment frag = (Fragment) Class.forName(classname).newInstance();

                //針對這個class傳遞資料
                Bundle bundle = new Bundle();
                bundle.putString("name", TopMenuList.get(i).getName());
                frag.setArguments(bundle);

                //塞進FragmentList
                mFragmentList.add(frag);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);
        mPageVp.setOffscreenPageLimit(2);
        mPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //無論選到哪個都變換TITLE
                onSectionAttached(position);

                setTab(position);

                //更改ACTIONBAR TITLE
                mActionBarTitle.setText(mTitle);
                //actionBar.setTitle(mTitle);

                if (mPageVp != null) {
                    mPageVp.setCurrentItem(position);
                }
                invalidateOptionsMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //選到選單後要顯示的title
    public void onSectionAttached(int number) {
        if (TopMenuList != null) {
            mTitle = TopMenuList.get(number).getName();
        }
    }

    //滑動tab
    private void setTab(int idx) {
        RadioButton rb = (RadioButton) tab.getChildAt(idx);
        rb.setChecked(true);
        int left = rb.getLeft();
        int width = rb.getMeasuredWidth();
        DisplayMetrics metrics = new DisplayMetrics();
        super.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int len = left + width / 2 - screenWidth / 2;
        hvChannel.smoothScrollTo(len, 0);//滑動ScroollView
    }

    //重新配置actionbar
    public void restoreActionBar() {
//        actionBar = getSupportActionBar();
//        actionBar.hide();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayHomeAsUpEnabled(false);

        //左上的ICON
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_action_storage);
        //背景色
        //ColorDrawable colorDrawable = new ColorDrawable();
        //colorDrawable.setColor(getResources().getColor(R.color.bg_color));
        //colorDrawable.setColor(Color.WHITE);
        //actionBar.setBackgroundDrawable(colorDrawable);

        //actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private View.OnClickListener btnSearchOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent it = new Intent();
            it.setClass(MainActivity.this, SearchPage.class);
            startActivity(it);

        }
    };


}
