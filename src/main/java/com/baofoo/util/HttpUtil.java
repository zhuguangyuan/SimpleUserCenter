/**
 * @author Administrator
 * @author Administrator
 */
/**
 * @author Administrator
 *
 */
package com.baofoo.util;

import com.baofoo.http.*;
import com.baofoo.rsa.RsaCodingUtil;
import com.baofoo.rsa.SignatureUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpUtil {
    public static Map<String, String> rspValidateCheck(String rspStr, String cerpath, String pfxpath, String pfxpwd) throws Exception {
        Map<String, String> rspDataMap = FormatUtil.getParm(rspStr);
        log.warn("rspDataMap:{}", rspDataMap);

        if (rspDataMap.isEmpty()) {
            throw new Exception("返回结果为空");
        }

        if (!rspDataMap.containsKey("signature")) {
            throw new Exception("缺少验签参数！");
        }

        String RSign = rspDataMap.get("signature");
        rspDataMap.remove("signature");// 需要删除签名字段
        String RSignature = SecurityUtil.sha1X16(FormatUtil.coverMap2String(rspDataMap), "UTF-8");//签名
        if (!SignatureUtils.verifySignature(cerpath, RSignature, RSign)) {
            throw new Exception("验签失败！");
        }

        if (!rspDataMap.containsKey("resp_code")) {
            throw new Exception("缺少resp_code参数！");
        }

        if (!rspDataMap.get("resp_code").equals("S")) {
            log.warn("rspDataMap resp_code:{}", rspDataMap.get("resp_code"));
        }

        if (rspDataMap.containsKey("dgtl_envlp")) {
            String RDgtlEnvlp = SecurityUtil.Base64Decode(RsaCodingUtil.decryptByPriPfxFile(rspDataMap.get("dgtl_envlp"), pfxpath, pfxpwd));
            Log.Write("响应体中的数字信封：" + RDgtlEnvlp);

            String RAesKey = FormatUtil.getAesKey(RDgtlEnvlp); //信封中的AESKey
            Log.Write("信封中的AESKey:" + RAesKey);

            rspDataMap.put("aes", RAesKey);
        }

        return rspDataMap;
    }

    public static String RequestForm(String Url, Map<String, String> Parms) {
        Log.Write("【请求全部参数】：" + Url + "?" + buildHttpParm(Parms));
        HttpSendModel httpSendModel = new HttpSendModel(Url, Parms);
        httpSendModel.setMethod(HttpMethod.POST);
        SimpleHttpResponse response = null;
        try {
            response = doRequest(httpSendModel, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return response.getEntityString();
    }

    public static SimpleHttpResponse doRequest(HttpSendModel httpSendModel, String getCharSet) throws Exception {
        // 创建默认的httpClient客户端端
        SimpleHttpClient simpleHttpclient = new SimpleHttpClient();
        try {
            return doRequest(simpleHttpclient, httpSendModel, getCharSet);
        } finally {
            simpleHttpclient.getHttpclient().getConnectionManager().shutdown();
        }
    }

    /**
     * @param simpleHttpclient
     * @param httpSendModel
     * @param getCharSet
     * @return
     * @throws Exception
     */
    public static SimpleHttpResponse doRequest(SimpleHttpClient simpleHttpclient,
                                               HttpSendModel httpSendModel,
                                               String getCharSet) throws Exception {

        HttpRequestBase httpRequest = buildHttpRequest(httpSendModel);

        if (httpSendModel.getUrl().startsWith("https://")) {
            simpleHttpclient.enableSSL();
        }

        try {
            HttpResponse response = simpleHttpclient.getHttpclient().execute(
                    httpRequest);

            int statusCode = response.getStatusLine().getStatusCode();
            if (isRequestSuccess(statusCode)) {
                return new SimpleHttpResponse(statusCode, EntityUtils.toString(
                        response.getEntity(), getCharSet), null);
            } else {
                return new SimpleHttpResponse(statusCode, null, response
                        .getStatusLine().getReasonPhrase());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("http请求异常", e);
        }
    }

    /**
     * @param httpSendModel
     * @return
     * @throws Exception
     */
    protected static HttpRequestBase buildHttpRequest(
            HttpSendModel httpSendModel) throws Exception {
        HttpRequestBase httpRequest;
        if (httpSendModel.getMethod() == null) {
            throw new Exception("请求方式未设定");
        } else if (httpSendModel.getMethod() == HttpMethod.POST) {
            String url = httpSendModel.getUrl();
            String sendCharSet = httpSendModel.getCharSet();
            List<HttpFormParameter> params = httpSendModel.getParams();
            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            if (params != null && params.size() != 0) {
                for (HttpFormParameter param : params) {
                    qparams.add(new BasicNameValuePair(param.getName(), param
                            .getValue()));
                }
            }

            HttpPost httppost = new HttpPost(url);
            try {
                UrlEncodedFormEntity Str = new UrlEncodedFormEntity(qparams, sendCharSet);
                Str.setContentType("application/x-www-form-urlencoded");
                httppost.setEntity(Str);
            } catch (UnsupportedEncodingException e) {
                throw new Exception("构建post请求参数失败", e);
            }

            httpRequest = httppost;
        } else if (httpSendModel.getMethod() == HttpMethod.GET) {
            HttpGet httpget = new HttpGet(httpSendModel.buildGetRequestUrl());
            httpRequest = httpget;
        } else {
            throw new Exception("请求方式不支持：" + httpSendModel.getMethod());
        }

        return httpRequest;
    }

    /**
     * 请求是否成功
     *
     * @param statusCode
     * @return
     */
    public static boolean isRequestSuccess(int statusCode) {
        return statusCode == 200;
    }


    public static String buildHttpParm(Map<String, String> Parms) {
        if (Parms.isEmpty()) {
            return "参数不能为空！";
        }
        String PostParms = "";
        int PostItemTotal = Parms.keySet().size();
        int Itemp = 0;
        for (String key : Parms.keySet()) {
            PostParms += key + "=" + Parms.get(key);
            Itemp++;
            if (Itemp < PostItemTotal) {
                PostParms += "&";
            }
        }
        return PostParms;
    }

}