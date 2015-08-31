package beehive_web.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import beehive_web.base.Constant;

@Entity
public class Dishes extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private long typeId;
    private String description;
    private float price;
    private String picture;
    private long sales;
    private int recommand;
    private float discount;
    private boolean onShow;
    
    protected Dishes() {}
    
    public Dishes(String name, long typeId, String description, float price, long sales, int recommand, float discount) {
        this.name = name;
        this.typeId = typeId;
        this.description = description;
        this.price = price;
        this.picture = "/";
        this.sales = sales;
        this.recommand = recommand;
        this.discount = discount;
        this.onShow = Constant.ON_SHOW;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getSales() {
        return sales;
    }

    public void setSales(long sales) {
        this.sales = sales;
    }

    public int getRecommand() {
        return recommand;
    }

    public void setRecommand(int recommand) {
        this.recommand = recommand;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public boolean isOnShow() {
        return onShow;
    }

    public void setOnShow(boolean onShow) {
        this.onShow = onShow;
    }
    
    
}