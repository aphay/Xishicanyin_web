package beehive_web.base;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private Map<String, Object> cache;
    private Map<Long, Integer> restaurantOrder;
    private static Cache c = null;
    
    private Cache() {
        cache = new HashMap<String,Object>();
        restaurantOrder = new HashMap<Long,Integer>();
    }
    
    public static Cache getInstance() {
        if(c == null) {
            c = new Cache();
        }
        return c;
    }
    
    //基本操作
    public Object get(String key) {
        return cache.get(key);
    }
    public void set(String key, Object value) {
        cache.put(key, value);
    }
    public boolean exist(String key) {
        return cache.containsKey(key);
    }
    
    //记录店铺订单数的操作
    public void add(long restaurantId) {
        if(!restaurantOrder.containsKey(restaurantId)) {
            restaurantOrder.put(restaurantId, 0);
        }
        restaurantOrder.put(restaurantId, restaurantOrder.get(restaurantId) + 1);
    }
    public void minus(long restaurantId) {
        if(!restaurantOrder.containsKey(restaurantId) || restaurantOrder.get(restaurantId) <= 0) {
            restaurantOrder.put(restaurantId, 0);
            return;
        }
        restaurantOrder.put(restaurantId, restaurantOrder.get(restaurantId) - 1);
    }
    public int getOrder(long restaurantId) {
        if(!restaurantOrder.containsKey(restaurantId)) {
            restaurantOrder.put(restaurantId, 0);
        }
        return restaurantOrder.get(restaurantId);
    }
    public void clearOrder() {
        restaurantOrder.clear();
    }
}
