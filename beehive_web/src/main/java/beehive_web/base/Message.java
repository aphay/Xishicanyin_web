package beehive_web.base;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.iharder.Base64;

import net.sf.json.JSONObject;

public class Message {
    public static final boolean sendValidateCode(String phone, String validateCode) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = df.format(new Date());
        
        String sig = Encrypt.MD5(Constant.M_ACCOUNT_SID+Constant.M_AUTH_TOKEN+timestamp).toUpperCase();
        String authorization = "";
        try {
            authorization = Base64.encodeBytes(
                    new String(Constant.M_ACCOUNT_SID+":"+timestamp).getBytes("utf-8")
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        String url = Constant.M_URL + sig;
        String param = 
                "{\"templateSMS\":{"
                + "\"appId\":\""+Constant.M_APP_ID+"\","
                + "\"param\":\"" + validateCode + "\","
                + "\"templateId\":\""+Constant.M_TEMP_ID+"\","
                + "\"to\":\""+phone+"\""
                + "}}";
        Map<String, String> header = new HashMap<String, String>();
        header.put("accept", "application/json");
        header.put("content-type", "application/json;charset=utf-8");
        header.put("Authorization", authorization);
        
        String ret = Web.sendPost(url, header, param);
        JSONObject json = JSONObject.fromObject(ret);
        JSONObject resp = JSONObject.fromObject(json.get("resp"));
        return resp.getString("respCode").equals("000000");
    }
    
    public static final boolean sendPushMessage(String deviceToken, String msg) {
        JSONObject json = new JSONObject();
        json.put("appkey", Constant.U_APP_KEY);
        json.put("timestamp", (new Date()).getTime()/1000);
        json.put("device_tokens", deviceToken);
        json.put("type", "unicast");
        JSONObject payload = new JSONObject();
        JSONObject body = new JSONObject();
        body.put("ticker", "新订单");
        body.put("title", "新订单");
        body.put("text", "请打开客户端查看");
        body.put("after_open", "go_app");
        body.put("custom", msg);
        payload.put("body", body);
        payload.put("display_type", "notification");
        json.put("payload", payload);
        
        String sign = Encrypt.MD5("POST" + Constant.U_URL + json.toString() + Constant.U_APP_MASTER_SECRET).toLowerCase();
        String url = Constant.U_URL + "?sign=" + sign;
        
        String ret = Web.sendPost(url, new HashMap<String,String>(), json.toString());
        JSONObject resp = JSONObject.fromObject(ret);
        return resp.getString("ret").equals("SUCCESS");
    }
//    public static void main(String[] args) {
//        System.out.println(
//            sendPushMessage(
//                "AmuXn06EoPbfch_40ER5cqtkqcffglvwKNnPMswyln96",
//                "{\"createTime\":\"2015-06-24 00:45:47\",\"takeNo\":32,\"status\":0,\"updateTime\":\"2015-07-17 01:04:27\",\"orderType\":1,\"dishesType\":[{\"id\":3,\"count\":2,\"name\":\"商务套餐\"},{\"id\":1,\"count\":3,\"name\":\"炒菜\"}],\"tel\":\"13687888888\",\"distribution\":\"配送点2\",\"operator\":\"测试管理员\",\"restaurantId\":1,\"orderId\":12,\"totalPrice\":188}"
//            )
//        );
//    }
}
