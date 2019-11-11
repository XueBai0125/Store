package my.com.store.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import my.com.store.R;
import my.com.store.adapter.GoodsAdapter;
import my.com.store.db.DbHelper;
import my.com.store.entity.GoodsEntity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private TextView OrderItem;
    private List<GoodsEntity> list;
    private GoodsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        initEvent();
    }
    
    private void initEvent() {
        mAdapter.setOnItemClickListener(new GoodsAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("ivId",list.get(position).getIvId());
                intent.putExtra("des",list.get(position).getDes());
                intent.putExtra("price",list.get(position).getPrice());
                startActivity(intent);
            }
        });
    
        OrderItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,OrderActivity.class);
                startActivity(intent);
            }
        });
    
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
    
    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
        OrderItem = (TextView) findViewById(R.id.orderItem);
        list=new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=new GoodsAdapter(list,this);
        rv.setAdapter(mAdapter);
    }
    
    private void initData() {
        if (list!=null){
            list.clear();
        }
        DbManager dbManager= DbHelper.getInstance().getDbManager(this,null);
        try {
            list=dbManager.selector(GoodsEntity.class).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
    
        if (list!=null && list.size()>0){
        
            Log.e("HH",list.toString());
            mAdapter.setData(list);
        }else {
            GoodsEntity entity1=new GoodsEntity();
            entity1.setIvId(R.drawable.salad);
            entity1.setName("Salad");
            entity1.setDes("mix lettuce,onion,mushroom,carrot,avocado with vegetable sauce");
            entity1.setPrice("9.50");

            GoodsEntity entity2=new GoodsEntity();
            entity2.setIvId(R.drawable.avacadoroll);
            entity2.setName("Avacado Roll");
            entity2.setDes("6 pieces");
            entity2.setPrice("4.70");

    
            GoodsEntity entity3=new GoodsEntity();
            entity3.setIvId(R.drawable.carliforniaroll);
            entity3.setName("Carlifornia Roll");
            entity3.setDes("6 pieces");
            entity3.setPrice("4.50");
    
            GoodsEntity entity4=new GoodsEntity();
            entity4.setIvId(R.drawable.chichkenkatsuroll);
            entity4.setName("Chichken Katsu Roll");
            entity4.setDes("6 pieces");
            entity4.setPrice("12.50");

            GoodsEntity entity5=new GoodsEntity();
            entity5.setIvId(R.drawable.tunaroll);
            entity5.setName("Tuna Roll");
            entity5.setDes("3 Pieces");
            entity5.setPrice("4.50");

            GoodsEntity entity6=new GoodsEntity();
            entity6.setIvId(R.drawable.tempuraroll);
            entity6.setName("Tempura Roll");
            entity6.setDes("Nine pieces");
            entity6.setPrice("18.50");

            GoodsEntity entity7=new GoodsEntity();
            entity7.setIvId(R.drawable.sunsetroll);
            entity7.setName("Sunset Roll");
            entity7.setDes("6 pieces");
            entity7.setPrice("12.50");

            GoodsEntity entity8=new GoodsEntity();
            entity8.setIvId(R.drawable.beefudon);
            entity8.setName("Beef Udon");
            entity8.setDes("Udon Noodle in Soy Sauce Based Soup with Wagyu Beef");
            entity8.setPrice("21.80");

            GoodsEntity entity9=new GoodsEntity();
            entity9.setIvId(R.drawable.seafoodudon);
            entity9.setName("Seafood Udon");
            entity9.setDes("Udon Noodle in Soy Sauce Based Soup with seafood");
            entity9.setPrice("21.80");
    
            GoodsEntity entity10=new GoodsEntity();
            entity10.setIvId(R.drawable.freshsalmon);
            entity10.setName("Salmon Sashimi");
            entity10.setDes("Nine pieces");
            entity10.setPrice("19.80");

            GoodsEntity entity11=new GoodsEntity();
            entity11.setIvId(R.drawable.spicysalmon);
            entity11.setName("Spicy Salmon Sashimi");
            entity11.setDes("Nine pieces");
            entity11.setPrice("20.80");

    
            GoodsEntity entity12=new GoodsEntity();
            entity12.setIvId(R.drawable.teriyakibeef);
            entity12.setName("Teriyaki Beef");
            entity12.setDes("Grilled chicken with teriyaki sauce and seasonal sauce");
            entity12.setPrice("18.80");
    
            GoodsEntity entity13=new GoodsEntity();
            entity13.setIvId(R.drawable.teriyakisalmon);
            entity13.setName("Teriyaki Salmon");
            entity13.setDes("Grilled salmon with teriyaki sauce and seasonal sauce");
            entity13.setPrice("18.80");

            GoodsEntity entity14=new GoodsEntity();
            entity14.setIvId(R.drawable.juice);
            entity14.setName("Juice");
            entity14.setDes("Can");
            entity14.setPrice("3.50");

            GoodsEntity entity15=new GoodsEntity();
            entity15.setIvId(R.drawable.softdrink);
            entity15.setName("Soft Drink");
            entity15.setDes("Can");
            entity15.setPrice("3.50");
    
            try {
                dbManager.save(entity1);
                dbManager.save(entity2);
                dbManager.save(entity3);
                dbManager.save(entity4);
                dbManager.save(entity5);
                dbManager.save(entity6);
                dbManager.save(entity7);
                dbManager.save(entity8);
                dbManager.save(entity9);
                dbManager.save(entity10);
                dbManager.save(entity11);
                dbManager.save(entity12);
                dbManager.save(entity13);
                dbManager.save(entity14);
                dbManager.save(entity15);
            } catch (DbException e) {
                e.printStackTrace();
            }
    
            initData();
        }
    }
}
