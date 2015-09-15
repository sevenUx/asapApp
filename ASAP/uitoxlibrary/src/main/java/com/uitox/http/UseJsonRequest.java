package com.uitox.http;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by seven on 2015/9/3.
 */
public class UseJsonRequest<T> extends JsonObjectRequest {

    public UseJsonRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    public UseJsonRequest(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public UseJsonRequest(int method, String url, String requestBody, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    public UseJsonRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }

    public UseJsonRequest(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

//    public UseJsonRequest(Context context,int method, String url, Class<T> clazz, Map<String,String> headers,
//                          OnListResponseListener<T> listener, Response.ErrorListener errorListener,HashMap<String, Object> params){
//        super(url, listener, errorListener);
//
//        this.context = context;
//        this.clazz = clazz;
//        this.headers = headers;
//        this.listListener = listener;
//        this.listener = null;
//        //jObj = new JSONObject(params);
//
//        if (method == Method.POST && params != null && params.size() > 0) {
//            setRetryPolicy(new DefaultRetryPolicy(12000, 0,
//                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            postString = new GsonBuilder().create().toJson(params);
//        }
//
//    }

    public interface OnListResponseListener<T>{
        void onResponse(List<T> response);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String je = new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));
            return Response.success(new JSONObject(je), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException var3) {
            return Response.error(new ParseError(var3));
        } catch (JSONException var4) {
            return Response.error(new ParseError(var4));
        }

    }


}
