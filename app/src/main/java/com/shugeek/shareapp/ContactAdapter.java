package com.shugeek.shareapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2016/6/19.
 */
public class ContactAdapter extends BaseAdapter {
    private Context mContext;

    public ContactAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.share_contact_item,null);
            holder.imageView = (ImageView)convertView.findViewById(R.id.list_item_head_image);
            holder.nickNameTextView = (TextView)convertView.findViewById(R.id.list_item_nick_name);
            holder.signTextView = (TextView)convertView.findViewById(R.id.list_item_user_sign);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //传入从服务器请求到数据，动态更改listview
//        holder.imageView.setImageResource();


        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView nickNameTextView;
        TextView signTextView;
    }

}
