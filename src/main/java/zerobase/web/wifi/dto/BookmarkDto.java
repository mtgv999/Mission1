package zerobase.web.wifi.dto;
import lombok.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class BookmarkDto extends BaseDto {
    int id;
    String bookmarkGroupName;
    String xSwifiMgrNo;
    String xSwifiMainNm;
    LocalDateTime regDt;
    public static BookmarkDto of(ResultSet rs) throws SQLException {
        return BookmarkDto.builder()
                .id(rs.getInt("ID"))
                .bookmarkGroupName(rs.getString("BOOKMARK_GROUP_NAME"))
                .xSwifiMgrNo(rs.getString("X_SWIFI_MGR_NO"))
                .xSwifiMainNm(rs.getString("X_SWIFI_MAIN_NM"))
                .regDt(getStringToDate(rs, "REG_DT")).build();}}