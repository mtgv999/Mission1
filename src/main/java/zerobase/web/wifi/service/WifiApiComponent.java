package zerobase.web.wifi.service;


import zerobase.web.wifi.utils.HttpClientUtils;

import java.io.IOException;

public class WifiApiComponent {

	public String getWipiInfoList(int pageIndex) {

		int PAGE_SIZE = 1000;
		int startPage = pageIndex * PAGE_SIZE + 1;
		int endPage = (pageIndex + 1) * PAGE_SIZE;

		String url = String.format("http://openapi.seoul.go.kr:8088/7073676c4a7361743131375672575259/json/TbPublicWifiInfo/%d/%d", startPage, endPage);

		String result = null;

		try {
			result = HttpClientUtils.doGet(url);
			//System.out.println(result);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

}
