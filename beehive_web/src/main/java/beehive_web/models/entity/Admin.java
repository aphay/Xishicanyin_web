package beehive_web.models.entity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import beehive_web.base.Encrypt;
import beehive_web.base.TimeUtil;

@Entity
public class Admin extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String loginName;
    private String personName;
    private String password;
    private String token;
    private String lastLoginTime;
    private long restaurantId;
    private String deviceToken;
    private int level;
    
    protected Admin() {}
    
    public Admin(String loginName, String personName, String password, long restaurantId, int level) {
        this.loginName = loginName;
        this.personName = personName;
        this.password = Encrypt.MD5(password);
        this.token = "";
        this.lastLoginTime = TimeUtil.date2String(new Date(1000));
        this.restaurantId = restaurantId;
        this.setDeviceToken("");
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLastLoginTime() {
        return lastLoginTime.split("\\.")[0];
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
