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
public class BookmarkGroupDto extends BaseDto {
    int id;
    String name;
    int sortValue;
    LocalDateTime regDt;
    LocalDateTime udtDt;
    public BookmarkGroupDto(ResultSet rs) throws SQLException {
        this.id = rs.getInt("ID");
        this.name = rs.getString("NAME");
        this.sortValue = rs.getInt("SORT_VALUE");
        this.regDt = getStringToDate(rs, "REG_DT");}
    public static BookmarkGroupDto of(ResultSet rs) throws SQLException {
        return BookmarkGroupDto.builder()
                .id(rs.getInt("ID"))
                .name(rs.getString("NAME"))
                .sortValue(rs.getInt("SORT_VALUE"))
                .regDt(getStringToDate(rs, "REG_DT"))
                .udtDt(getStringToDate(rs, "UDT_DT")).build();}}