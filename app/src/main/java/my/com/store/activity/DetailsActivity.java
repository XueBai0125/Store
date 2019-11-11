package my.com.store.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import androidx.annotation.Nullable;
import my.com.store.R;
import my.com.store.db.DbHelper;
import my.com.store.entity.OrderEntity;

public class DetailsActivity extends Activity {
    private ImageView iv;
    private TextView Name;
    private TextView Des;
    private TextView Price;
    private ImageView PlusIcon;
    private TextView Num;
    private ImageView MinusIcon;
    private Button Add;
    
    private String name;
    private int ivId;
    private String des;
    private String price;
    
    private void assignViews() {
        iv = (ImageView) findViewById(R.id.iv);
        Name = (TextView) findViewById(R.id.orderName);
        Des = (TextView) findViewById(R.id.orderDes);
        Price = (TextView) findViewById(R.id.orderPrice);
        PlusIcon = (ImageView) findViewById(R.id.plusIcon);
        Num = (TextView) findViewById(R.id.orderNum);
        MinusIcon = (ImageView) findViewById(R.id.minusIcon);
        Add = (Button) findViewById(R.id.orderAdd);
    }
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
    
        name=getIntent().getStringExtra("name");
        ivId=getIntent().getIntExtra("ivId",0);
        des=getIntent().getStringExtra("des");
        price=getIntent().getStringExtra("price");
        
        initView();
        initEvent();
        
    }
    
    private void initView() {
        assignViews();
        iv.setBackgroundResource(ivId);
        Name.setText(name);
        Des.setText(des);
        Price.setText(price);
    }
    
    private void initEvent() {
        MinusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num=Num.getText().toString().trim();
                if (num.equals("1")){
                
                }else {
                    int a=Integer.parseInt(num);
                    a--;
                    Num.setText(a+"");
                }
            }
        });
    
    
        PlusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num=Num.getText().toString().trim();
                int a=Integer.parseInt(num);
                a++;
                Num.setText(a+"");
            }
        });
    
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num=Num.getText().toString().trim();
                if (TextUtils.isEmpty(num) || num.equals("0")){
                    Toast.makeText(DetailsActivity.this,"Add fail",Toast.LENGTH_SHORT).show();
                }else {
                    DbManager dbManager= DbHelper.getInstance().getDbManager(DetailsActivity.this,null);
                    OrderEntity orderEntity=new OrderEntity();
                    orderEntity.setDes(des);
                    orderEntity.setName(name);
                    orderEntity.setNum(num);
                    orderEntity.setPrice(price);
                    orderEntity.setIvId(ivId);
                    try {
                        dbManager.save(orderEntity);
                        Toast.makeText(DetailsActivity.this,"Add Success",Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        
    }
    
   
}
