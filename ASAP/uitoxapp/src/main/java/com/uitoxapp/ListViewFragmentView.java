package com.uitoxapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.uitox.adapter.MyAdapter;
import com.uitox.adapter.ViewHolder;
import com.uitox.asapapp.Product;
import com.uitox.http.NetWorkTool;
import com.uitox.view.BaseFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seven on 2015/8/26.
 */
public class ListViewFragmentView extends BaseFragmentView {

    private ListView listView;
    private GridView gridView;
    //private List<Movie> movieList = new ArrayList<Movie>();
    private List<Product> productList = new ArrayList<Product>();
    private MyAdapter adapter;
    private ImageButton mbtnImage;

    private boolean isGridView = false;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_fragment_view, container, false);

//        mbtnImage = (ImageButton)view.findViewById(R.id.imageButton);
//        mbtnImage.setOnClickListener(btnImageOnClick);

        listView = (ListView) view.findViewById(R.id.listView);
        //gridView = (GridView) view.findViewById(R.id.gridView);

        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));
        productList.add(new Product("iPhone6 Plus S", "搶先上市最新手機", "50000", "29999"));

        setListOrGridView();

        return view;
    }

    @Override
    public void initData() {
//        HashMap<String, String> hashMap = new HashMap<String, String>();
//        hashMap.put("un", "852041173");
//        hashMap.put("pw", "852041173abc");
//
//        GsonRequest<Movie> notifyRequest = new GsonRequest<Movie>(
//                getActivity(),
//                Request.Method.POST,
//                NetParams.getSHIHJIEUrl("/phpinfo.php"),
//                Movie.class,
//                null,
//                new GsonRequest.OnListResponseListener<Movie>() {
//                    @Override
//                    public void onResponse(List<Movie> response) {
//                        for (Movie res : response) {
//                            movieList.add(res);
//                        }
//                        adapter.notifyDataSetChanged();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                        ShowYourMessage.msgToShowShort(getActivity(), "ERROR");
//                    }
//                }, hashMap);
//        NetWorkTool.getInstance(getActivity()).addToRequestQueue(notifyRequest);
    }

    @Override
    public void initBundle(Bundle bundle) {

    }

    @Override
    public void onStop() {
        super.onStop();
        NetWorkTool.getInstance(getActivity()).cancelAll(this);
    }

    @Override
    public void updateUI() {

    }

    private View.OnClickListener btnImageOnClick = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
                //切換LIST和GRID

//                if(isGridView)
//                {
//                    mbtnImage.setBackgroundResource(R.drawable.ic_action_view_as_grid);
//                    isGridView = false;
//                }
//                else
//                {
//                    mbtnImage.setBackgroundResource(R.drawable.ic_action_view_as_list);
//                    isGridView = true;
//                }
//                setListOrGridView();
            }
    };

    //設定GRID和LISTVEEW
    private void setListOrGridView()
    {
//        int layoutId = isGridView ? R.layout.grid_row : R.layout.list_row;

        adapter = new MyAdapter<Product>(getActivity(), productList, R.layout.list_row_item) {
            @Override
            public void convert(ViewHolder holder, Product product, View convertView, int position) {
                holder.setText(R.id.ProductName, product.getTitle())
//                        .setText(R.id.Describe, product.getDescribe())
//                        .setText(R.id.OrgPrice, product.getOrgPrice())
//                        .setText(R.id.RelPrice, product.getRelPrice())
                        .setImageUrl(R.id.ProductImage, "");
            }
        };

        //listView.setVisibility(View.VISIBLE);
        listView.setAdapter(adapter);

//        if(isGridView) {
//            listView.setVisibility(View.GONE);
//            gridView.setVisibility(View.VISIBLE);
//            gridView.setAdapter(adapter);
//        }
//        else {
//            listView.setVisibility(View.VISIBLE);
//            gridView.setVisibility(View.GONE);
//            listView.setAdapter(adapter);
//        }
    }

}
