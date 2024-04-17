package zerobase.web.wifi.dto;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@NoArgsConstructor
@ToString
@Data
public class PosHistoryDto {
	int id;
	double lat;
	double lnt;
	LocalDateTime regDt;
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public PosHistoryDto(ResultSet rs) throws SQLException {
		this.id = rs.getInt("ID");
		this.lat = rs.getDouble("LAT");
		this.lnt = rs.getDouble("LNT");
		String regDt = rs.getString("REG_DT");
		this.regDt = LocalDateTime.parse(regDt, dateTimeFormatter);}}