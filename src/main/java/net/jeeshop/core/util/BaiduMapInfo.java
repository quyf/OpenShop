package net.jeeshop.core.util;

import java.io.Serializable;

//http://developer.baidu.com/map/webservice-geocoding.htm
public class BaiduMapInfo implements Serializable {
	private static final long serialVersionUID = -7543113695889545864L;
	
	int status;
	Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
