package com.sha1607.smallmovie.webdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.AuthorBean;
import com.sha1607.smallmovie.utils.EndlessRecyclerOnScrollListener;
import com.sha1607.smallmovie.webdetail.adapter.AuthorAdapter;
import com.sha1607.smallmovie.webdetail.presenter.AuthorPresenter;
import com.sha1607.smallmovie.webdetail.view.AuthorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthorActivity extends AppCompatActivity implements AuthorView {
    @BindView(R.id.rl_author_artilce)
    RecyclerView mRlAuthorArtilce;
    @BindView(R.id.toolbar_title_author)
    TextView mToolbarTitleAuthor;

    private AuthorPresenter mAuthorPresenter;
    private AuthorAdapter mAuthorAdapter;
    private String type = "a3f48d322ea843fda8c6f2e8209499f8";
    private int id = 1;
    private String loadMore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        ButterKnife.bind(this);
        Intent intent=getIntent();
         type = intent.getStringExtra("type");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRlAuthorArtilce.setLayoutManager(linearLayoutManager);
        mAuthorPresenter = new AuthorPresenter(this);
        mAuthorPresenter.lodaData(this.type, id);
        mAuthorAdapter = new AuthorAdapter(this);
        mRlAuthorArtilce.setAdapter(mAuthorAdapter);


        mRlAuthorArtilce.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                if (loadMore.equals("1")) {
                    mAuthorPresenter.lodaData(AuthorActivity.this.type, ++id);
                }
            }
        });


    }

    @Override
    public void loadData(AuthorBean datas) {
        mAuthorAdapter.setData(datas.getArticleList());
        loadMore = datas.getCanLoadMore();
        if (id == 1) {
            mToolbarTitleAuthor.setText(datas.getAuthorInfo().getNickname() + "的文章");
        }
    }
}
