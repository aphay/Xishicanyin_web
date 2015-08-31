package beehive_web.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beehive_web.base.Json;
import beehive_web.base.TimeUtil;
import beehive_web.models.entity.Admin;
import beehive_web.models.entity.Distribution;
import beehive_web.models.entity.Restaurant;
import beehive_web.base.Constant;
import beehive_web.models.entity.Dishes;
import beehive_web.models.entity.Order;
import beehive_web.models.entity.User;
import beehive_web.models.repository.AdminRepository;
import beehive_web.models.repository.DishesRepository;
import beehive_web.models.repository.DishesTypeRepository;
import beehive_web.models.repository.DistributionRepository;
import beehive_web.models.repository.OrderRepository;
import beehive_web.models.repository.RestaurantRepository;
import beehive_web.models.repository.UserRepository;

@Service
@Transactional
public class WebOrderService {
    @Autowired
    private OrderRepository oR;
    @Autowired
    private RestaurantRepository rR;
    @Autowired
    private UserRepository uR;
    @Autowired
    private DishesRepository dR;
    @Autowired
    private DishesTypeRepository dtR;
    @Autowired
    private DistributionRepository distributionR;
    @Autowired
    private AdminRepository aR;
    
    public Order findOne(long orderId) {
    	return oR.findOne(orderId);
    }
    
    public Map<String,Object> findMapOne(long orderId) {
    	Order o = oR.findOne(orderId);
    	if(o == null) {
    		return null;
    	}
    	Map<String, Map<Long,String>> map = getMap();
    	Map<String, Object> m = new HashMap<String,Object>();
    	m.put("orderId", o.getOrderId());
    	//获取全量菜品表
        Map<Long, String> dishesMap = new HashMap<Long, String>();
        Iterator<Dishes> iter = dR.findAll().iterator();
        while(iter.hasNext()) {
            Dishes item = iter.next();
            dishesMap.put(item.getId(), item.getName());
        }
        //解析菜品
        Map<String, Object> d = new HashMap<String, Object>();
        Map<String, Integer> dishesJson = Json.decodeCar(o.getDishes());
        for(String key:dishesJson.keySet()) {
            long id = Long.parseLong(key);
            d.put(dishesMap.get(id), dishesJson.get(key));
        }
        m.put("dishes", Json.encode(d));
    	
        m.put("totalPrice", o.getTotalPrice());
    	m.put("remark", o.getRemarks());
    	m.put("restaurant", map.get("restaurant").get(o.getRestaurantId()));
    	m.put("distribution", map.get("distribution").get(o.getDistributionId()));
    	m.put("takeNo", o.getTakeNo());
    	m.put("status", Constant.ORDER_STATUS[o.getStatus()]);
    	m.put("userId", o.getUserId());
    	m.put("tel", o.getTel());
    	m.put("orderType", Constant.PAY_TYPE[o.getOrderType()]);
    	m.put("hasPaid", o.isHasPaid()?"是":"否");
    	m.put("cancelRemark", o.getCancelRemark());
    	return m;
    }
    
    public Map<String,Object> findMapOne2(long orderId) {
    	Order o = oR.findOne(orderId);
    	if(o == null) {
    		return null;
    	}
    	Map<String, Object> m = new HashMap<String,Object>();
    	m.put("order", o);
    	//获取全量菜品表
        Map<Long, String> dishesMap = new HashMap<Long, String>();
        Iterator<Dishes> iter = dR.findAll().iterator();
        while(iter.hasNext()) {
            Dishes item = iter.next();
            dishesMap.put(item.getId(), item.getName());
        }
        //解析菜品
        List<Map<String, Object>> dList = new ArrayList<Map<String, Object>>();
        Map<String, Integer> dishesJson = Json.decodeCar(o.getDishes());
        for(String key:dishesJson.keySet()) {
            long id = Long.parseLong(key);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id", id);
            map.put("name", dishesMap.get(id));
            map.put("count", dishesJson.get(key));
            dList.add(map);
        }
        m.put("dishes", dList);
    	return m;
    }
    
    public List<Map<String,Object>> findAll(String time) {
    	String tomorrow = TimeUtil.tomorrow(time);
        return this.orderList(oR.findByCreateTimeBetween(time,tomorrow));
    }
    public List<Map<String,Object>> findAll(String time, String tel) {
    	String tomorrow = TimeUtil.tomorrow(time);
        return this.orderList(oR.findByTelAndCreateTimeBetween(tel, time,tomorrow));
    }
    
    public User findUserById(long userId) {
        return uR.findOne(userId);
    }
    
