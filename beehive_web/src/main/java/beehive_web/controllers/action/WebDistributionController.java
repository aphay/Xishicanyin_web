package beehive_web.controllers.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import beehive_web.models.entity.Distribution;
import beehive_web.base.Json;
import beehive_web.services.WebDistributionService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/web/distribution")
public class WebDistributionController {
    @Autowired
    private WebDistributionService dS;
    
    @RequestMapping(value = "/createdistribution", method = RequestMethod.POST)
    public String createdistribution(HttpSession session,@RequestParam("name") String name, @RequestParam("address") String address,
            @RequestParam("tel") String tel) {
        Distribution d = new Distribution(name,tel,address,"");
        if(dS.save(d)) {
            return Json.format(0, "创建取餐点成功");
        }
        else {
            return Json.format(1, "创建取餐点失败，数据库保存失败");
        }
    }
    
    @RequestMapping(value = "/updatedistribution", method = RequestMethod.POST)
    public String updatedistribution(HttpSession session,@RequestParam("distributionId") long distributionId, @RequestParam("name") String name, 
            @RequestParam("address") String address,@RequestParam("tel") String tel) {
        Distribution d = dS.findOne(distributionId);
        if(d == null) {
            return Json.format(1, "修改取餐点失败，取餐点不存在");
        }
        d.setName(name);
        d.setAddress(address);
        d.setTel(tel);
        if(dS.save(d)) {
            return Json.format(0, "修改取餐点成功");
        }
        else {
            return Json.format(2, "修改取餐点失败，数据库保存失败");
        }
    }
    
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    public String enable(HttpSession session, @RequestParam("distributionId") long distributionId) {
        if(dS.enable(distributionId)) {
            return Json.format(0, "取餐点设置生效成功");
        }
        else {
            return Json.format(1, "取餐点设置生效失败，取餐点不存在");
        }
    }
    
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public String disable(HttpSession session, @RequestParam("distributionId") long distributionId) {
        if(dS.disable(distributionId)) {
            return Json.format(0, "取餐点设置失效成功");
        }
        else {
            return Json.format(1, "取餐点设置失效失败，取餐点不存在");
        }
    }
    
    @RequestMapping(value = "/deletedistribution", method = RequestMethod.POST)
    public String deletedistribution(HttpSession session,@RequestParam("distributionId") long distributionId) {
        if(dS.delete(distributionId)) {
            return Json.format(0, "删除取餐点成功");
        }
        else {
            return Json.format(1, "删除取餐点失败，取餐点不存在");
        }
    }
    
    @RequestMapping(value = "/distributionlist", method = RequestMethod.POST)
    public String distributionlist(HttpSession session) {
        List<Distribution> list = dS.findAll();
        return Json.format(0, "获取取餐点列表成功", list);
    }
    
    @RequestMapping(value = "/distributiondetail", method = RequestMethod.POST)
    public String distributiondetail(HttpSession session,@RequestParam("distributionId") long distributionId) {
        if(dS.findOne(distributionId) == null) {
            return Json.format(1, "获取取餐点详情失败，取餐点不存在");
        }
        Map<String,Object> r = dS.detail(distributionId);
        return Json.format(0, "获取取餐点详情成功", r);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpSession session,@RequestParam("distributionId") long distributionId, @RequestParam("restaurantId") long restaurantId) {
        if(dS.addToRestaurant(distributionId, restaurantId)) {
            return Json.format(0, "添加成功");
        }
        else {
            return Json.format(1, "添加失败");
        }
    }
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(HttpSession session,@RequestParam("distributionId") long distributionId, @RequestParam("restaurantId") long restaurantId) {
        if(dS.removeFromRestaurant(distributionId, restaurantId)) {
            return Json.format(0, "移除成功");
        }
        else {
            return Json.format(1, "移除失败");
        }
    }
    @RequestMapping(value = "/addall", method = RequestMethod.POST)
    public String addall(HttpSession session,@RequestParam("distributionId") long distributionId) {
        if(dS.addToAllRestaurant(distributionId)) {
            return Json.format(0, "添加成功");
        }
        else {
            return Json.format(1, "添加失败");
        }
    }
    @RequestMapping(value = "/removeall", method = RequestMethod.POST)
    public String removeall(HttpSession session,@RequestParam("distributionId") long distributionId) {
        if(dS.removeFromAllRestaurant(distributionId)) {
            return Json.format(0, "移除成功");
        }
        else {
            return Json.format(1, "移除失败");
        }
    }
    
}
