package beehive_web.base;

import java.util.*;

import net.sf.json.JSONObject;

public class Constant {
    
    //订单
    public final static int NEW = 0;
    public final static int DELIVERY = 1;
    public final static int COMPLETE = 2;
    public final static int CANCEL = 3;
    public final static String[] ORDER_STATUS = {
        "新订单",
        "已配送",
        "已完成",
        "已取消"
    };
    //支付
    public final static boolean NOT_PAID = false;
    public final static boolean PAID = true;
    
    //优惠券
    public final static int NOT_USED = 0;
    public final static int USED = 1;
    public final static int OVERDUE = 2;
    public final static String[] VOUCHER_STATUS = {
        "未使用",
        "已使用",
        "已到期"
    };
    
    //管理员
    public final static int SUPERADMIN = 0;
    
    //用户
    public final static boolean BLACK = true;
    public final static boolean NOT_BLACK = false;
    
    //菜品分类
    public final static String[] DISHES_TYPE = {
        "炒菜",
        "盖烧饭",
        "商务套餐",
        "配菜饮料"
    };
    
    //推荐分类
    public final static String[] RECOMMAND_TYPE = {
        "不推荐",
        "新品菜",
        "精品菜"
    };
    
    //支付类型
    public final static int COD = 0;
    public final static int WX_ONLINE = 1;
    public final static int USER_MONEY = 2;
    public final static String[] PAY_TYPE = {
        "货到付款",
        "微信支付",
        "储值扣费"
    };
    
    //管理员权限控制
    public final static int ENGINEER = 3;
    public final static int BOSS = 2;
    public final static int MANAGER = 1;
    public final static int WORKER = 0;
    public final static String[] ROLE = {
        "员工",
        "店长",
        "老板",
        "开发者"
    };
    
    //取消订单的原因
    public final static String MANAGER_CANCEL = "店长手动取消";
    public final static String USER_CANCEL = "用户手动取消";
    public final static String WEB_CANCEL = "管理后台取消";
    public final static String NOTPAY_CANCEL = "超过付款时间";
    public final static String NEXTDAY_CANCEL = "隔天订单";
    
    //云之讯短信相关配置
    public final static String M_ACCOUNT_SID = "5508a1f4f6a88e109d1108aac59aee94";
    public final static String M_AUTH_TOKEN = "95cb83c0e1fd320414d61bd904702c83";
    public final static String M_APP_ID = "096251fe6fef425bbf434749bdc049d1";
    public final static String M_TEMP_ID = "6059";
    public final static String M_URL = "https://api.ucpaas.com/2014-06-30/Accounts/"+M_ACCOUNT_SID+"/Messages/templateSMS?sig=";

    
    //友盟消息推送相关配置
    public final static String U_APP_KEY = "556038cf67e58ee8fc00848b";
    public final static String U_APP_MASTER_SECRET = "wtdbgrm6xulq8s5ygja8vnvmsex1eznl";
    public final static String U_URL = "http://msg.umeng.com/api/send";
    
    //微信相关帐号
    public final static String W_APP_ID = "wxba383cd56b8e9f2e";
    public final static String W_APP_SECRET = "44350fdd1027a55d937b2fc7a96bd289";
    public final static String W_MCH_ID = "1243220002";
    public final static String W_API_KEY = "1a2b3c4d1a2b3c4d1a2b3c4d1a2b3c4d";
    public final static String W_UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public final static String W_CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
    public final static String W_PAY_NOTIFY_URL = "http://123.57.249.80:8080/beehive/microsite/order/pay";
    public final static String W_USER_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public final static String W_GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    public final static String W_GET_JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
    public static int W_EXPIRE_IN = 7000;
    
    //微信随机数生成算法
    public static final String wxRandomString() {
        String randomCode = generateRandomCode(10);
        return Encrypt.MD5(randomCode);
    }
    
    //微信签名生成算法
    public static final String wxSign(Map<String, Object> map) {
        //将参数按字典序排序
        List<String> list = new ArrayList<String>(map.keySet());
        Collections.sort(list);
        //拼接StringA
        StringBuilder stringA = new StringBuilder();
        for(int i = 0 ; i < list.size() ; i++) {
            String key = list.get(i);
            stringA.append(key);
            stringA.append('=');
            stringA.append(map.get(key));
            stringA.append('&');
        }
        //拼接key并输出MD5值
        stringA.append("key=");
        stringA.append(Constant.W_API_KEY);
        return Encrypt.MD5(stringA.toString()).toUpperCase();
    }
    
    //微信js签名生成算法
    public static final String wxJsSign(long timestamp, String nonceStr, String url) {
        String jsapiTicket = Constant.wxGetJsapiTicket();
        if(jsapiTicket == null){
            return null;
        }
        String str = "jsapi_ticket="+jsapiTicket+"&noncestr="+nonceStr+"&timestamp="+timestamp+"&url="+url;
        return Encrypt.SHA1(str);
    }
    
    //获取微信accessToken和JsapiTicket缓存
    public static final String wxGetAccessToken() {
        Cache cache = Cache.getInstance();
        long now = new Date().getTime()/1000;
        if(!cache.exist("wxAccessToken") || 
            now - (long)cache.get("wxAccessTokenTime") > Constant.W_EXPIRE_IN) {
            String url = Constant.W_GET_ACCESS_TOKEN_URL+"?grant_type=client_credential&appid="+Constant.W_APP_ID+"&secret="+Constant.W_APP_SECRET;
            String ret = Web.sendGet(url);
            JSONObject json = JSONObject.fromObject(ret);
            if(json.containsKey("errcode") && json.getInt("errcode") != 0) {
                return null;
            }
            cache.set("wxAccessToken", json.getString("access_token"));
            cache.set("wxAccessTokenTime", now);
            Constant.W_EXPIRE_IN = json.getInt("expires_in") - 200;
        }
        return (String)cache.get("wxAccessToken");
    }
    public static final String wxGetJsapiTicket() {
        Cache cache = Cache.getInstance();
        long now = new Date().getTime()/1000;
        if(!cache.exist("wxJsapiTicket") ||
            now - (long)cache.get("wxJsapiTicketTime") > Constant.W_EXPIRE_IN) {
            String accessToken = Constant.wxGetAccessToken();
            if(accessToken == null) {
                return null;
            }
            String url = Constant.W_GET_JSAPI_TICKET+"?access_token="+accessToken+"&type=wx_card";
            String ret = Web.sendGet(url);
            JSONObject json = JSONObject.fromObject(ret);
            if(json.containsKey("errcode") && json.getInt("errcode") != 0) {
                return null;
            }
            cache.set("wxJsapiTicket", json.getString("ticket"));
            cache.set("wxJsapiTicketTime", now);
            Constant.W_EXPIRE_IN = json.getInt("expires_in") - 200;
        }
        return (String)cache.get("wxJsapiTicket");
    }
    
    //生成指定位数的随机纯数字码
    public static final String generateRandomCode(int digit) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < digit;i++) {
            long num = Math.round(Math.random() * 1000) % 10;
            sb.append(num);
        }
        return sb.toString();
    }
    
    //图片保存地址
    public static String APP_PATH = "";
    public final static String PICTURE_PATH = "pictures";
    public final static String[] LEGAL_SUFFIX = {
        "jpg","jpeg","bmp","png","gif"
    };
    
    //菜品是否上架
    public final static boolean ON_SHOW = true;
    public final static boolean OFF_SHOW = false;
    
    //约定字符串
    public final static String APP_KEY = "*#b3Eh1V3_9r3At#*";
}
