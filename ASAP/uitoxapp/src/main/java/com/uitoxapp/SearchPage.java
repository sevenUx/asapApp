package com.uitoxapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.uitox.adapter.MyAdapter;
import com.uitox.adapter.ViewHolder;
import com.uitox.asapapp.SearchResponse;
import com.uitox.http.NetWorkTool;
import com.uitox.http.UseJsonRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchPage extends AppCompatActivity {

    //action bar
    private ActionBar actionBar;
    private TextView mActionBarTitle;
    private SearchView mTxtSearch;

    private ListView listView;
    private List<SearchResponse> SearchList = new ArrayList<SearchResponse>();
    private MyAdapter adapter;
    private LinearLayout mSortMenu;
    private Gson gson = new Gson();
    private ImageButton mBtnSearch;
    private ImageButton mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        getUIControl();
        actionBar.hide();
        mActionBarTitle.setText(R.string.title_activity_search_view);
        //mSortMenu.setVisibility(LinearLayout.GONE);
        mBtnSearch.setVisibility(LinearLayout.GONE);
        mBtnBack.setOnClickListener(btnBackOnClick);



        setSearchTextEvent();
    }

    private void getUIControl(){
        actionBar = getSupportActionBar();
        mActionBarTitle = (TextView) findViewById(R.id.txtTitle);
        mSortMenu = (LinearLayout) findViewById(R.id.menuSort);
        mTxtSearch = (SearchView) findViewById(R.id.txtSearch);
        mBtnSearch = (ImageButton) findViewById(R.id.btnSearch);
        mBtnBack = (ImageButton) findViewById(R.id.btnBack);
        listView = (ListView) findViewById(R.id.listView);
    }

    private  void setSearchTextEvent()
    {
        mTxtSearch.setIconifiedByDefault(true);

        mTxtSearch.setSubmitButtonEnabled(true);
        mTxtSearch.onActionViewExpanded();
        //mTxtSearch.setBackgroundColor(Color.GRAY);
        mTxtSearch.setQueryHint("查詢");

        try {
//            Field field = mTxtSearch.getClass().getDeclaredField("mSubmitButton");
//
//            field.setAccessible(true);
//
//            ImageView iv = (ImageView) field.get(mTxtSearch);
//
//            iv.setImageDrawable(this.getResources().getDrawable(
//                    R.drawable.ic_search_24dp));


        } catch (Exception e) {

            e.printStackTrace();
        }

//        Cursor cursor = this.getTestCursor();

//        @SuppressWarnings("deprecation")
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
//                R.layout.mytextview, cursor, new String[] { "tb_name" },
//                new int[] { R.id.textview });
//
//        sv.setSuggestionsAdapter(adapter);

        mTxtSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String str) {

                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String str) {
                setData(str);
                //mSortMenu.setVisibility(LinearLayout.VISIBLE);
                return false;
            }

        });

    }

    private void setData(String str)
    {
        //productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        SearchList.clear();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("account", "01_uitoxtest");
        hashMap.put("password", "Aa1234%!@#");
        hashMap.put("platform_id", "AW000001");
        hashMap.put("version", "1.0.0");
        HashMap<String, String> subMap = new HashMap<String, String>();
        subMap.put("q", str);
        hashMap.put("data", subMap);

        JSONObject params=new JSONObject(hashMap);
        //RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://uxapi.uitoxbeta.com/web_search/get_app_show_main_multi";

        UseJsonRequest objRequest = new UseJsonRequest(url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject obj) {
                        try {
                            JSONArray result = obj.getJSONArray("list");

                            JsonParser parser = new JsonParser();
                            JsonArray jsonArray = parser.parse(result.toString()).getAsJsonArray();

                            for(JsonElement je : jsonArray){
                                SearchResponse t = gson.fromJson(je, SearchResponse.class);
                                SearchList.add(t);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (Exception ex) {
                            ex.getMessage();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }

        });
        objRequest.setTag("obj");
        NetWorkTool.getInstance(this).addToRequestQueue(objRequest);

        setListOrGridView();
    }

    //設定GRID和LISTVEEW
    private void setListOrGridView()
    {
        adapter = new MyAdapter<SearchResponse>(this, SearchList, R.layout.list_row_item) {
            @Override
            public void convert(ViewHolder holder, SearchResponse searchResponse, View convertView, int position) {

                holder.setText(R.id.ProductName, searchResponse.getSM_NAME())
                        //.setText(R.id.Describe, searchResponse.getMarket_info().getPrice().getShow_price().toString())
                        .setText(R.id.ProductPriceOg, searchResponse.getMarket_info().getPrice().getShow_price().price + "")
                        .setText(R.id.ProductPrice, searchResponse.getMarket_info().getPrice().getFinal_price().price + "")
                        .setImageUrl(R.id.ProductImage, searchResponse.getSM_PIC());
            }

        };

        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_view, menu);
        return true;
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

    private View.OnClickListener btnBackOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            SearchPage.this.finish();
        }
    };

}
