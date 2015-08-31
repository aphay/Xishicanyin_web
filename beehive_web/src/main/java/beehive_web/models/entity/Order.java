package beehive_web.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import beehive_web.base.TakeNo;
import beehive_web.base.TimeUtil;

@Entity
@Table(name="user_order")
public class Order extends BaseEntity{
    @Id
    private long orderId;
    private String createTime;
    private String updateTime;
    private String dishes;
    private String dishesType;
    private float totalPrice;
    private long voucherId;
    private String remarks;
    private long restaurantId;
    private long distributionId;
    private long operatorId;
    private int status;
    private int takeNo;
    private long userId;
    private String tel;
    private int orderType;
    private boolean hasPaid;
    private String cancelRemark;
    
    protected Order() {}
    
    public Order(String dishes, String dishesType, float totalPrice,
        long voucherId,String remarks,long restaurantId, long distributionId,int status,
        long userId, String tel, int orderType, boolean hasPaid) {
        
        this.orderId = TimeUtil.nowLong();
        this.createTime = TimeUtil.now();
        this.updateTime = TimeUtil.now();
        this.dishes = dishes;
        this.dishesType = dishesType;
        this.totalPrice = totalPrice;
        this.voucherId = voucherId;
        this.remarks = remarks;
        this.restaurantId = restaurantId;
        this.distributionId = distributionId;
        this.operatorId = 0;
        this.status = status;
        this.takeNo = TakeNo.generate();
        this.userId = userId;
        this.tel = tel;
        this.orderType = orderType;
        this.hasPaid = hasPaid;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getCreateTime() {
        return createTime.split("\\.")[0];
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime.split("\\.")[0];
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public String getDishesType() {
        return dishesType;
    }

    public void setDishesType(String dishesType) {
        this.dishesType = dishesType;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(long voucherId) {
        this.voucherId = voucherId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public long getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(long distributionId) {
        this.distributionId = distributionId;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTakeNo() {
        return takeNo;
    }

    public void setTakeNo(int takeNo) {
        this.takeNo = takeNo;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}
    
    
}
