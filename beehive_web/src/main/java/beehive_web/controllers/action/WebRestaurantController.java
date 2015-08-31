package beehive_web.controllers.action;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import beehive_web.models.entity.Restaurant;
import beehive_web.services.WebRestaurantService;
import beehive_web.base.Json;

@RestController
@EnableAutoConfiguration
@RequestMapping("/web/restaurant")
public class WebRestaurantController {
    @Autowired
    private WebRestaurantService rS;
    
    @RequestMapping(value = "/createrestaurant", method = RequestMethod.POST)
    public String createrestaurant(HttpSession session,@RequestParam("name") String name, @RequestParam("address") String address, 
            @RequestParam("tel") String tel, @RequestParam("openTime") int openTime, 
            @RequestParam("closeTime") int closeTime) {
        if(openTime > closeTime) {
            return Json.format(1, "创建店铺失败，开业时间晚于打烊时间");
        }
        Restaurant r = new Restaurant(name,address,tel,openTime,closeTime);
        if(rS.save(r)) {
            return Json.format(0, "创建店铺成功");
        }
        else {
            return Json.format(2, "创建店铺失败，保存失败");
        }
    }
    
    @RequestMapping(value = "/updaterestaurant", method = RequestMethod.POST)
    public String updaterestaurant(HttpSession session,@RequestParam("restaurantId") long id, @RequestParam("name") String name, @RequestParam("address") String address, 
            @RequestParam("tel") String tel, @RequestParam("openTime") int openTime, 
            @RequestParam("closeTime") int closeTime) {
        if(openTime > closeTime) {
            return Json.format(1, "修改店铺失败，开业时间晚于打烊时间");
        }
        Restaurant r = rS.findOne(id);
        r.setName(name);
        r.setAddress(address);
        r.setTel(tel);
        r.setOpenTime(openTime);
        r.setCloseTime(closeTime);
        if(rS.save(r)) {
            return Json.format(0, "修改店铺成功");
        }
        else {
            return Json.format(2, "修改店铺失败，数据库保存失败");
        }
    }
    @RequestMapping(value = "/removerestaurant", method = RequestMethod.POST)
    public String removerestaurant(HttpSession session,@RequestParam("restaurantId") long id) {
        if(rS.delete(id)) {
            return Json.format(0, "删除店铺成功");
        }
        else {
            return Json.format(1, "删除店铺失败，店铺不存在");
        }
    }
    @RequestMapping(value = "/restaurantlist", method = RequestMethod.POST)
    public String restaurantlist(HttpSession session) {
        List<Map<String,Object>> list = rS.findAll();
        return Json.format(0, "获取店铺列表成功", list);
    }
    @RequestMapping(value = "/restaurantdetail", method = RequestMethod.POST)
    public String restaurantdetail(HttpSession session,@RequestParam("restaurantId") long id) {
        Restaurant r = rS.findOne(id);
        if(r == null) {
            return Json.format(1, "获取店铺详细信息失败，店铺不存在");
        }
        return Json.format(0, "获取店铺详细信息成功", r);
    }
    
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    public String enable(HttpSession session, @RequestParam("restaurantId") long restaurantId) {
        if(rS.enable(restaurantId)) {
            return Json.format(0, "店铺设置生效成功");
        }
        else {
            return Json.format(1, "店铺设置生效失败，店铺不存在");
        }
    }
    
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public String disable(HttpSession session, @RequestParam("restaurantId") long restaurantId) {
        if(rS.disable(restaurantId)) {
            return Json.format(0, "店铺设置失效成功");
        }
        else {
            return Json.format(1, "店铺设置失效失败，店铺不存在");
        }
    }
}
