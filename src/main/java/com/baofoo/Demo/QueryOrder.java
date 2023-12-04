package com.baofoo.Demo;

import com.baofoo.rsa.SignatureUtils;
import com.baofoo.util.FormatUtil;
import com.baofoo.util.HttpUtil;
import com.baofoo.util.Log;
import com.baofoo.util.SecurityUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
/**
 * 交易结果查询
 * Company: www.baofu.com
 * @author dasheng(大圣)
 * @date 2023年02月08日
 */
public class QueryOrder{

	public static void main(String[] args) throws Exception {
		String send_time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//报文发送日期时间
		
		String OTI = "TID1B883169757936B124DC8727B4DA7";//原交易商户订单号
		
		String MemberId = "100029078";//商户号
		String TerminalId = "200004282";//终端号
		
		String  pfxpath ="D:\\projects\\dev\\bqloan\\src\\main\\resources\\baofoo\\config\\bfkey_100029078@@200004282.pfx";//商户私钥 
		String  cerpath = "D:\\projects\\dev\\bqloan\\src\\main\\resources\\baofoo\\config\\bfkey_100029078@@200004282.cer";//宝付公钥
		String  pfxpwd = "123456";//私钥密码
		
		Map<String,String> DateArry = new TreeMap<String,String>();
		DateArry.put("send_time", send_time);
		DateArry.put("msg_id", "TISN"+FormatUtil.CreateAeskey(28));//报文流水号
		DateArry.put("version", "4.0.0.0");
		DateArry.put("terminal_id", TerminalId);
		DateArry.put("txn_type", "07");//交易类型
		DateArry.put("member_id", MemberId);
		DateArry.put("orig_trans_id", OTI);//交易时的trans_id
		DateArry.put("orig_trade_date", send_time);//
		
		String SignVStr = FormatUtil.coverMap2String(DateArry);
		Log.Write("SHA-1摘要字串："+SignVStr);
		String signature = SecurityUtil.sha1X16(SignVStr, "UTF-8");//签名
		Log.Write("SHA-1摘要结果："+signature);		
		String Sign = SignatureUtils.encryptByRSA(signature, pfxpath, pfxpwd);
		Log.Write("RSA签名结果："+Sign);
		DateArry.put("signature", Sign);//签名域
		
		String PostString  = HttpUtil.RequestForm("https://vgw.baofoo.com/cutpayment/protocol/backTransRequest", DateArry);	
		Log.Write("请求返回:"+PostString);
		
		Map<String, String> ReturnData = FormatUtil.getParm(PostString);
		
		if(!ReturnData.containsKey("signature")){
			throw new Exception("缺少验签参数！");
		}
		
		String RSign=ReturnData.get("signature");
		Log.Write("返回的验签值："+RSign);
		ReturnData.remove("signature");//需要删除签名字段		
		String RSignVStr = FormatUtil.coverMap2String(ReturnData);
		Log.Write("返回SHA-1摘要字串："+RSignVStr);
		String RSignature = SecurityUtil.sha1X16(RSignVStr, "UTF-8");//签名
		Log.Write("返回SHA-1摘要结果："+RSignature);
		
		if(SignatureUtils.verifySignature(cerpath,RSignature,RSign)){
			Log.Write("Yes");//验签成功
		}
		if(!ReturnData.containsKey("resp_code")){
			throw new Exception("缺少resp_code参数！");
		}
		if(ReturnData.get("resp_code").toString().equals("S")){	
			Log.Write("交易成功！");
		}else if(ReturnData.get("resp_code").toString().equals("I")){	
			Log.Write("处理中！");
		}else if(ReturnData.get("resp_code").toString().equals("F")){	
			Log.Write("失败！");
		}else{
			throw new Exception("反回异常！");//异常不得做为订单状态。
		}
	}
}