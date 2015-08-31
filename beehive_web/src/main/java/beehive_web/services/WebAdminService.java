package beehive_web.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beehive_web.base.Constant;
import beehive_web.models.entity.Admin;
import beehive_web.models.entity.Restaurant;
import beehive_web.models.repository.AdminRepository;
import beehive_web.models.repository.RestaurantRepository;

@Service
@Transactional
public class WebAdminService {
    @Autowired
    private AdminRepository aR;
    @Autowired
    private RestaurantRepository rR;
    
    public Admin findOne(long adminId) {
        return aR.findOne(adminId);
    }
    
    public Admin findByLoginName(String loginName) {
        return aR.findByLoginName(loginName);
    }
    
    public String getRestaurantName(long restaurantId) {
        if(rR.exists(restaurantId)) {
            return rR.findOne(restaurantId).getName();
        }
        else {
            return "-";
        }
    }
    
    public Restaurant[] getRestaurantList() {
        List<Restaurant> list = new ArrayList<Restaurant>();
        for(Restaurant r:rR.findAll()) {
            list.add(r);
        }
        return list.toArray(new Restaurant[0]);
    }
    
    public boolean restaurantExists(long restaurantId) {
        return rR.exists(restaurantId);
    }
    
    public List<Map<String,Object>> getAdminList(Admin a) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if(a.getLevel() == Constant.BOSS || a.getLevel() == Constant.ENGINEER) {
            Iterator<Admin> iter = aR.findAll().iterator();
            while(iter.hasNext()) {
                Admin i = iter.next();
                Map<String, Object> r = new HashMap<String, Object>();
                r.put("adminId", i.getId());
                r.put("loginName", i.getLoginName());
                r.put("personName", i.getPersonName());
                String restaurantName = "-";
                if(i.getRestaurantId() != 0) {
                    restaurantName = rR.findOne(i.getRestaurantId()).getName();
                }
                r.put("restaurantName", restaurantName);
                r.put("role", Constant.ROLE[i.getLevel()]);
                list.add(r);
            }
        }
        else if(a.getLevel() == Constant.MANAGER) {
            List<Admin> aList = aR.findByRestaurantId(a.getRestaurantId());
            for(Admin i:aList) {
                if(i.getLevel() == Constant.WORKER) {
                    Map<String, Object> r = new HashMap<String, Object>();
                    r.put("adminId", i.getId());
                    r.put("loginName", i.getLoginName());
                    r.put("personName", i.getPersonName());
                    String restaurantName = "-";
                    if(a.getRestaurantId() != 0) {
                        restaurantName = rR.findOne(a.getRestaurantId()).getName();
                    }
                    r.put("restaurantName", restaurantName);
                    r.put("role", Constant.ROLE[a.getLevel()]);
                    list.add(r);
                }
            }
        }
        return list;
    }
    
    public void delete(long adminId) {
        if(aR.exists(adminId)) {
            aR.delete(adminId);
        }
    }
    
    public boolean save(Admin a) {
        Admin ra = aR.save(a);
        if(!ra.getLoginName().equals(a.getLoginName())) {
            return false;
        }
        if(!ra.getPersonName().equals(a.getPersonName())) {
            return false;
        }
        if(!ra.getPassword().equals(a.getPassword())) {
            return false;
        }
        if(ra.getRestaurantId() != a.getRestaurantId()) {
            return false;
        }
        if(ra.getLevel() != a.getLevel()) {
            return false;
        }
        return true;
    }
}
