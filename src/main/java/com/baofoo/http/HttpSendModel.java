package com.baofoo.http;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类名称：表单参数
 * 类描述：
 * 创建人：陈少杰
 * 创建时间：2014-10-22 下午2:58:22
 * 修改人：大圣（dasheng@baofu.com）
 * 修改时间：2023-08-18 下午09:46:22
 * @version
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HttpSendModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String url;
	private String charSet;
	private HttpMethod method;
	private List<HttpFormParameter> params;

	/**
	 * 
	 */
	public HttpSendModel() {
		charSet = CharSet.Default;
		method = HttpMethod.getDefault();
	}

	/**
	 * @param url
	 */
	public HttpSendModel(String url) {
		super();
		charSet = CharSet.Default;
		method = HttpMethod.getDefault();

		int index = url.indexOf("?");
		if (index != -1) {
			this.url = url.substring(0, index);
			String paramsString = url.substring(index + 1);
			buildParams(paramsString);
		} else {
			this.url = url;
		}
	}
	
	public HttpSendModel(String url,Map<String,String> Parms) {
		charSet = CharSet.Default;
		method = HttpMethod.getDefault();
		this.url = url;
		buildParams(Parms);
	}
	
	
	

	public HttpSendModel(String url, String urlPath) throws Exception {
		super();
		charSet = CharSet.Default;
		method = HttpMethod.getDefault();

		if (StringUtils.isBlank(urlPath)) {
			this.url = url;
		} else {
			if (!url.startsWith(urlPath)) {
				throw new Exception("地址不匹配");
			}

			if (url.length() == urlPath.length()) {
				this.url = url;
				return;
			}

			if (url.charAt(urlPath.length()) != '?') {
				throw new Exception("地址不匹配");
			}

			this.url = urlPath;
			String paramsString = url.substring(urlPath.length() + 1);

			buildParams(paramsString);
		}
	}

	/**
	 * @param paramsString
	 */
	private void buildParams(String paramsString) {
		if (StringUtils.isBlank(paramsString)) {
			return;
		}

		params = new ArrayList<HttpFormParameter>();
		String[] keyValue;

		String[] theParams = paramsString.split("&");
		String key;
		String value;
		for (String p : theParams) {
			keyValue = p.split("=", 2);
			key = keyValue[0];
			if (keyValue.length < 2) {
				value = "";
			} else {
				value = keyValue[1];
			}
			params.add(new HttpFormParameter(key, value));
		}
	}
	
	
	private void buildParams(Map<String,String> Parms) {
		if (null == Parms || Parms.isEmpty()) return;
		Map<String,String> TempParms = Parms;
		params = new ArrayList<HttpFormParameter>();		
		for (String key : TempParms.keySet()){
			if(!StringUtils.isBlank(TempParms.get(key))) {
				params.add(new HttpFormParameter(key, TempParms.get(key)));				
			}else {
				params.add(new HttpFormParameter(key, ""));
			}			
		}
	}
	
	
	/**
	 * 生成get模式的请求地址
	 * 
	 * @return
	 */
	public String buildGetRequestUrl() {

		String url = this.getUrl();
		String charSet = this.getCharSet();
		List<HttpFormParameter> params = this.getParams();

		String requestUrl = url;
		if (params != null && params.size() != 0) {

			List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			for (HttpFormParameter param : params) {
				qparams.add(new BasicNameValuePair(param.getName(), param
						.getValue() == null ? "" : param.getValue()));
			}
			String appender = URLEncodedUtils.format(qparams, charSet);
			// 用于url不存在参数的情况
			if (url.indexOf("?") == -1) {
				requestUrl += "?" + appender;
			}
			// 用于url已存在参数的情况
			else {
				requestUrl += "&" + appender;
			}

		}

		return requestUrl;
	}

	/**
	 * @param formName
	 * @return
	 */
	public String buildPostRequestForm(String formName) {
		String url = this.getUrl();
		String charSet = this.getCharSet();
		List<HttpFormParameter> params = this.getParams();

		StringBuilder buffer = new StringBuilder();

		buffer.append("<SCRIPT LANGUAGE=\"JavaScript\"> var isIE=!!window.ActiveXObject||\"ActiveXObject\" in window; </SCRIPT>");
		buffer.append("<form id=\"")
				.append(formName)
				.append("\" name=\"")
				.append(formName)
				.append("\" action=\"")
				.append(url)
				.append("\" accept-charset=\"")
				.append(charSet)
				.append("\" method=\"" + this.getMethod().value()
						+ "\" onsubmit=\"if(isIE)document.charset='" + charSet
						+ "'\" >\n");

		for (HttpFormParameter param : params) {
			if (param.isHidden()) {
				buffer.append("<input type=\"hidden\" name=\"")
						.append(param.getName()).append("\" value=\"")
						.append(param.getValue()).append("\" />\n");
			} else {
				buffer.append("<input type=\"text\" name=\"")
						.append(param.getName()).append("\" value=\"")
						.append(param.getValue()).append("\" />\n");
			}
		}
		
		buffer.append("<input type=\"submit\" value=\"submit\" />");

		buffer.append("</form>");

		return buffer.toString();
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the charSet
	 */
	public String getCharSet() {
		return charSet;
	}

	/**
	 * @param charSet
	 *            the charSet to set
	 */
	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	/**
	 * @return the method
	 */
	public HttpMethod getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	/**
	 * @return the params
	 */
	public List<HttpFormParameter> getParams() {
		if (params == null) {
			params = new ArrayList<HttpFormParameter>();
		}
		return this.params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(List<HttpFormParameter> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[url]" + this.url);
		if (method != null) {
			sb.append("[method]" + this.method);
		}
		if (params != null && params.size() > 0) {
			sb.append("[params]-");
			for (HttpFormParameter param : params) {
				sb.append("[" + param.getName() + "]" + param.getValue());
			}
		}

		return sb.toString();

	}

}
