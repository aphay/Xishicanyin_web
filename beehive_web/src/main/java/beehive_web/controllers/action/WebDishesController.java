package beehive_web.controllers.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import beehive_web.models.entity.Dishes;
import beehive_web.services.WebDishesService;
import beehive_web.base.Constant;
import beehive_web.base.Json;

@RestController
@EnableAutoConfiguration
@RequestMapping("/web/dishes")
public class WebDishesController {
    @Autowired
    private WebDishesService dS;
    
    @RequestMapping(value = "/createdishes", method = RequestMethod.POST)
    public String createdishes(HttpSession session,@RequestParam("name") String name, @RequestParam("typeId") long typeId, 
            @RequestParam("desc") String desc, @RequestParam("price") float price, 
            @RequestParam("sales") long sales, @RequestParam("recommand") int recommand,
            @RequestParam("discount") float discount) {
        //菜品保存
        Dishes d = new Dishes(name, typeId, desc, price, sales, recommand, discount);
        if(dS.save(d)) {
            return Json.format(0, "添加菜品成功");
        }
        else {
            return Json.format(1, "添加菜品失败，保存失败");
        }
    }
    
    @RequestMapping(value = "/onshow", method = RequestMethod.POST)
    public String onshow(HttpSession session, @RequestParam("dishesId") long dishesId) {
        if(dS.onShow(dishesId)) {
            return Json.format(0, "菜品上架成功");
        }
        else {
            return Json.format(1, "菜品上架失败，菜品不存在");
        }
    }
    
    @RequestMapping(value = "/offshow", method = RequestMethod.POST)
    public String offshow(HttpSession session, @RequestParam("dishesId") long dishesId) {
        if(dS.offShow(dishesId)) {
            return Json.format(0, "菜品下架成功");
        }
        else {
            return Json.format(1, "菜品下架失败，菜品不存在");
        }
    }
    
    @RequestMapping(value = "/updatedishes", method = RequestMethod.POST)
    public String updatedishes(HttpSession session,@RequestParam("dishesId") long id,
            @RequestParam("name") String name, @RequestParam("typeId") long typeId, 
            @RequestParam("desc") String description, @RequestParam("price") float price, 
            @RequestParam("sales") long sales, @RequestParam("recommand") int recommand,
            @RequestParam("discount") float discount) {
        //保存图片
        Dishes d = dS.findOne(id);
        if(d == null) {
            return Json.format(1, "修改菜品失败，菜品不存在");
        }
        d.setName(name);
        d.setTypeId(typeId);
        d.setDescription(description);
        d.setPrice(price);
        d.setSales(sales);
        d.setRecommand(recommand);
        d.setDiscount(discount);
        if(dS.save(d)) {
            return Json.format(0, "修改菜品成功");
        }
        else {
            return Json.format(2, "修改菜品失败，保存失败");
        }
    }
    
    @RequestMapping(value = "/disheslist", method = RequestMethod.POST)
    public String disheslist(HttpSession session) {
        List<Map<String,Object>> list = dS.findAll();
        return Json.format(0, "获取菜品列表成功", list);
    }
    
    @RequestMapping(value = "/dishesdetail", method = RequestMethod.POST)
    public String dishesdetail(HttpSession session,@RequestParam("dishesId") long dishesId) {
        Dishes d = dS.findOne(dishesId);
        if(d == null) {
            return Json.format(1, "获取菜品详情失败，菜品不存在");
        }
        return Json.format(0, "获取菜品详情成功", d);
    }
    
    @RequestMapping(value = "/uploadpicture", method = RequestMethod.POST)
    public String uploadpicture(HttpSession session,HttpServletRequest request,@RequestParam("dishesId") long dishesId, 
            @RequestParam(value = "picture") MultipartFile picture) {
        //检查存储图片的路径是否存在
        String folder = request.getServletContext().getRealPath("/") + Constant.PICTURE_PATH;
        File file = new File(folder);
        if(!file.exists()) {
            file.mkdirs();
        }
        
        Dishes d = dS.findOne(dishesId);
        if(d == null) {
            return Json.format(1, "上传图片失败，菜品不存在");
        }
        //图片上传
        String url = "/";
        try {
            url = updatePicture(dishesId, picture, folder);
        } catch (Exception e) {
            return Json.format(2, e.getMessage());
        }
        //保存图片地址
        d.setPicture(url);
        if(dS.save(d)) {
            return Json.format(0, "上传图片成功", url);
        }
        else {
            return Json.format(3, "上传图片失败，保存数据库失败");
        }
    }
    
    //上传图片函数
    private String updatePicture(long dishesId, MultipartFile picture, String folder) throws Exception {
        //验证图片合法
        if(picture.getSize() > 1024 * 1024) {
            throw new Exception("上传图片失败，图片大小应小于1M");
        }
        String filename = picture.getOriginalFilename();
        String[] pictureName = filename.split("\\.");
        if(pictureName.length != 2) {
            throw new Exception("上传图片失败，文件名格式错误");
        }
        boolean legalSuffix = false;
        for(String suffix:Constant.LEGAL_SUFFIX) {
            if(pictureName[1].equals(suffix)) {
                legalSuffix = true;
                break;
            }
        }
        if(!legalSuffix) {
            throw new Exception("上传图片失败，非法文件类型");
        }
        //上传并获得文件url
        String path = folder + "/" + dishesId + "." + pictureName[1];
        System.out.println(path);
        FileOutputStream out = new FileOutputStream(new File(path));
        try {
            out.write(picture.getBytes());
            String url = "/" + Constant.PICTURE_PATH + "/" + dishesId + "." + pictureName[1];
            return url;
        }
        catch(Exception e) {
            e.printStackTrace();
            return "/";
        }
        finally{
            out.close();
        }
    }
}
