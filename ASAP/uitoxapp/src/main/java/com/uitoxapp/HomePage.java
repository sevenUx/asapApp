package com.uitoxapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
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

public class HomePage extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private SliderLayout sliderShow;
    private GridView gridView;
    private List<SearchResponse> SearchList = new ArrayList<SearchResponse>();
    private MyAdapter adapter;
    private Gson gson = new Gson();
    //action bar
    private ActionBar actionBar;
    private TextView mActionBarTitle;
    private ImageButton mBtnSearch;
    private Gallery gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getUIControl();

        actionBar.hide();
        mBtnSearch.setOnClickListener(btnSearchOnClick);

        setData();

        sliderShow = (SliderLayout) findViewById(R.id.banner);
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("v01", "http://img04-tw1.uitoximg.com/A/deploy/AWC000001/1441934217_201507AP2900000008.jpg");
        url_maps.put("v02", "http://img04-tw1.uitoximg.com/A/deploy/AWC000001/1441881593_201507AP2900000008.jpg");
        url_maps.put("v03", "http://img04-tw1.uitoximg.com/A/deploy/AWC000001/1441934222_201507AP2900000008.jpg");
        url_maps.put("v04", "http://img04-tw1.uitoximg.com/A/deploy/AWC000001/1441879458_201507AP2900000008.jpg");
        url_maps.put("v05", "http://img04-tw1.uitoximg.com/A/deploy/AWC000001/1441880909_201507AP2900000008.jpg");
        url_maps.put("v06", "http://img04-tw1.uitoximg.com/A/deploy/AWC000001/1441934228_201507AP2900000008.jpg");

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(HomePage.this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);

            sliderShow.addSlider(textSliderView);
        }
        sliderShow.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderShow.setCustomAnimation(new DescriptionAnimation());
        sliderShow.setDuration(4000);
        sliderShow.addOnPageChangeListener(this);
    }

    private void getUIControl(){
        actionBar = getSupportActionBar();
        mActionBarTitle = (TextView) findViewById(R.id.txtTitle);
        mBtnSearch = (ImageButton) findViewById(R.id.btnSearch);
        gallery = (Gallery)findViewById(R.id.gallery);
        gridView = (GridView) findViewById(R.id.gridView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
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

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {

    }

    @Override
    public void onResume() {
        sliderShow.startAutoCycle();
        super.onResume();
    }

    //當activity要停止時,動畫也跟著停止
    @Override
    public void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }

    private View.OnClickListener btnSearchOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent it = new Intent();
            it.setClass(HomePage.this, SearchPage.class);
            startActivity(it);
        }
    };


    private void setData()
    {
        //productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        SearchList.clear();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("account", "01_uitoxtest");
        hashMap.put("password", "Aa1234%!@#");
        hashMap.put("platform_id", "AW000001");
        hashMap.put("version", "1.0.0");
        HashMap<String, String> subMap = new HashMap<String, String>();
        subMap.put("q", "htc");
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
        adapter = new MyAdapter<SearchResponse>(this, SearchList, R.layout.grid_row_item) {
            @Override
            public void convert(ViewHolder holder, SearchResponse searchResponse, View convertView, int position) {

                holder.setText(R.id.ProductName, searchResponse.getSM_NAME())
                        //.setText(R.id.Describe, searchResponse.getMarket_info().getPrice().getShow_price().toString())
                        .setText(R.id.ProductPriceOg, searchResponse.getMarket_info().getPrice().getShow_price().price + "")
                        .setText(R.id.ProductPrice, searchResponse.getMarket_info().getPrice().getFinal_price().price + "")
                        .setImageUrl(R.id.ProductImage, searchResponse.getSM_PIC());
            }

        };

        gridView.setAdapter(adapter);
        gallery.setAdapter(adapter);
    }

}
