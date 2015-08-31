package beehive_web.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beehive_web.base.Constant;
import beehive_web.models.entity.Dishes;
import beehive_web.models.entity.Distribution;
import beehive_web.models.entity.Restaurant;
import beehive_web.models.entity.RestaurantAndDistribution;
import beehive_web.models.repository.DistributionRepository;
import beehive_web.models.repository.RestaurantAndDistributionRepository;
import beehive_web.models.repository.RestaurantRepository;

@Service
@Transactional
public class WebDistributionService {
    @Autowired
    private DistributionRepository dR;
    @Autowired
    private RestaurantRepository rR;
    @Autowired
    private RestaurantAndDistributionRepository rdR;

    public Distribution findOne(long distributionId) {
        return dR.findOne(distributionId);
    }
    
    public List<Distribution> findAll() {
        List<Distribution> list = new ArrayList<Distribution>();
        Iterator<Distribution> iter = dR.findAll().iterator();
        while(iter.hasNext()) {
            list.add(iter.next());
        }
        return list;
    }
    
    public boolean delete(long distributionId) {
        if(dR.exists(distributionId)) {
            rdR.deleteByDistributionId(distributionId);
            dR.delete(distributionId);
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean save(Distribution d) {
        Distribution rd = dR.save(d);
        if(!rd.getName().equals(d.getName())) {
            return false;
        }
        if(!rd.getAddress().equals(d.getAddress())){
            return false;
        }
        if(!rd.getTel().equals(d.getTel())) {
            return false;
        }
        return true;
    }
    
    public Map<String, Object> detail(long distributionId) {
        Map<String, Object> r = new HashMap<String, Object>();
        Distribution d = dR.findOne(distributionId);
        r.put("id", d.getId());
        r.put("name", d.getName());
        r.put("address", d.getAddress());
        r.put("tel", d.getTel());
        List<Long> ids = new ArrayList<Long>();
        for(RestaurantAndDistribution rd:rdR.findByDistributionId(distributionId)) {
            ids.add(rd.getRestaurantId());
        }
        List<Restaurant> rList = new ArrayList<Restaurant>();
        Iterator<Restaurant> iter = rR.findAll(ids).iterator();
        while(iter.hasNext()) {
            rList.add(iter.next());
        }
        r.put("restaurant", rList);
        r.put("enable", d.isEnable());
        return r;
    }
    
    public boolean enable(long distributionId) {
        Distribution d = dR.findOne(distributionId);
        if(d == null) {
            return false;
        }
        d.setEnable(true);
        dR.save(d);
        return true;
    }
    
    public boolean disable(long distributionId) {
    	Distribution d = dR.findOne(distributionId);
        if(d == null) {
            return false;
        }
        d.setEnable(false);
        dR.save(d);
        return true;
    }
    
    public boolean addToRestaurant(long distributionId, long restaurantId) {
        if(!dR.exists(distributionId) || !rR.exists(restaurantId)) {
            return false;
        }
        if(rdR.findByRestaurantIdAndDistributionId(restaurantId, distributionId) == null) {
            RestaurantAndDistribution rd = new RestaurantAndDistribution(restaurantId, distributionId);
            rdR.save(rd);
        }
        return true;
    }
    
    public boolean removeFromRestaurant(long distributionId, long restaurantId) {
        if(!dR.exists(distributionId) || !rR.exists(restaurantId)) {
            return false;
        }
        rdR.deleteByRestaurantIdAndDistributionId(restaurantId, distributionId);
        return true;
    }
    
    public boolean addToAllRestaurant(long distributionId) {
        if(!dR.exists(distributionId)) {
            return false;
        }
        Iterator<Restaurant> iter = rR.findAll().iterator();
        List<RestaurantAndDistribution> list = new ArrayList<RestaurantAndDistribution>();
        while(iter.hasNext()) {
            Restaurant r = iter.next();
            if(rdR.findByRestaurantIdAndDistributionId(r.getId(), distributionId) == null) {
                list.add(new RestaurantAndDistribution(r.getId(), distributionId));
            }
        }
        rdR.save(list);
        return true;
    }
    
    public boolean removeFromAllRestaurant(long distributionId) {
        if(!dR.exists(distributionId)) {
            return false;
        }
        rdR.deleteByDistributionId(distributionId);
        return true;
    }
}
