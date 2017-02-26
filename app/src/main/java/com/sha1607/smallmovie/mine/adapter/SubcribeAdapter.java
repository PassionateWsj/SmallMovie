package com.sha1607.smallmovie.mine.adapter;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/30 下午 -1:07
 * name:
 * desc:
 * step:
 **********************************
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.db.Order;
import com.sha1607.smallmovie.utils.MyImageLoader;

import java.util.ArrayList;
import java.util.List;

public class SubcribeAdapter extends RecyclerView.Adapter<SubcribeAdapter.SubcribeHolder> {
    private List<Order> mOrders;
    private Context mContext;
    public SubcribeAdapter(Context context){
        this.mContext=context;
    }
    @Override
    public SubcribeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_subcribe_list,parent,false);

        return new SubcribeHolder(view);
    }

    @Override
    public void onBindViewHolder(SubcribeHolder holder, int position) {
        Order order = mOrders.get(position);
        holder.mTextView_name.setText(order.getTitle());
       String desc=  order.getArticlesNum()+"篇/"+ order.getSubscribeNum()+"人订阅";
        holder.mTextView_desc.setText(desc);
        MyImageLoader.with(mContext,order.getImgUrl(),holder.mImageView,MyImageLoader.LoaderEnum.GLIDE);

    }

    @Override
    public int getItemCount() {
        return mOrders==null?0:mOrders.size();
    }
    public  void setData(List<Order> datas){
        if (mOrders==null){
            mOrders=new ArrayList<>();
        }
        mOrders.addAll(datas);
        notifyDataSetChanged();

    }



    class  SubcribeHolder extends RecyclerView.ViewHolder{
        TextView mTextView_name;
        TextView mTextView_desc;
        ImageView mImageView;
        public SubcribeHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_subcribe_show);
            mTextView_name = (TextView) itemView.findViewById(R.id.tv_subcreibe_name);
            mTextView_desc = (TextView) itemView.findViewById(R.id.tv_subcreibe_desc);

        }
    }
}
