package com.baofoo.Demo;

import com.baofoo.rsa.RsaCodingUtil;
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
 * 协议支付-查绑接口
 * Company: www.baofu.com
 * @author dasheng(大圣)
 * @date 2023年02月08日
 */
public class QueryBind{

	public static void main(String[] args) throws Exception {
		String send_time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//报文发送日期时间
		
		String AccNo = "6228480476455953239";//查询银行卡号
		
		String MemberId = "100029078";//商户号
		String TerminalId = "200004282";//终端号
		
		String  pfxpath ="D:\\projects\\dev\\bqloan\\src\\main\\resources\\baofoo\\config\\bfkey_100029078@@200004282.pfx";//商户私钥 
		String  cerpath = "D:\\projects\\dev\\bqloan\\src\\main\\resources\\baofoo\\config\\bfkey_100029078@@200004282.cer";//宝付公钥
		String  pfxpwd = "123456";//私钥密码
        
        String AesKey = FormatUtil.CreateAeskey();////商户自定义（可随机生成  商户自定义(AES key长度为=16位)）
		String dgtl_envlp = "01|"+AesKey;//使用接收方的公钥加密后的对称密钥，并做Base64转码，明文01|对称密钥，01代表AES[密码商户自定义]
		Log.Write("密码dgtl_envlp："+dgtl_envlp);		
		dgtl_envlp = RsaCodingUtil.encryptByPubCerFile(SecurityUtil.Base64Encode(dgtl_envlp), cerpath);//公钥加密
		AccNo = SecurityUtil.AesEncrypt(SecurityUtil.Base64Encode(AccNo), AesKey);//先BASE64后进行AES加密
		Log.Write("银行卡号AES结果:"+AccNo);

		Map<String,String> DateArry = new TreeMap<String,String>();
		DateArry.put("send_time", send_time);
		DateArry.put("msg_id", "TISN"+FormatUtil.CreateAeskey(28));//报文流水号
		DateArry.put("version", "4.0.0.0");
		DateArry.put("terminal_id", TerminalId);
		DateArry.put("txn_type", "03");//交易类型
		DateArry.put("member_id", MemberId);
		DateArry.put("dgtl_envlp", dgtl_envlp);
		DateArry.put("user_id", "");//用户在平台的唯一ID
		DateArry.put("acc_no", AccNo);//银行卡号密文[与user_id必须其中一个有值]
		
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
			if(!ReturnData.containsKey("dgtl_envlp")){
				throw new Exception("缺少dgtl_envlp参数！");
			}
			String RDgtlEnvlp = SecurityUtil.Base64Decode(RsaCodingUtil.decryptByPriPfxFile(ReturnData.get("dgtl_envlp"), pfxpath, pfxpwd));		
			Log.Write("返回数字信封："+RDgtlEnvlp);
			String RAesKey=FormatUtil.getAesKey(RDgtlEnvlp);//获取返回的AESkey
			Log.Write("返回的AESkey:"+RAesKey);
			Log.Write("协议列表:"+SecurityUtil.Base64Decode(SecurityUtil.AesDecrypt(ReturnData.get("protocols"),RAesKey)));
		}
	}
}