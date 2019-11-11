package my.com.store.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "GoodsEntity")
public class GoodsEntity {
    @Column(name = "id",isId = true)
    private int id;
    @Column(name = "ivId")
    private int ivId;
    @Column(name = "name")
    private String name;
    @Column(name = "des")
    private String des;
    @Column(name = "price")
    private String price;
    
    public int getIvId() {
        return ivId;
    }
    
    public void setIvId(int ivId) {
        this.ivId = ivId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDes() {
        return des;
    }
    
    public void setDes(String des) {
        this.des = des;
    }
    
    public String getPrice() {
        return price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
}