    public boolean delete(long orderId) {
        if(oR.exists(orderId)) {
            oR.delete(orderId);
            return true;
        }
        return false;
    }
    
    public boolean save(Order o) {
        Order ro = oR.save(o);
        if(!ro.getDishes().equals(o.getDishes())) {
            return false;
        }
        if(ro.getTotalPrice() != o.getTotalPrice()) {
            return false;
        }
        if(ro.getVoucherId() != o.getVoucherId()) {
            return false;
        }
        if(!ro.getRemarks().equals(o.getRemarks())) {
            return false;
        }
        if(ro.getRestaurantId() != o.getRestaurantId()) {
            return false;
        }
        if(ro.getDistributionId() != o.getDistributionId()) {
            return false;
        }
        if(ro.getStatus() != o.getStatus()) {
            return false;
        }
        if(ro.getUserId() != o.getUserId()) {
            return false;
        }
        if(ro.getOrderType() != o.getOrderType()) {
            return false;
        }
        if(ro.isHasPaid() != o.isHasPaid()) {
            return false;
        }
        return true;
    }
    
    public Map<String, Long> statistics(String startTime,String endTime) {
        List<Order> list = oR.findByStatusAndUpdateTimeBetween(Constant.COMPLETE, startTime, endTime);
        //将json中的菜品统计出来
        Map<String, Long> s = new HashMap<String, Long>();
        Set<Long> ids = new HashSet<Long>();
        for(Order o:list) {
            JSONObject dishes = JSONObject.fromObject(o.getDishes());
            Iterator<String> iter = dishes.keys();
            while(iter.hasNext()) {
                String id = iter.next();
                ids.add(Long.parseLong(id));    //将菜品id转成long并存入集合，以便接下来处理
                
                if(s.containsKey(id)) {
                    s.put(id, s.get(id) + dishes.getLong(id));
                }
                else {
                    s.put(id, dishes.getLong(id));
                }
            }
        }
        //将菜品销量更新到对应菜品表
        Iterator<Dishes> iter = dR.findAll().iterator();
        List<Dishes> dList = new ArrayList<Dishes>();
        while(iter.hasNext()) {
        	Dishes d = iter.next();
        	d.setSales(0);
        	if(s.containsKey(""+d.getId())) {
                d.setSales(s.get(""+d.getId()));        		
        	}
        	dList.add(d);
        }
        dR.save(dList);
        return s;
    }
    
    public Map<Long,Long> getDishesTypeMap() {
        Iterator<Dishes> iter = dR.findAll().iterator();
        Map<Long, Long> r = new HashMap<Long, Long>();
        while(iter.hasNext()) {
            Dishes d = iter.next();
            r.put(d.getId(),d.getTypeId());
        }
        return r;
    }
    
    //将订单相关状态转成对应名称
    private List<Map<String, Object>> orderList(List<Order> oList) {
        List<Map<String,Object>> r = new ArrayList<Map<String, Object>>();
        Map<String, Map<Long,String>> map = getMap();
        for(Order o:oList) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("orderId", o.getOrderId());
            m.put("totalPrice", o.getTotalPrice());
            //使用缓存查找各Id对应的名称
            m.put("operator", map.get("admin").get(o.getOperatorId()));
            m.put("orderType", Constant.PAY_TYPE[o.getOrderType()]);
            m.put("createTime", o.getCreateTime());
            m.put("updateTime", o.getUpdateTime());
            m.put("status", Constant.ORDER_STATUS[o.getStatus()]);
            m.put("hasPaid", o.isHasPaid()?"是":"否");
            m.put("cancelRemark", o.getCancelRemark());
            r.add(m);
        }
        return r;
    }
    
    private Map<String, Map<Long,String>> getMap() {
        Map<String, Map<Long,String>> map = new HashMap<String,Map<Long,String>>();
        map.put("restaurant", new HashMap<Long,String>());
        map.put("distribution", new HashMap<Long,String>());
        map.put("admin", new HashMap<Long,String>());
        
        Iterator<Restaurant> ri = rR.findAll().iterator();
        while(ri.hasNext()) {
            Restaurant r = ri.next();
            map.get("restaurant").put(r.getId(), r.getName());
        }
        Iterator<Distribution> di = distributionR.findAll().iterator();
        while(di.hasNext()) {
            Distribution d = di.next();
            map.get("distribution").put(d.getId(), d.getName());
        }
        Iterator<Admin> ai = aR.findAll().iterator();
        while(ai.hasNext()) {
            Admin a = ai.next();
            map.get("admin").put(a.getId(), a.getPersonName());
        }
        map.get("admin").put(0L, "用户");
        map.get("admin").put(99999L, "系统");
        
        return map;
    }
}
