package com.scu.miomin.shswiperefreshlayout;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleLvAdapter extends BaseAdapter {

    private ArrayList<String> datas;
    private Context context;
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;
    // 用来导入布局
    private LayoutInflater inflater = null;
    private Boolean isShow=false;

    public ArrayList<String> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<String> datas) {
        this.datas = datas;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        SimpleLvAdapter.isSelected = isSelected;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }

    // 构造器
    public SimpleLvAdapter(ArrayList<String> datas, Context context,Boolean isShow) {
        super();
        this.datas = datas;
        this.context = context;
        Log.e("TAG",datas+"");


        isSelected = new HashMap<Integer, Boolean>();
        this.isShow=isShow;
        for (int i = 0; i < datas.size(); i++) {
            getIsSelected().put(i, false);
        }
        notifyDataSetChanged();
    }
    public void removeData(int position){
        datas.remove(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // 保留一个item的控件
        ViewHolder holder = null;

        if (convertView == null) {
            // 拿到ListViewItem的布局（一行，需要单独定义一个），转换为View类型的对象
            convertView = View.inflate(context, R.layout.simple_item, null);
            holder = new ViewHolder();
            holder.id_num = (TextView) convertView.findViewById(R.id.id_num);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.cb_item_checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSelected.put(position, isChecked);
            }
        });
        if(isShow){
            holder.checkBox.setVisibility(View.VISIBLE);
        }else {
            holder.checkBox.setVisibility(View.INVISIBLE);
        }

            holder.id_num.setText("1111111111111111111" + datas.get(position));

            holder.checkBox.setChecked(getIsSelected().get(position));
        return convertView;
    }

    class ViewHolder {
        TextView id_num;
        CheckBox checkBox;
    }
}