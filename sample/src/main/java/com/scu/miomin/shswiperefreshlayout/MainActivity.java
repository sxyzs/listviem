package com.scu.miomin.shswiperefreshlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        initData();
//        initRecyclerView();
    }

//    private void initRecyclerView() {
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        SimpleRvAdapter adapter = new SimpleRvAdapter(this, mDatas);
//        adapter.setOnItemClickLitener(new SimpleRvAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                switch (position) {
////                    case 0:
////                        RvActivity.startActivity(MainActivity.this);
////                        break;
////                    case 1:
////                        SvActivity.startActivity(MainActivity.this);
////                        break;
//                    case 2:
//                        LvActivity.startActivity(MainActivity.this);
//                        break;
//                }
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        });
//        recyclerView.setAdapter(adapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerRVDecoration(this,
//                DividerRVDecoration.VERTICAL_LIST));
//    }
//
//    protected void initData() {
//        mDatas = new ArrayList();
//        mDatas.add("RecyclerView");
//        mDatas.add("ScrollView");
//        mDatas.add("ListView");
//    }

    public void tiaozhuan(View view) {
        startActivity(new Intent(MainActivity.this,LvActivity.class));
    }


}
