package beehive_web.controllers.action;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import beehive_web.models.entity.User;
import beehive_web.services.WebUserService;
import beehive_web.base.Json;

@RestController
@EnableAutoConfiguration
@RequestMapping("/web/user")
public class WebUserController {
    @Autowired
    private WebUserService uS;
    
    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public String updateuser(HttpSession session,@RequestParam("userId") long userId, @RequestParam("tel") String tel, 
            @RequestParam("distributionId") long distributionId, @RequestParam("money") float money) {
        User u = uS.findOne(userId);
        if(u == null) {
            return Json.format(1, "修改用户信息失败，用户不存在");
        }
        u.setTel(tel);
        u.setDistributionId(distributionId);
        u.setMoney(money);
        if(uS.save(u)){
            return Json.format(0, "修改用户信息成功", u);
        }
        else {
            return Json.format(3, "修改用户信息失败，数据库保存失败");
        }
    }
    
    @RequestMapping(value = "/setblack", method = RequestMethod.POST)
    public String setblack(HttpSession session,@RequestParam("userId") long userId, @RequestParam("isBlack") boolean isBlack) {
        User u = uS.findOne(userId);
        if(u == null) {
            return Json.format(1, "设置黑名单失败，用户不存在");
        }
        u.setBlack(isBlack);
        if(uS.save(u)) {
            return Json.format(0, "设置黑名单成功", isBlack);
        }
        else {
            return Json.format(2, "设置黑名单失败，保存失败");
        }
    }
    
    @RequestMapping(value = "/userlist", method = RequestMethod.POST)
    public String userlist(HttpSession session) {
        List<Map<String,Object>> list = uS.findAll();
        return Json.format(0, "获取用户列表成功", list);
    }
    
    @RequestMapping(value = "/userdetail", method = RequestMethod.POST)
    public String userdetail(HttpSession session,@RequestParam("userId") long userId) {
        User u = uS.findOne(userId);
        if(u == null) {
            return Json.format(1, "获取用户详情失败，用户不存在");
        }
        return Json.format(0, "获取用户详情成功", u);
    }
    
    @RequestMapping(value = "/searchuser", method = RequestMethod.POST)
    public String searchuser(HttpSession session,@RequestParam("tel") String tel) {
        List<Map<String,Object>> list = uS.findUserByTel(tel);
        return Json.format(0, "搜索用户成功", list);
    }
}
