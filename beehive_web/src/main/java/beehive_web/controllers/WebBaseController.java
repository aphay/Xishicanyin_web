package beehive_web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import beehive_web.models.entity.Admin;
import beehive_web.models.entity.Dishes;
import beehive_web.models.entity.DishesType;
import beehive_web.models.entity.Distribution;
import beehive_web.models.entity.Order;
import beehive_web.models.repository.DishesRepository;
import beehive_web.models.repository.DishesTypeRepository;
import beehive_web.models.repository.DistributionRepository;
import beehive_web.models.repository.OrderRepository;
import beehive_web.models.repository.UserRepository;
import beehive_web.base.Constant;
import beehive_web.base.Encrypt;
import beehive_web.base.TimeUtil;
import beehive_web.services.WebAdminService;

@Controller
@EnableAutoConfiguration
public class WebBaseController {
    @Autowired
    private WebAdminService aS;
    @Autowired
    private OrderRepository oR;
    @Autowired
    private UserRepository uR;
    @Autowired
    private DishesRepository dR;
    @Autowired
    private DistributionRepository distributionR;
    @Autowired
    private DishesTypeRepository dtR;
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpSession session, Map<String,Object> model) {
        if(session != null && 
            session.getAttribute("isLogin") != null && 
            (boolean)session.getAttribute("isLogin") == true) {
            model.putAll(portalData());
            return "portal";
        }
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, Map<String,Object> model,@RequestParam("loginName") String loginName, @RequestParam("password") String password) {
        Admin a = aS.findByLoginName(loginName);
        if(a == null) {
            model.put("msg", "登录失败，管理员不存在");
            return "login";
        }
        
        if(a.getLevel() != Constant.BOSS && a.getLevel() != Constant.ENGINEER) {
        	model.put("msg", "登录失败，权限不够");
        	return "login";
        }
        
        if(!Encrypt.MD5(password).equals(a.getPassword())) {
            model.put("msg", "登录失败，密码不正确");
            return "login";
        }
        
        session.setAttribute("isLogin", true);
        session.setAttribute("adminId", a.getId());
        session.setAttribute("personName", a.getPersonName());
        session.setAttribute("restaurantId", a.getRestaurantId());
        session.setAttribute("restaurantName", aS.getRestaurantName(a.getRestaurantId()));
        session.setAttribute("level", a.getLevel());
        session.setAttribute("role", Constant.ROLE[a.getLevel()]);
        session.setAttribute("lastLoginTime", TimeUtil.now());
        model.putAll(portalData());
        return "portal";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session,Map<String,Object> model) {
        session.setAttribute("isLogin", false);
        session.removeAttribute("adminId");
        session.removeAttribute("personName");
        session.removeAttribute("restaurantId");
        session.removeAttribute("restaurantName");
        session.removeAttribute("level");
        session.removeAttribute("role");
        session.removeAttribute("lastLoginTime");
        return "login";
    }
    
    @RequestMapping(value = "/portal", method = RequestMethod.GET)
    public String pagePortal(HttpSession session, Map<String,Object> model) {
        model.putAll(portalData());
        return "portal";
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String pageAdmin(HttpSession session, Map<String,Object> model) {
        return "admin";
    }
    
    @RequestMapping(value = "/adminEdit", method = RequestMethod.POST)
    public String pageAdminEdit(HttpSession session, Map<String,Object> model, @RequestParam("adminId") long adminId) {
        model.put("id", adminId);
        model.put("restaurantList", aS.getRestaurantList());
        model.put("roleList", Constant.ROLE);
        return "adminEdit";
    }
    
    @RequestMapping(value = "/dishes", method = RequestMethod.GET)
    public String pageDishes(HttpSession session, Map<String,Object> model) {
    	return "dishes";
    }
    
    @RequestMapping(value = "/dishesEdit", method = RequestMethod.POST)
    public String pageDishesEdit(HttpSession session, Map<String,Object> model, @RequestParam("dishesId") long dishesId) {
    	model.put("id", dishesId);
    	Iterator<DishesType> iter = dtR.findAll().iterator();
    	List<DishesType> dtList = new ArrayList<DishesType>();
    	while(iter.hasNext()) {
    		dtList.add(iter.next());
    	}
    	model.put("typeList", dtList.toArray(new DishesType[0]));
    	return "dishesEdit";
    }
    
    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public String pageRestaurant(HttpSession session, Map<String,Object> model) {
    	return "restaurant";
    }
    
    @RequestMapping(value = "/restaurantEdit", method = RequestMethod.POST)
    public String pageRestaurantEdit(HttpSession session, Map<String,Object> model, @RequestParam("restaurantId") long restaurantId) {
    	model.put("id", restaurantId);
        return "restaurantEdit";
    }
    
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String pageOrder(HttpSession session, Map<String,Object> model) {
    	return "order";
    }
    
    @RequestMapping(value = "/orderEdit", method = RequestMethod.POST)
    public String pageOrderEdit(HttpSession session, Map<String,Object> model, @RequestParam("orderId") long orderId) {
    	model.put("id", orderId);
    	model.put("restaurantList", aS.getRestaurantList());
    	model.put("orderTypeList", Constant.PAY_TYPE);
    	model.put("statusList",Constant.ORDER_STATUS);
    	model.put("hasPaidList",new String[]{"否","是"});
    	Iterator<Dishes> iter = dR.findAll().iterator();
    	List<Dishes> dList = new ArrayList<Dishes>();
    	while(iter.hasNext()) {
    		dList.add(iter.next());
    	}
    	model.put("dishesList", dList.toArray(new Dishes[0]));
    	return "orderEdit";
    }
    
    @RequestMapping(value = "/distribution", method = RequestMethod.GET)
    public String pageDistribution(HttpSession session, Map<String,Object> model) {
    	return "distribution";
    }
    
    @RequestMapping(value = "/distributionEdit", method = RequestMethod.POST)
    public String pageDistribution(HttpSession session, Map<String,Object> model, @RequestParam("distributionId") long distributionId) {
    	model.put("id", distributionId);
        model.put("restaurantList", aS.getRestaurantList());
    	return "distributionEdit";
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String pageUser(HttpSession session, Map<String,Object> model) {
    	return "user";
    }
    
    @RequestMapping(value = "/userEdit", method = RequestMethod.POST)
    public String pageUserEdit(HttpSession session, Map<String,Object> model, @RequestParam("userId") long userId) {
    	Iterator<Distribution> iter = distributionR.findAll().iterator();
    	List<Distribution> dList = new ArrayList<Distribution>();
    	while(iter.hasNext()) {
    		dList.add(iter.next());
    	}
    	model.put("id", userId);
    	model.put("distributionList", dList.toArray(new Distribution[0]));
    	return "userEdit";
    }
    
    //欢迎页数据加载
    private Map<String, Object> portalData() {
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("orderCount", oR.countByCreateTimeGreaterThan(TimeUtil.today()));
        m.put("completeCount", oR.countByStatusAndCreateTimeGreaterThan(Constant.COMPLETE, TimeUtil.today()));
        m.put("userCount", uR.count());
        double totalPrice = 0.00;
        for(Order o:oR.findByStatusAndHasPaidAndCreateTimeGreaterThan(Constant.COMPLETE, Constant.PAID, TimeUtil.today())) {
            totalPrice += o.getTotalPrice();
        }
        m.put("money", totalPrice);
        String sales = "";
        for(Dishes d:dR.findTop10ByOrderBySalesDesc()) {
            sales += "[\"" + d.getName() + "\", " + d.getSales() + "],";
        }
        m.put("salesTop10", sales);
        return m;
    }
}
