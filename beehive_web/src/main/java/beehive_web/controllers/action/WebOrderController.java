package beehive_web.controllers.action;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import beehive_web.models.entity.Order;
import beehive_web.models.entity.User;
import beehive_web.services.WebOrderService;
import beehive_web.base.Cache;
import beehive_web.base.Constant;
import beehive_web.base.Json;

@RestController
@EnableAutoConfiguration
@RequestMapping("/web/order")
public class WebOrderController {
    @Autowired
    private WebOrderService oS;
    
    @RequestMapping(value = "/createorder", method = RequestMethod.POST)
    public String createorder(HttpSession session,@RequestParam("userId") long userId,@RequestParam("totalPrice") float totalPrice,
            @RequestParam("voucherId") long voucherId, @RequestParam("remark") String remark,
            @RequestParam("restaurantId") long restaurantId,
            @RequestParam("orderType") int orderType, @RequestParam("status") int status,
            @RequestParam("hasPaid") boolean hasPaid, @RequestParam("dishes") String dishes){
        //计算菜品分类统计
        Map<String,Integer> dishesType = new HashMap<String,Integer>(); 
        Map<Long, Long> dtMap = oS.getDishesTypeMap();
        Map<String, Integer> dMap = Json.decodeCar(dishes);
        for(String key:dMap.keySet()) {
            String k = "" + dtMap.get(Long.parseLong(key));        //获取菜品分类Id
            if(dishesType.containsKey(k)) {
                dishesType.put(k, dishesType.get(k) + 1);
            }
            else {
                dishesType.put(k, 1);
            }
        }
        
        User u = oS.findUserById(userId);
        if(u == null) {
            return Json.format(2, "创建订单失败，用户不存在");
        }
        if(u.getDistributionId()==0 || u.getTel().equals("")) {
        	return Json.format(3, "创建订单失败，用户的电话与取餐点为空");
        }
        Order o = new Order(dishes,Json.encodeCar(dishesType),totalPrice,voucherId,remark,restaurantId,u.getDistributionId(),
                status, userId, u.getTel(),orderType, hasPaid);
        if(orderType == Constant.COD || orderType == Constant.USER_MONEY) {
            //如果是货到付款订单，就标志为已付过款，方便后续处理
            o.setHasPaid(Constant.PAID);
        }
        if(o.getStatus() == Constant.CANCEL) {
        	o.setCancelRemark(Constant.WEB_CANCEL);
        }
        if(oS.save(o)) {
            Cache.getInstance().add(restaurantId);
            return Json.format(0, "创建订单成功");
        }
        else {
            return Json.format(4, "创建订单失败，新建订单失败");
        }
    }
    
    @RequestMapping(value = "/deleteorder", method = RequestMethod.POST)
    public String deleteorder(HttpSession session,@RequestParam("orderId") long orderId) {
        if(oS.delete(orderId)){
            return Json.format(0, "删除订单成功");
        }
        else {
            return Json.format(1, "删除订单失败，订单不存在");
        }

    }
    
    @RequestMapping(value = "/updateorder", method = RequestMethod.POST)
    public String updateorder(HttpSession session,@RequestParam("orderId") long orderId, @RequestParam("userId") long userId,
            @RequestParam("totalPrice") float totalPrice,
            @RequestParam("restaurantId") long restaurantId,
            @RequestParam("voucherId") long voucherId, @RequestParam("remark") String remark, 
            @RequestParam("orderType") int orderType, @RequestParam("status") int status,
            @RequestParam("hasPaid") boolean hasPaid,@RequestParam("dishes") String dishes){
        //计算菜品分类统计
        Map<String,Integer> dishesType = new HashMap<String,Integer>(); 
        Map<Long, Long> dtMap = oS.getDishesTypeMap();
        Map<String, Integer> dMap = Json.decodeCar(dishes);
        for(String key:dMap.keySet()) {
            String k = "" + dtMap.get(Long.parseLong(key));        //获取菜品分类Id
            if(dishesType.containsKey(k)) {
                dishesType.put(k, dishesType.get(k) + 1);
            }
            else {
                dishesType.put(k, 1);
            }
        }
        
        User u = oS.findUserById(userId);
        if(u == null) {
            return Json.format(1, "创建订单失败，用户不存在");
        }

        if(u.getDistributionId()==0 || u.getTel().equals("")) {
        	return Json.format(3, "创建订单失败，用户的电话与取餐点为空");
        }
        Order o = oS.findOne(orderId);
        if(o == null) {
            return Json.format(4, "创建订单失败，订单不存在");
        }
        int originalStatus = o.getStatus();
        o.setDishes(dishes);
        o.setDishesType(Json.encodeCar(dishesType));
        o.setTotalPrice(totalPrice);
        o.setVoucherId(voucherId);
        o.setRemarks(remark);
        o.setRestaurantId(restaurantId);
        o.setDistributionId(u.getDistributionId());
        o.setStatus(status);
        o.setUserId(userId);
        o.setTel(u.getTel());
        o.setOrderType(orderType);
        o.setHasPaid(hasPaid);
        if(orderType == Constant.COD || orderType == Constant.USER_MONEY) {
            //如果是货到付款订单，就标志为已付过款，方便后续处理
            o.setHasPaid(Constant.PAID);
        }
        if(o.getStatus() == Constant.CANCEL) {
        	o.setCancelRemark(Constant.WEB_CANCEL);
        }
        if(oS.save(o)) {
            if(originalStatus == Constant.COMPLETE && status != Constant.COMPLETE) {
                Cache.getInstance().add(restaurantId);
            }
            if(originalStatus != Constant.COMPLETE && status == Constant.COMPLETE) {
                Cache.getInstance().minus(restaurantId);
            }
            return Json.format(0, "修改订单成功");
        }
        else {
            return Json.format(2, "修改订单失败，数据库保存失败");
        }
    }
    
    @RequestMapping(value = "/orderlist", method = RequestMethod.POST)
    public String orderlist(HttpSession session, @RequestParam("time") String time, @RequestParam("tel") String tel) {
    	List<Map<String,Object>> list = null;
    	if(tel.equals("")) {
	    	list = oS.findAll(time);
        }
    	else {
    		list = oS.findAll(time, tel);
    	}
    	return Json.format(0, "获取订单列表成功", list);
    }
    
    @RequestMapping(value = "/orderdetail", method = RequestMethod.POST)
    public String orderdetail(HttpSession session,@RequestParam("orderId") long orderId) {
        Map<String,Object> o = oS.findMapOne(orderId);
        if(o == null) {
            return Json.format(1, "获取订单详情失败，订单未找到");
        }
        return Json.format(0, "获取订单详情成功", o);
    }
    
    @RequestMapping(value = "/orderdetail2", method = RequestMethod.POST)
    public String orderdetail2(HttpSession session,@RequestParam("orderId") long orderId) {
        Map<String,Object> map = oS.findMapOne2(orderId);
        if(map == null) {
            return Json.format(1, "获取订单详情失败，订单未找到");
        }
        return Json.format(0, "获取订单详情成功", map);
    }
    
    @RequestMapping(value = "/orderstatisics", method = RequestMethod.POST)
    public String orderstatisics(HttpSession session,@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
        //将json中的菜品统计出来
        Map<String, Long> s = oS.statistics(startTime, endTime);
        return Json.format(0, "统计菜品销量成功", s);
    }
}
