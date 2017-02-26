package com.sha1607.smallmovie.discover.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sha1607.smallmovie.R;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/29 9:47
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class FollowFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow,container,false);
        return view;
    }
}
