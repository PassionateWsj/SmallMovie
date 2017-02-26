package com.sha1607.smallmovie.discover.adatper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.DiscoverListBean;
import com.sha1607.smallmovie.discover.widget.UpdateFragment;
import com.sha1607.smallmovie.moviedetail.MovieDetailActivity;
import com.sha1607.smallmovie.utils.MyImageLoader;
import com.sha1607.smallmovie.utils.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/29 11:03
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.MyHolder> {

    private Context mContext;
    private List<DiscoverListBean.RecommendsListBean> mDatas;
    private HashMap<Integer, Boolean> isChosen;
    private UpdateFragment.OnResultListener mListener;

    public UpdateAdapter(Context context, UpdateFragment.OnResultListener listener) {
        this.mContext = context;
        mDatas = new ArrayList<DiscoverListBean.RecommendsListBean>();
        isChosen = new HashMap<>();
        this.mListener = listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.update_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.pos = position;
        MyImageLoader.with(mContext, mDatas.get(position).getWriterInfo().getHeadImgUrl(), holder.mIvDiscoverWriter, MyImageLoader.LoaderEnum.GLIDECIRCLE);
        holder.mTvDiscoverNikname.setText(mDatas.get(position).getWriterInfo().getNickname());
        holder.mTvDiscoverDate.setText(TimeUtils.getStrTime(mDatas.get(position).getCreateDate() + ""));
        holder.mTvDiscoverComment.setText(mDatas.get(position).getRecommend());
        MyImageLoader.with(mContext, mDatas.get(position).getFilmResources().getImages().getLarge(), holder.mIvDiscoverFilm, MyImageLoader.LoaderEnum.GLIDE);
        holder.mTvDiscoverFilmName.setText(mDatas.get(position).getFilmResources().getTitle());

        if (mDatas.get(position).getFilmResources().getDirectors().size() != 0) {
            StringBuilder builder1 = new StringBuilder();
            for (int i = 0; i < mDatas.get(position).getFilmResources().getDirectors().size(); i++) {

                if (i == mDatas.get(position).getFilmResources().getDirectors().size() - 1) {
                    builder1.append(mDatas.get(position).getFilmResources().getDirectors().get(i).getName());
                } else {
                    builder1.append(mDatas.get(position).getFilmResources().getDirectors().get(i).getName() + "/");
                }
            }
            holder.mTvDiscoverFilmDirector.setText(builder1.toString());
        }
        if (mDatas.get(position).getFilmResources().getCasts().size() != 0) {
            StringBuilder builder2 = new StringBuilder();
            for (int i = 0; i < mDatas.get(position).getFilmResources().getCasts().size(); i++) {
                if (i == mDatas.get(position).getFilmResources().getCasts().size() - 1) {
                    builder2.append(mDatas.get(position).getFilmResources().getCasts().get(i).getName());
                } else {
                    builder2.append(mDatas.get(position).getFilmResources().getCasts().get(i).getName() + "/");
                }
            }
            holder.mTvDiscoverFilmActor.setText(builder2.toString());
        }

        holder.mTvPraise.setText(mDatas.get(position).getPraiseCount() + "");

        if (isChosen.get(position)) {
            holder.mIvLove.setImageResource(R.drawable.zan_press_new);
            holder.mTvPraise.setText(mDatas.get(position).getPraiseCount() + 1 + "");
        } else {
            holder.mIvLove.setImageResource(R.drawable.zan_new_white);
            holder.mTvPraise.setText(mDatas.get(position).getPraiseCount() + "");
        }
        if (holder.mTvPraise.getText().equals("0")) {
            holder.mTvPraise.setVisibility(View.INVISIBLE);
        } else {
            holder.mTvPraise.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private int pos;
        private ImageView mIvDiscoverWriter;
        private TextView mTvDiscoverNikname;
        private TextView mTvDiscoverDate;
        private TextView mTvDiscoverComment;
        private ImageView mIvDiscoverFilm;
        private TextView mTvDiscoverFilmName;
        private TextView mTvDiscoverFilmDirector;
        private TextView mTvDiscoverFilmActor;
        private TextView mTvPraise;
        private ImageView mIvpl;
        private ImageView mIvLove;
        private RelativeLayout mRlDiscoverFilm;
        private ImageView mIvBj;


        public MyHolder(View itemView) {
            super(itemView);
            mIvDiscoverWriter = (ImageView) itemView.findViewById(R.id.iv_discover_writer);
            mTvDiscoverNikname = (TextView) itemView.findViewById(R.id.tv_discover_nikname);
            mTvDiscoverDate = (TextView) itemView.findViewById(R.id.tv_discover_date);
            mTvDiscoverComment = (TextView) itemView.findViewById(R.id.tv_discover_comment);
            mIvDiscoverFilm = (ImageView) itemView.findViewById(R.id.iv_discover_film);
            mTvDiscoverFilmName = (TextView) itemView.findViewById(R.id.tv_discover_film_name);
            mTvDiscoverFilmDirector = (TextView) itemView.findViewById(R.id.tv_discover_film_director);
            mTvDiscoverFilmActor = (TextView) itemView.findViewById(R.id.tv_discover_film_actor);
            mTvPraise = (TextView) itemView.findViewById(R.id.tv_praise);
            mIvpl = (ImageView) itemView.findViewById(R.id.iv_pl);
            mIvLove = (ImageView) itemView.findViewById(R.id.iv_discover_love);
            mRlDiscoverFilm = (RelativeLayout) itemView.findViewById(R.id.rl_discover_film);
            mIvBj = (ImageView) itemView.findViewById(R.id.iv_bj);

            mIvpl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                }
            });
            mIvLove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isChosen.put(pos, !isChosen.get(pos));
//                    Toast.makeText(mContext, "" + pos, Toast.LENGTH_SHORT).show();
//                    for (Map.Entry<Integer, Boolean> integerBooleanEntry : isChosen.entrySet()) {
//                        Integer key = integerBooleanEntry.getKey();
//                        Boolean value = integerBooleanEntry.getValue();
//                        int i = key.intValue();
//                        boolean b = value.booleanValue();
//                        Log.e(i + "" + ":::", b + "");
//                    }
//                    Log.e("-------", "------");
                    mListener.getMap(isChosen);

                    notifyDataSetChanged();
//                    if (isChosen.get(pos))
//                        mIvLove.setImageResource(R.drawable.zan_press_new);
//                    else {
//                        mIvLove.setImageResource(R.drawable.zan_new_white);
//                    }


                }
            });

            mRlDiscoverFilm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MovieDetailActivity.class);
                    intent.putExtra("id", mDatas.get(pos).getFilmResourcesId());
                    mContext.startActivity(intent);
                }
            });

            mIvBj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, "想看" + pos, Toast.LENGTH_SHORT).show();
                    AlertDialog dialog = new AlertDialog.Builder(mContext).create();
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
                    window.setWindowAnimations(R.style.mystyle);//添加动画
                    dialog.show();
                    View inflate = LayoutInflater.from(mContext).inflate(R.layout.discover_dialog, null);
                    window.setContentView(inflate);
                    WindowManager.LayoutParams lp = window.getAttributes();
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    window.setAttributes(lp);

                    TextView tvWantSee = (TextView) inflate.findViewById(R.id.dialog_want_see);
                    TextView tvAlreadySee = (TextView) inflate.findViewById(R.id.dialog_already_see);
                    TextView tvWriteComment = (TextView) inflate.findViewById(R.id.dialog_write_comment);
                    RelativeLayout rlDialogCancel = (RelativeLayout) inflate.findViewById(R.id.dialog_cancle);

                    tvWantSee.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "请先登录" + pos, Toast.LENGTH_SHORT).show();
                        }
                    });
                    tvAlreadySee.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "请先登录" + pos, Toast.LENGTH_SHORT).show();
                        }
                    });
                    tvWriteComment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "请先登录" + pos, Toast.LENGTH_SHORT).show();
                        }
                    });
                    rlDialogCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
                }
            });
//     itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    switch (v.getId()) {
//                        case R.id.iv_pl:
//                            Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
//                            break;
//
//                        case R.id.iv_discover_love:
//                            isChosen.put(pos, !isChosen.get(pos));
//                            notifyDataSetChanged();
//                            break;
//                    }
//                }
//            });

        }

    }

    public void setData(List<DiscoverListBean.RecommendsListBean> datas, HashMap<Integer, Boolean> map) {
        mDatas = datas;
//        for (int i = 0; i < mDatas.size(); i++) {
//            isChosen.put(i, false);
//        }
        isChosen = map;
//        for (Map.Entry<Integer, Boolean> integerBooleanEntry : isChosen.entrySet()) {
//            Integer key = integerBooleanEntry.getKey();
//            Boolean value = integerBooleanEntry.getValue();
//            int i = key.intValue();
//            boolean b = value.booleanValue();
//            Log.e(i + "" + ":::", b + "");
//        }
//        Log.e("-------", "------");
//        Log.e("---", map.get(0) + "");

        notifyDataSetChanged();
    }

}

