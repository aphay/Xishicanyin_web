package beehive_web.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class Json {
    public final static String encode(Map<String,Object> map) {
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }

    public final static String encodeCar(Map<String,Integer> map) {
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }
    
    //专用于解析购物车以及订单的json数据
    public final static Map<String,Integer> decodeCar(String jsonStr) {
        JSONObject json = JSONObject.fromObject(jsonStr);
        Map<String,Integer> r = new HashMap<String,Integer>();
        Iterator<String> keys = json.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            r.put(key, json.getInt(key));
        }
        return r;
    }
    
    public final static String format(int ret, String msg) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ret", ret);
        map.put("msg", msg);
        return encode(map);
    }
    
    public final static String format(int ret, String msg, Object data) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ret", ret);
        map.put("msg", msg);
        map.put("data", data);
        return encode(map);
    }
    
    //将Json转成xml文档
    public final static String json2xml(Map<String,Object> map) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setRootName("xml");
        xmlSerializer.setTypeHintsEnabled(false);
        return xmlSerializer.write(JSONObject.fromObject(map)).split("\n", 2)[1];
    }
    
    //将xml转成Json
    public final static String xml2json(String xml) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        return ((JSONObject)xmlSerializer.read(xml)).toString();
    }
    
//    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("test", 1);
//        map.put("test2", "3333");
//        String xml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx2421b1c4370ec43b]]></appid><mch_id><![CDATA[10000100]]></mch_id></xml>";
//        JSONObject json = JSONObject.fromObject(xml2json(xml));
//        System.out.println(json.getString("return_code"));
//    }
}
