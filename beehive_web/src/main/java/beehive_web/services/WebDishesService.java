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
import beehive_web.models.entity.Dishes;
import beehive_web.models.entity.DishesType;
import beehive_web.models.repository.DishesRepository;
import beehive_web.models.repository.DishesTypeRepository;

@Service
@Transactional
public class WebDishesService {
    @Autowired
    private DishesRepository dR;
    @Autowired
    private DishesTypeRepository dtR;
    
    public boolean save(Dishes d) {
        Dishes rd = dR.save(d);
        if(!rd.getName().equals(d.getName())) {
            return false;
        }
        if(rd.getTypeId() != d.getTypeId()) {
            return false;
        }
        if(!rd.getDescription().equals(d.getDescription())) {
            return false;
        }
        if(Float.compare(rd.getPrice(), d.getPrice()) != 0) {
            return false;
        }
        if(d.getSales() != d.getSales()) {
            return false;
        }
        if(rd.getRecommand() != d.getRecommand()) {
            return false;
        }
        if(Float.compare(rd.getDiscount(), d.getDiscount()) != 0) {
            return false;
        }
        return true;
    }
    
    public boolean delete(long dishesId) {
        if(!dR.exists(dishesId)) {
            return false;
        }
        dR.delete(dishesId);
        return true;
    }
    
    public boolean onShow(long dishesId) {
        Dishes d = dR.findOne(dishesId);
        if(d == null) {
            return false;
        }
        d.setOnShow(Constant.ON_SHOW);
        dR.save(d);
        return true;
    }
    
    public boolean offShow(long dishesId) {
        Dishes d = dR.findOne(dishesId);
        if(d == null) {
            return false;
        }
        d.setOnShow(Constant.OFF_SHOW);
        dR.save(d);
        return true;
    }
    
    public Dishes findOne(long dishesId) {
        return dR.findOne(dishesId);
    }
    
    public List<Map<String,Object>> findAll() {
    	Map<Long,String> dtMap = getDishesTypeMap();
        Iterator<Dishes> iter = dR.findAll().iterator();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        while(iter.hasNext()) {
        	Map<String,Object> m = new HashMap<String,Object>();
            Dishes d = iter.next();
            m.put("id", d.getId());
            m.put("name", d.getName());
            m.put("type", dtMap.get(d.getTypeId()));
            m.put("path", d.getPicture());
            m.put("price", d.getPrice());
            m.put("sales", d.getSales());
            m.put("discount", d.getDiscount());
            m.put("onShow", d.isOnShow());
            list.add(m);
        }
        return list;
    }
    
    private Map<Long,String> getDishesTypeMap() {
    	Map<Long,String> m = new HashMap<Long,String>();
    	Iterator<DishesType> iter = dtR.findAll().iterator();
    	while(iter.hasNext()) {
    		DishesType dt = iter.next();
    		m.put(dt.getId(), dt.getName());
    	}
    	return m;
    }
}
