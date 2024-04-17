package zerobase.web.wifi.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@NoArgsConstructor
@ToString @Data
public class BaseDto {
	protected static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	protected static LocalDateTime getStringToDate(ResultSet rs, String columnId) throws SQLException {
		String regDt = rs.getString(columnId);
		try {return LocalDateTime.parse(regDt, dateTimeFormatter);
		} catch (Exception e) {
		}return null;}
	public static String getNowDateString() {
		return LocalDateTime.now().format(dateTimeFormatter);}}