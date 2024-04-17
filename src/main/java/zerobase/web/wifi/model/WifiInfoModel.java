package zerobase.web.wifi.model;
import lombok.*;
import javax.servlet.http.HttpServletRequest;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class WifiInfoModel {
	private double lat;
	private double lnt;
	public static WifiInfoModel getParameter(HttpServletRequest request) {
		double lat = 0;
		double lnt = 0;
		try {lat = Double.parseDouble(request.getParameter("lat"));
		} catch (Exception e) {}
		try {lnt = Double.parseDouble(request.getParameter("lnt"));
		} catch (Exception e) {}
		return WifiInfoModel.builder()
			.lat(lat)
			.lnt(lnt)
			.build();}
	public boolean isEmpty() {
		return !(lat > 0 && lnt > 0);}}