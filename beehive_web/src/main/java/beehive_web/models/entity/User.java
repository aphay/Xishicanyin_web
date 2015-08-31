package beehive_web.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String openId;
    private boolean isBlack;
    private String tel;
    private long distributionId;
    private float money;
    private String validateCode;
    private long expireTime;
    
    protected User() {}
    
    public User(String openId, boolean isBlack, String tel, long distributionId) {
        this.openId = openId;
        this.isBlack = isBlack;
        this.tel = tel;
        this.distributionId = distributionId;
        this.money = 0;
        this.validateCode = "0";
        this.expireTime = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public void setBlack(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public long getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(long distributionId) {
        this.distributionId = distributionId;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
    
    
}
