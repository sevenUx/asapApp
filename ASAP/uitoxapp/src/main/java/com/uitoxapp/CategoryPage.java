package com.uitoxapp;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.uitox.adapter.MyAdapter;
import com.uitox.adapter.ViewHolder;
import com.uitox.asapapp.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryPage extends AppCompatActivity implements NavigationDrawerFragmentView.NavigationDrawerCallbacks {

    //導覽列
//    private DrawerLayout mDrawerLayout;
//    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationDrawerFragmentView mNavigationDrawerFragment;

    //action bar
    private ActionBar actionBar;
    private TextView mActionBarTitle;
    private ImageButton mBtnSearch;

    private ListView listView;
    private List<CategoryResponse> CategoryList = new ArrayList<CategoryResponse>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        mNavigationDrawerFragment = (NavigationDrawerFragmentView) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer_right);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer_right, (DrawerLayout) findViewById(R.id.drawer_layout));

        actionBar = getSupportActionBar();
        actionBar.hide();
        mActionBarTitle = (TextView) findViewById(R.id.txtTitle);
        mActionBarTitle.setText("全站商品分類");
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);


        listView = (ListView) findViewById(R.id.listView);
        CategoryList.add(new CategoryResponse("閃購"));
        CategoryList.add(new CategoryResponse("家電"));
        CategoryList.add(new CategoryResponse("手機"));
        CategoryList.add(new CategoryResponse("服飾"));
        CategoryList.add(new CategoryResponse("精品"));
        CategoryList.add(new CategoryResponse("女鞋"));
        CategoryList.add(new CategoryResponse("中秋"));
        CategoryList.add(new CategoryResponse("日用"));
        setListOrGridView();

    }

    private void setListOrGridView()
    {
        adapter = new MyAdapter<CategoryResponse>(this, CategoryList, R.layout.category_item1) {
            @Override
            public void convert(ViewHolder holder, CategoryResponse categoryResponse, View convertView, int position) {

                holder.setText(R.id.txtItemName, categoryResponse.getItemName());
            }
        };

        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.menu_category_page, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }

}
