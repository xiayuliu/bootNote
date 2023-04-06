package com.xia.contoller;

import com.xia.service.UsersService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@Controller
public class UsersController {


    @Resource
    private UsersService usersService;


    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/payment")
    public String toPayment(){
        return "payment";
    }


    @RequestMapping("/loginController")
    @ResponseBody
    public JSONObject login(String loginName, String Password){
        System.out.println(loginName+" "+Password);

        String token= usersService.loginVerify(loginName, Password);
        JSONObject jsonObject= new JSONObject();
        if (token==null) {
            jsonObject.put("value", "账号密码有误");
            return jsonObject;
        }else {
            jsonObject.put("loginName",loginName);
            jsonObject.put("token",token);
            jsonObject.put("value","登录成功");
        }
        return jsonObject;
    }

    @RequestMapping("/enrollController")
    @ResponseBody
    public JSONObject enroll(String userName,String password){
        System.out.println(userName);
        JSONObject jsonObject= new JSONObject();
        int salt = usersService.salt(userName, password);
        if (salt>0){
            jsonObject.put("value","注册成功");
        }
        return jsonObject;
    }

    /*@PostMapping("/generate-alipay-qrcode")
    public PayResponse generateAlipayQrCode(String outTradeNo, double totalAmount) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOutTradeNo(outTradeNo);
        payRequest.setTotalAmount(totalAmount);
        payRequest.setSubject("测试订单");
        return alipayService.pay(payRequest);
    }*/

}
