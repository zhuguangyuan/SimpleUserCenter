
package com.baofoo.Demo;

import com.baofoo.rsa.RsaCodingUtil;
import com.baofoo.rsa.SignatureUtils;
import com.baofoo.util.FormatUtil;
import com.baofoo.util.HttpUtil;
import com.baofoo.util.Log;
import com.baofoo.util.SecurityUtil;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
/**
 * 协议支付-直接支付接口
 * Company: www.baofu.com
 * @author dasheng(大圣)
 * @date 2023年02月08日
 */
public class SinglePay{

	public static void main(String[] args) throws Exception {
		String send_time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//报文发送日期时间
		
		String ProtocolNo = "1202306281230074700008242194";//签约协议号（确认绑卡返回）
		
		String MemberId = "100029078";//商户号
		String TerminalId = "200004282";//终端号
		
		String  pfxpath ="D:\\projects\\dev\\bqloan\\src\\main\\resources\\baofoo\\config\\bfkey_100029078@@200004282.pfx";//商户私钥 
		String  cerpath = "D:\\projects\\dev\\bqloan\\src\\main\\resources\\baofoo\\config\\bfkey_100029078@@200004282.cer";//宝付公钥
		String  pfxpwd = "123456";//私钥密码

		String ReturnUrl="";//异步通知接收地址
		String TransId = "TID"+FormatUtil.CreateAeskey(20);//商户订单号

		Log.Write("订单号："+TransId);
		
        String AesKey = FormatUtil.CreateAeskey();//商户自定义(可随机生成  AES key长度为=16位)
		String dgtl_envlp = "01|"+AesKey;//使用接收方的公钥加密后的对称密钥，并做Base64转码，明文01|对称密钥，01代表AES[密码商户自定义]
		Log.Write("密码dgtl_envlp："+dgtl_envlp);	
		dgtl_envlp = RsaCodingUtil.encryptByPubCerFile(SecurityUtil.Base64Encode(dgtl_envlp), cerpath);//公钥加密			
		ProtocolNo = SecurityUtil.AesEncrypt(SecurityUtil.Base64Encode(ProtocolNo), AesKey);//先BASE64后进行AES加密
		Log.Write("签约协议号AES结果:"+ProtocolNo);
		
		String CardInfo="";//信用卡：信用卡有效期|安全码,借记卡：传空
		
		//暂不支持信用卡
		CardInfo = SecurityUtil.AesEncrypt(SecurityUtil.Base64Encode(CardInfo), AesKey);//先BASE64后进行AES加密
		
		
		Map<String,String> DateArry = new TreeMap<String,String>();
		DateArry.put("send_time", send_time);
		DateArry.put("msg_id", "TISN"+System.currentTimeMillis());//报文流水号
		DateArry.put("version", "4.0.0.0");
		DateArry.put("terminal_id", TerminalId);
		DateArry.put("txn_type", "08");//交易类型(参看：文档中《交易类型枚举》)
		DateArry.put("member_id", MemberId);
		DateArry.put("trans_id", TransId);
		DateArry.put("dgtl_envlp", dgtl_envlp);
		DateArry.put("user_id", "");//用户在商户平台唯一ID (和绑卡时要一致)
		DateArry.put("protocol_no", ProtocolNo);//签约协议号（密文）
		DateArry.put("txn_amt", "1");//交易金额 [单位：分  例：1元则提交100]，此处注意数据类型的转转，建议使用BigDecimal类弄进行转换为字串
		DateArry.put("card_info", CardInfo);//卡信息
		
//		DateArry.put("fee_member_id", "");//手续费商户
		
		Map<String,String> RiskItem = new HashMap<String,String>();
		 /**--------风控基础参数-------------**/
		/**
		 * 说明风控参数必须，按商户开通行业、真实交易信息传，不可传固定值。
		 */
		 RiskItem.put("goodsCategory", "06");//商品类目 详见附录《商品类目》
		 RiskItem.put("userLoginId", "bofootest");//用户在商户系统中的登陆名（手机号、邮箱等标识）
		 RiskItem.put("userEmail", "");
		 RiskItem.put("userMobile", "15821798636");//用户手机号		 
		 RiskItem.put("registerUserName", "大圣");//用户在商户系统中注册使用的名字
		 RiskItem.put("identifyState", "1");//用户在平台是否已实名，1：是 ；0：不是
		 RiskItem.put("userIdNo", "341182197807131732");//用户身份证号		 
		 RiskItem.put("registerTime", "20170223113233");//格式为：YYYYMMDDHHMMSS
		 RiskItem.put("registerIp", "10.0.0.0");//用户在商户端注册时留存的IP
		 RiskItem.put("chName", "10.0.0.0");//持卡人姓名		 
		 RiskItem.put("chIdNo", "");//持卡人身份证号
		 RiskItem.put("chCardNo", "");//持卡人银行卡号
		 RiskItem.put("chMobile", "");//持卡人手机
		 RiskItem.put("chPayIp", "116.216.217.170");//持卡人支付IP
		 RiskItem.put("deviceOrderNo", "");//加载设备指纹中的订单号
		 
		 
		 /**--------行业参数  (以下为游戏行业风控参，请参看接口文档附录风控参数)-------------**/
		 RiskItem.put("gameName", "15821798636");//充值游戏名称
		 RiskItem.put("userAcctId", "15821798636");//游戏账户ID 
		 RiskItem.put("rechargeType", "0");//充值类型 (0:为本账户充值或支付、1:为他人账户充值或支付； 默认为 0)
		 RiskItem.put("gameProdType", "02");//01：点券类 、 02：金币类 、 03：装备道具类 、 04：其他
		 RiskItem.put("gameAcctId", "");//被充值游戏账户ID,若充值类型为1 则填写
		 RiskItem.put("gameLoginTime", "20");//游戏登录次数，累计最近一个月
		 RiskItem.put("gameOnlineTime", "100");//游戏在线时长，累计最近一个月
		 
		 DateArry.put("risk_item",JSONObject.fromObject(RiskItem).toString());//放入风控参数		 
		 DateArry.put("return_url", ReturnUrl);//最多填写三个地址,不同地址用间使用‘|’分隔
		 
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
			 Log.Write("验签：Yes");//验签成功
		 
			 if(!ReturnData.containsKey("resp_code")){
				 throw new Exception("缺少resp_code参数！");
			 }
			 if(ReturnData.get("resp_code").toString().equals("S")){
				 Log.Write("支付成功！");
			 }else if(ReturnData.get("resp_code").toString().equals("I")){	
				Log.Write("处理中！");
			 }else if(ReturnData.get("resp_code").toString().equals("F")){
				Log.Write("失败！");
			 }else{
				throw new Exception("反回异常！");//异常不得做为订单状态。
			 }
		 }else {
			 Log.Write("验签：NO");
		 }
		
	}
}