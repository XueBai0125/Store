package my.com.store.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import my.com.store.R;
import my.com.store.adapter.OrderAdapter;
import my.com.store.db.DbHelper;
import my.com.store.entity.OrderEntity;


public class OrderActivity extends Activity {
    private RecyclerView rv;
    private TextView tvPrice;
    private List<OrderEntity> list;
    private OrderAdapter mAdapter;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        
        initView();
        initEvent();
    }
    
    private void initEvent() {
        mAdapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onLongClick(final int position) {
                AlertDialog.Builder builder=new AlertDialog.Builder(OrderActivity.this);
                builder.setMessage("Do you want to delete this order?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DbManager dbManager= DbHelper.getInstance().getDbManager(OrderActivity.this,null);
                        try {
                            OrderEntity entity=dbManager.findById(OrderEntity.class,list.get(position).getId());
                            dbManager.delete(entity);
                            initData();
                            Toast.makeText(OrderActivity.this,"Delete Success！",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        
                    }
                }).create().show();
                
            }
        });
        
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
    
    private void initData() {
        if (list!=null){
            list.clear();
        }
        DbManager dbManager= DbHelper.getInstance().getDbManager(this,null);
        try {
            list=dbManager.selector(OrderEntity.class).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (list!=null && list.size()>0) {
            mAdapter.setData(list);
        }
        
        double total=0;
        for (OrderEntity entity : list){
            double price= Double.parseDouble(entity.getPrice());
            int num=Integer.parseInt(entity.getNum());
            total=total+price*num;
        }
        tvPrice.setText(total+"");
    }
    
    private void initView() {
        rv=findViewById(R.id.rv);
        tvPrice=findViewById(R.id.order_total_price);
        list=new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=new OrderAdapter(list,this);
        rv.setAdapter(mAdapter);
    }
}
