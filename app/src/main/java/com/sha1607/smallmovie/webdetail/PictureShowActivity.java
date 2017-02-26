package com.sha1607.smallmovie.webdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.utils.MyImageLoader;
import com.sha1607.smallmovie.webdetail.adapter.PictureAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureShowActivity extends AppCompatActivity {

    @BindView(R.id.vp_img_show)
    ViewPager mVpImgShow;
    @BindView(R.id.tv_num_current)
    TextView mTvNumCurrent;
    private PictureAdapter mAdapter;
    private List<ImageView> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_picture_show);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String[] shuzus = intent.getStringArrayExtra("shuzu");

        Toast.makeText(this, shuzus.length + "", Toast.LENGTH_SHORT).show();
        int current = 0;
        mList = new ArrayList<>();
        for (int i = 0; i < shuzus.length; i++) {
            if (image.equals(shuzus[i])) {
                current = i;
            }
            ImageView iv = new ImageView(this);
            Log.e("000000", shuzus[i] + "  " + i);
                MyImageLoader.with(this, shuzus[i], iv, MyImageLoader.LoaderEnum.GLIDE);
            mList.add(iv);
        }
        mTvNumCurrent.setText(current+1+" "+"/"+" "+mList.size());

        mAdapter = new PictureAdapter(this, mList);
        mVpImgShow.setAdapter(mAdapter);
        mVpImgShow.setCurrentItem(current);
        mVpImgShow.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                   mTvNumCurrent.setText(position+1+" "+"/"+" "+mList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
