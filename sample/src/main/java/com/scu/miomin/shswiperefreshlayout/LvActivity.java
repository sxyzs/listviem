package com.scu.miomin.shswiperefreshlayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;
import com.scu.miomin.shswiperefresh.view.SHListView;

import java.util.ArrayList;

public class LvActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<String> mDatas;
    private SHSwipeRefreshLayout swipeRefreshLayout;
    private SHListView shLv;
//    private CheckBox checkBox;
    private SimpleLvAdapter simpleLvAdapter;
    private Button edit,cancel,selectAll,delete,selectNone;

    private int checkNum; // 记录选中的条目数量
    private ArrayList<String> li;
    private ArrayList<String> list;
    private Boolean isShow = false;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
//            initData();
            if (msg.what == 1) {
                //只设置一个
//                simpleLvAdapter = new SimpleLvAdapter(mDatas,LvActivity.this,true);
//                shLv.setAdapter(simpleLvAdapter);
                simpleLvAdapter.setShow(true);
                isShow = true;
                edit.setVisibility(View.GONE);
                cancel.setVisibility(View.VISIBLE);
                selectAll.setVisibility(View.VISIBLE);
                delete.setVisibility(View.VISIBLE);
                selectNone.setVisibility(View.VISIBLE);
                simpleLvAdapter.notifyDataSetChanged();
            } else if (msg.what == 0) {
                simpleLvAdapter = new SimpleLvAdapter(mDatas,LvActivity.this,false);
                shLv.setAdapter(simpleLvAdapter);
                isShow = false;
                cancel.setVisibility(View.GONE);
                edit.setVisibility(View.VISIBLE);
                selectAll.setVisibility(View.GONE);
                delete.setVisibility(View.GONE);
                selectNone.setVisibility(View.GONE);
            }
        };
    };
    private boolean isrefresh = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        View view = LayoutInflater.from(this).inflate(R.layout.simple_item, null);
//        checkBox = (CheckBox) view.findViewById(R.id.cb_item_checkbox);
        edit = (Button)findViewById(R.id.edit);
        cancel = (Button)findViewById(R.id.cancel);
        selectAll = (Button)findViewById(R.id.selectAll);
        delete = (Button)findViewById(R.id.delete);
        selectNone = (Button)findViewById(R.id.selectNone);

        initData();

        initLv();


            initSwipeRefreshLayout();



    }

//    public static void startActivity(Context context) {
//        Intent intent = new Intent(context, LvActivity.class);
//        context.startActivity(intent);
//    }

    private void initLv() {
        shLv = (SHListView) findViewById(R.id.shLv);
        simpleLvAdapter = new SimpleLvAdapter(mDatas,LvActivity.this,false);
        shLv.setAdapter(simpleLvAdapter);
        shLv.setOnItemClickListener(this);
        simpleLvAdapter.notifyDataSetChanged();

    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout = (SHSwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        final View view = inflater.inflate(R.layout.refresh_view, null);
        final TextView textView = (TextView) view.findViewById(R.id.title);
        swipeRefreshLayout.setFooterView(view);
        swipeRefreshLayout.setOnRefreshListener(new SHSwipeRefreshLayout.SHSOnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.finishRefresh();
                        Toast.makeText(LvActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 1600);
            }

            @Override
            public void onLoading() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas.add(10+"");
                        int c =  simpleLvAdapter.getCount();
                        Log.e("TAG",mDatas.size()+""+c);
                        simpleLvAdapter.notifyDataSetChanged();


                        swipeRefreshLayout.finishLoadmore();


                        Toast.makeText(LvActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                    }
                }, 1600);
            }

            /**
             * 监听下拉刷新过程中的状态改变
             * @param percent 当前下拉距离的百分比（0-1）
             * @param state 分三种状态{NOT_OVER_TRIGGER_POINT：还未到触发下拉刷新的距离；OVER_TRIGGER_POINT：已经到触发下拉刷新的距离；START：正在下拉刷新}
             */
            @Override
            public void onRefreshPulStateChange(float percent, int state) {
                switch (state) {
                    case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                        swipeRefreshLayout.setRefreshViewText("下拉刷新");

                        break;
                    case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                        swipeRefreshLayout.setRefreshViewText("松开刷新");
                        break;
                    case SHSwipeRefreshLayout.START:
                        swipeRefreshLayout.setRefreshViewText("正在刷新");
//                        mDatas.add(10+"");
//                        simpleLvAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onLoadmorePullStateChange(float percent, int state) {
                switch (state) {
                    case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                        textView.setText("上拉加载");
                        break;
                    case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:


                        textView.setText("松开加载");

                        break;
                    case SHSwipeRefreshLayout.START:
                        textView.setText("正在加载...");

                        break;
                }
            }
        });

    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            mDatas.add("11111111111111111111111111111111111" +  i);
        }
    }
    private void initData1() {
        list = new ArrayList<String>();
        for (int i = 10; i < 20; i++) {
            list.add("" +  i);
        }
    }

    public void edit(View view) {
        if(!isShow){
            Message message = Message.obtain();
            message.what = 1;
            handler.sendMessage(message);
         ;

        }
//        swipeRefreshLayout.setVisibility(View.GONE);


    }

    public void cancel(View view) {
        if(isShow) {
            Message message = Message.obtain();
            message.what = 0;
            handler.sendMessage(message);


        }
//        swipeRefreshLayout.setVisibility(View.VISIBLE);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
//        SimpleLvAdapter.ViewHolder holder = (SimpleLvAdapter.ViewHolder) view.getTag();
//        // 改变CheckBox的状态
//        //非编辑态不需要
////        holder.checkBox.toggle();
//        // 将CheckBox的选中状况记录下来
//        SimpleLvAdapter.getIsSelected().put(position,holder.checkBox.isChecked());
    }

    public void selectAll(View view) {
        // 遍历list的长度，将MyAdapter中的map值全部设为true
        for (int i = 0; i < mDatas.size(); i++) {
            SimpleLvAdapter.getIsSelected().put(i, true);
        }
        // 数量设为list的长度
        checkNum = mDatas.size();
        // 刷新listview和TextView的显示
        simpleLvAdapter.notifyDataSetChanged();
    }

    public void delete(View view) {
        int count = simpleLvAdapter.getCount();

        for (int i = 0; i < count; i++) {
            int p = i-(count-simpleLvAdapter.getCount());
            if(SimpleLvAdapter.getIsSelected().get(p) != null && SimpleLvAdapter.getIsSelected().get(p)) {
               mDatas.remove(p);
//                simpleLvAdapter.removeData(p);
                simpleLvAdapter.notifyDataSetChanged();
            }
        }
//        checkNum = mDatas.size();
        // 刷新listview和TextView的显示


    }








    public void selectNone(View view) {

    // 遍历list的长度，将MyAdapter中的map值全部设为true
        for (int i = 0; i < mDatas.size(); i++) {
        SimpleLvAdapter.getIsSelected().put(i, false);

    }
    // 数量设为list的长度
    checkNum = mDatas.size();
    // 刷新listview和TextView的显示
        simpleLvAdapter.notifyDataSetChanged();
}
}
