package zerobase.web.wifi.utils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import zerobase.web.wifi.dto.WifiInfoDto;
import java.util.ArrayList;
import java.util.List;
public class WifiUtils {
	public static List<WifiInfoDto> stringToDto(String jsonString) {
		List<WifiInfoDto> wifiInfoDtoList = new ArrayList<>();
		JsonElement jsonElement = JsonParser.parseString(jsonString);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		JsonObject TbPublicWifiInfo = jsonObject.getAsJsonObject("TbPublicWifiInfo");
		int list_total_count = TbPublicWifiInfo.get("list_total_count").getAsInt();
		String CODE = TbPublicWifiInfo.getAsJsonObject("RESULT").get("CODE").getAsString();
		String MESSAGE = TbPublicWifiInfo.getAsJsonObject("RESULT").get("MESSAGE").getAsString();
		System.out.println(list_total_count);
		System.out.println(CODE);
		System.out.println(MESSAGE);
		JsonArray row = TbPublicWifiInfo.getAsJsonArray("row");
		if (row != null) {
			row.forEach(e -> {
				JsonObject x = e.getAsJsonObject();
				wifiInfoDtoList.add(new WifiInfoDto(x));});}
		return wifiInfoDtoList;}}