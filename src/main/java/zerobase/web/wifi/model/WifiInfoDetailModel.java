package zerobase.web.wifi.model;
import lombok.*;
import javax.servlet.http.HttpServletRequest;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class WifiInfoDetailModel {
	private String mgrNo;
	public static WifiInfoDetailModel getParameter(HttpServletRequest request) {
		String mgrNo = request.getParameter("mgrNo");
		return WifiInfoDetailModel.builder()
			.mgrNo(mgrNo)
			.build();}}