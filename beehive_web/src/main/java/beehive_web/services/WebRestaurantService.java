package beehive_web.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beehive_web.models.entity.Distribution;
import beehive_web.models.entity.Restaurant;
import beehive_web.models.repository.RestaurantAndDistributionRepository;
import beehive_web.models.repository.RestaurantRepository;

@Service
@Transactional
public class WebRestaurantService {
    @Autowired
    private RestaurantRepository rR;
    @Autowired
    private RestaurantAndDistributionRepository rdR;
    
    public Restaurant findOne(long restaurantId) {
        return rR.findOne(restaurantId);
    }
    
    public List<Map<String,Object>> findAll() {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Iterator<Restaurant> iter = rR.findAll().iterator();
        while(iter.hasNext()) {
            Restaurant r = iter.next();
            Map<String,Object> m = new HashMap<String,Object>();
            m.put("id", r.getId());
            m.put("name", r.getName());
            m.put("address", r.getAddress());
            m.put("tel", r.getTel());
            String openTime = r.getOpenTime()/60 + ":" + r.getOpenTime()%60;
            String closeTime = r.getCloseTime()/60 + ":" + r.getCloseTime()%60;
            m.put("openTime", openTime);
            m.put("closeTime", closeTime);
            m.put("enable", r.isEnable());
            list.add(m);
        }
        return list;
    }
    
    public boolean delete(long restaurantId) {
        rdR.deleteByRestaurantId(restaurantId);
        if(rR.exists(restaurantId)) {
            rR.delete(restaurantId);
            return true;
        }
        return false;
    }
    
    public boolean save(Restaurant r) {
        Restaurant rr = rR.save(r);
        if(!rr.getName().equals(r.getName())) {
            return false;
        }
        if(!rr.getAddress().equals(r.getAddress())) {
            return false;
        }
        if(!rr.getTel().equals(r.getTel())) {
            return false;
        }
        if(rr.getOpenTime() != r.getOpenTime()) {
            return false;
        }
        if(rr.getCloseTime() != r.getCloseTime()) {
            return false;
        }
        return true;
    }
    
    public boolean enable(long restaurantId) {
        Restaurant r = rR.findOne(restaurantId);
        if(r == null) {
            return false;
        }
        r.setEnable(true);
        rR.save(r);
        return true;
    }
    
    public boolean disable(long restaurantId) {
    	Restaurant r = rR.findOne(restaurantId);
        if(r == null) {
            return false;
        }
        r.setEnable(false);
        rR.save(r);
        return true;
    }
}
