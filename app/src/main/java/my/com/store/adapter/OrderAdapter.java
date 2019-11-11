package my.com.store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import my.com.store.R;
import my.com.store.entity.OrderEntity;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<OrderEntity> list;
    private Context context;
    
    public OrderAdapter(List<OrderEntity> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }
    
    public void setData(List<OrderEntity> list){
        this.list=list;
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.rv_item_order,parent,false);
        return new OrderAdapter.ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvNum.setText("X  "+list.get(position).getNum());
        holder.tvPrice.setText("$  "+list.get(position).getPrice());
        holder.itemView.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOnItemClickListener.onLongClick(position);
                return false;
            }
        });
    
    }
    
    @Override
    public int getItemCount() {
        return list == null ? 0:list.size();
    }
    
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvNum;
        TextView tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.orderName);
            tvNum=itemView.findViewById(R.id.orderNum);
            tvPrice=itemView.findViewById(R.id.orderPrice);
        }
    }
    
    
    public interface OnItemClickListener{
        void onLongClick(int position);
    }
    
    private OnItemClickListener mOnItemClickListener;
  
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
    
    
    
    
}
