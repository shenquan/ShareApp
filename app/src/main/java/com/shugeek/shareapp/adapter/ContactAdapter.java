package com.shugeek.shareapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shugeek.shareapp.R;

/**
 * Created by Administrator on 2016/6/19.
 */
public class ContactAdapter extends BaseAdapter {
    private Context mContext;
    private int[] headImage = new int[]{
            R.mipmap.share_person_1,
            R.mipmap.share_person_2,
            R.mipmap.share_person_3,
            R.mipmap.share_person_4,
            R.mipmap.share_person_5,
            R.mipmap.share_person_6,
            R.mipmap.share_person_7,
            R.mipmap.share_person_8,
            R.mipmap.share_person_9,
            R.mipmap.share_person_10,
    };
    private String[] nickName = new String[]{
            "易水寒",
            "沪江极客show",
            "如此简单",
            "Present",
            "启动追一个梦",
            "呦呵呦呵",
            "随风~",
            "自骑白马",
            "吾往矣",
            "暗夜精灵",
    };
    private String[] signature = new String[]{
            "自然不可改良、生活可以选择选择绿色生活、健康适度消费。",
            "我从来不记在辞典上已经印有的东西。我的记忆力是运用来记忆书本上还没有的东西。",
            "事业和工作的乐趣，不在于取得的成功和业绩，更多的乐趣在于跌跌闯闯的过程中。",
            "自然不可改良、生活可以选择选择绿色生活、健康适度消费。",
            "没钱的时候，老婆兼秘书；有钱的时候，秘书兼老婆。",
            "一个人不能被同一块石头绊倒两次。",
            "音乐是心灵的迸发。它不象化学那样能进行实验分析。对伟大的音乐来说只有一种真正的特性，那就是感情。~",
            "诗人的想象力支配现实的程度，说到底，是衡量他的价值和尊严的精确尺度。",
            "信仰就倘没有充分的证据，那不过是迷信自己而已",
            "最能保人心神之健康的预防药就是朋友的忠言规谏",
    };

    public ContactAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.share_contact_item, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.list_item_head_image);
            holder.nickNameTextView = (TextView) convertView.findViewById(R.id.list_item_nick_name);
            holder.signTextView = (TextView) convertView.findViewById(R.id.list_item_user_sign);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //本应该从服务器请求到数据，动态更改listview
        holder.imageView.setImageResource(headImage[i]);
        holder.nickNameTextView.setText(nickName[i]);
        holder.signTextView.setText(signature[i]);
        if (i == 1 || i == 3 || i == 8) {
            holder.nickNameTextView.setTextColor(0xffdd0000);
            holder.signTextView.setTextColor(0xffdd0000);
        } else {//此处必须加else，因为会重用
            holder.nickNameTextView.setTextColor(0xff222222);
            holder.signTextView.setTextColor(0xff666666);
        }

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView nickNameTextView;
        TextView signTextView;
    }

}
