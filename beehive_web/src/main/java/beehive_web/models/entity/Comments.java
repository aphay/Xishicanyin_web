package beehive_web.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comments extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long userId;
    private long dishesId;
    private int score;
    private String content;
    private Date createTime;
    
    protected Comments() {}
    
    public Comments(long userId, long dishesId, int score, String content) {
        this.userId = userId;
        this.dishesId = dishesId;
        this.score = score;
        this.content = content;
        this.createTime = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDishesId() {
        return dishesId;
    }

    public void setDishesId(long dishesId) {
        this.dishesId = dishesId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    
}
