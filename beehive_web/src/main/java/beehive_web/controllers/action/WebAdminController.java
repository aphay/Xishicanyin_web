package beehive_web.controllers.action;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import beehive_web.models.entity.Admin;
import beehive_web.services.WebAdminService;
import beehive_web.base.Constant;
import beehive_web.base.Json;
import beehive_web.base.Encrypt;

@RestController
@EnableAutoConfiguration
@RequestMapping("/web/admin")
public class WebAdminController {
    @Autowired
    private WebAdminService aS;
    
    @RequestMapping(value = "/createadmin", method = RequestMethod.POST)
    public String createadmin(HttpSession session,@RequestParam("loginName") String loginName, @RequestParam("personName") String personName, 
            @RequestParam("password") String password, @RequestParam("restaurantId") long restaurantId, 
            @RequestParam("level") int level) {
        if((int)session.getAttribute("level") <= Constant.BOSS) {
            return Json.format(5, "创建管理员失败，没有权限");
        }
        else {
        	if(level == Constant.BOSS || level == Constant.ENGINEER) {
        		restaurantId = 0;
        	}
        	else {
	            if(!aS.restaurantExists(restaurantId)) {
	                return Json.format(1, "创建管理员失败，店铺不存在");
	            }
        	}
        }

        if(aS.findByLoginName(loginName) != null) {
            return Json.format(3, "创建管理员失败，该登录名已存在");
        }
        Admin a = new Admin(loginName, personName, password, restaurantId, level);
        if(aS.save(a)) {
            return Json.format(0, "创建管理员成功");
        }
        else {
            return Json.format(2, "创建管理员失败，不能保存管理员");
        }
    }
    @RequestMapping(value = "/removeadmin", method = RequestMethod.POST)
    public String removeadmin(HttpSession session, @RequestParam("adminId") long adminId) {
        if(session != null && (boolean)session.getAttribute("isLogin")) {
            if(adminId == (long)session.getAttribute("adminId")) {
                return Json.format(1, "当前用户不能删除自己");
            }
            aS.delete(adminId);
            return Json.format(0, "删除管理员成功");    
        }
        return Json.format(255, "请先登录");
    }
    @RequestMapping(value = "/updateadmin", method = RequestMethod.POST)
    public String updateadmin(HttpSession session, @RequestParam("adminId") long adminId, @RequestParam("loginName") String loginName, 
            @RequestParam("personName") String personName, @RequestParam("restaurantId") long restaurantId, 
            @RequestParam("level") int level) {
        if((int)session.getAttribute("level") <= Constant.BOSS) {
            return Json.format(5, "创建管理员失败，没有权限");
        }
        else {
        	if(level == Constant.BOSS || level == Constant.ENGINEER) {
        		restaurantId = 0;
        	}
        	else {
	            if(!aS.restaurantExists(restaurantId)) {
	                return Json.format(1, "修改管理员失败，店铺不存在");
	            }
        	}
        }
        
        Admin a = aS.findOne(adminId);
        if(a == null) {
            return Json.format(3, "修改管理员失败，管理员不存在");
        }
        if(!loginName.equals(a.getLoginName()) && aS.findByLoginName(loginName) != null) {
            return Json.format(4, "修改管理员失败，登录名已存在");
        }
        a.setLoginName(loginName);
        a.setPersonName(personName);
        a.setRestaurantId(restaurantId);
        a.setLevel(level);
        if(aS.save(a)) {
            return Json.format(0, "修改管理员成功");
        }
        else {
            return Json.format(2, "修改管理员失败，不能保存管理员");
        }
    }
    @RequestMapping(value = "/updateadminpassword", method = RequestMethod.POST) 
    public String updateadminpassword(HttpSession session, @RequestParam("adminId") long adminId, @RequestParam("password") String password) {
        Admin a = aS.findOne(adminId);
        if(a == null) {
            return Json.format(1, "修改密码失败，管理员不存在");
        }
        
        password = Encrypt.MD5(password);
        a.setPassword(password);
        if(aS.save(a)) {
            return Json.format(0, "修改密码成功");
        }
        else {
            return Json.format(2, "修改密码失败，新密码保存失败");
        }
    }
    @RequestMapping(value = "/adminlist", method = RequestMethod.POST)
    public String adminlist(HttpSession session) {
        Admin a = aS.findOne((long)session.getAttribute("adminId"));
        if(a == null) {
            return Json.format(0, "获取管理员列表失败，管理员不存在");
        }
        List<Map<String, Object>> list = aS.getAdminList(a);
        return Json.format(0, "获取管理员列表成功", list);
    }
    @RequestMapping(value = "/admindetail", method = RequestMethod.POST)
    public String admindetail(HttpSession session, @RequestParam("adminId") long id) {
        Admin a = aS.findOne(id);
        if(a == null) {
            return Json.format(1, "获取管理员详情失败，管理员不存在");
        }
        Map<String, Object> r = new HashMap<String,Object>();
        r.put("adminId", a.getId());
        r.put("loginName", a.getLoginName());
        r.put("personName", a.getPersonName());
        r.put("restaurantId", a.getRestaurantId());
        r.put("level", a.getLevel());
        r.put("role", Constant.ROLE[a.getLevel()]);
        return Json.format(0, "获取管理员详情成功", r);
    }
}
