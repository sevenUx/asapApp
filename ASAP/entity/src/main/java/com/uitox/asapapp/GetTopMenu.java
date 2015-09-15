package com.uitox.asapapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seven on 2015/8/25.
 */
public class GetTopMenu {
    private static List<TopMenu> selectedTop = new ArrayList<TopMenu>();

    static {

        selectedTop.add(new TopMenu("", "家電", 0, "com.uitoxapp.ListViewFragmentView"));
        selectedTop.add(new TopMenu("", "手機", 1, "com.uitoxapp.ListViewFragmentView"));
        selectedTop.add(new TopMenu("", "服飾", 2, "com.uitoxapp.ListViewFragmentView"));
        selectedTop.add(new TopMenu("", "精品", 4, "com.uitoxapp.ListViewFragmentView"));
        selectedTop.add(new TopMenu("", "女鞋", 5, "com.uitoxapp.ListViewFragmentView"));
        selectedTop.add(new TopMenu("", "中秋", 6, "com.uitoxapp.ListViewFragmentView"));
        selectedTop.add(new TopMenu("", "日用", 7, "com.uitoxapp.ListViewFragmentView"));
        selectedTop.add(new TopMenu("", "資訊", 8, "com.uitoxapp.ListViewFragmentView"));
        selectedTop.add(new TopMenu("", "相機", 9, "com.uitoxapp.ListViewFragmentView"));
    }

    public static List<TopMenu> getSelectedTopMenu() {
        return selectedTop;
    }
}
