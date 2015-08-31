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
import beehive_web.models.entity.User;
import beehive_web.models.repository.DistributionRepository;
import beehive_web.models.repository.UserRepository;

@Service
@Transactional
public class WebUserService {
    @Autowired
    private UserRepository uR;
    @Autowired
    private DistributionRepository dR;
    
    public User findOne(long userId) {
        return uR.findOne(userId);
    }
    
    public List<Map<String,Object>> findAll() {
        Iterator<User> iter = uR.findAll().iterator();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        while(iter.hasNext()) {
            User u = iter.next();
            Map<String,Object> m = new HashMap<String,Object>();
            m.put("id", u.getId());
            m.put("tel", u.getTel());
            String distribution = "-";
            if(u.getDistributionId() != 0) {
            	distribution = dR.findOne(u.getDistributionId()).getName();
            }
            m.put("distribution", distribution);
            m.put("money", u.getMoney());
            m.put("isBlack", u.isBlack());
            list.add(m);
        }
        return list;
    }
    
    public List<Map<String,Object>> findUserByTel(String tel) {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(User u:uR.findByTel(tel)) {
            Map<String,Object> m = new HashMap<String,Object>();
            m.put("id", u.getId());
            m.put("tel", u.getTel());
            String distribution = "-";
            if(u.getDistributionId() != 0) {
            	distribution = dR.findOne(u.getDistributionId()).getName();
            }
            m.put("distribution", distribution);
            m.put("money", u.getMoney());
            m.put("isBlack", u.isBlack());
            list.add(m);
        }
        return list;
    }
    
    public Distribution findDistribution(long distributionId) {
        return dR.findOne(distributionId);
    }
    
    public boolean save(User u){
        User ru = uR.save(u);
        if(!ru.getTel().equals(u.getTel())) {
            return false;
        }
        if(ru.getDistributionId() != u.getDistributionId()) {
            return false;
        }
        if(ru.isBlack() != u.isBlack()) {
            return false;
        }
        if(Float.compare(ru.getMoney(), u.getMoney()) != 0) {
            return false;
        }
        return true;
    }
}
