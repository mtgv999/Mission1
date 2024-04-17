package zerobase.web.wifi.dto;
import com.google.gson.JsonObject;
import lombok.*;
import java.sql.ResultSet;
import java.sql.SQLException;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class WifiInfoDto {
    private String mgrNo;
    private String wrdofc;
    private String mainNm;
    private String adres1;
    private String adres2;
    private String instlFloor;
    private String instlTy;
    private String instlMby;
    private String svcSe;
    private String cmcwr;
    private int cnstcYear;
    private String inoutDoor;
    private String remars3;
    private double lat;
    private double lnt;
    private String workDttm;
    private double distance;
    public WifiInfoDto(JsonObject x) {
        this.mgrNo = x.get("X_SWIFI_MGR_NO").getAsString();
        this.wrdofc = x.get("X_SWIFI_WRDOFC").getAsString();
        this.mainNm = x.get("X_SWIFI_MAIN_NM").getAsString();
        this.adres1 = x.get("X_SWIFI_ADRES1").getAsString();
        this.adres2 = x.get("X_SWIFI_ADRES2").getAsString();
        this.instlFloor = x.get("X_SWIFI_INSTL_FLOOR").getAsString();
        this.instlTy = x.get("X_SWIFI_INSTL_TY").getAsString();
        this.instlMby = x.get("X_SWIFI_INSTL_MBY").getAsString();
        this.svcSe = x.get("X_SWIFI_SVC_SE").getAsString();
        this.cmcwr = x.get("X_SWIFI_CMCWR").getAsString();
        this.cnstcYear = 0;
        try {this.cnstcYear = x.get("X_SWIFI_CNSTC_YEAR").getAsInt();
        } catch (Exception e) {}
        this.inoutDoor = x.get("X_SWIFI_INOUT_DOOR").getAsString();
        this.remars3 = x.get("X_SWIFI_REMARS3").getAsString();
        this.lat = x.get("LAT").getAsDouble();
        this.lnt = x.get("LNT").getAsDouble();
        this.workDttm = x.get("WORK_DTTM").getAsString();}
    public static WifiInfoDto of(ResultSet rs) throws SQLException {

        return WifiInfoDto.builder()
                .mgrNo(rs.getString("X_SWIFI_MGR_NO"))
                .wrdofc(rs.getString("X_SWIFI_WRDOFC"))
                .mainNm(rs.getString("X_SWIFI_MAIN_NM"))
                .adres1(rs.getString("X_SWIFI_ADRES1"))
                .adres2(rs.getString("X_SWIFI_ADRES2"))
                .instlFloor(rs.getString("X_SWIFI_INSTL_FLOOR"))
                .instlTy(rs.getString("X_SWIFI_INSTL_TY"))
                .instlMby(rs.getString("X_SWIFI_INSTL_MBY"))
                .svcSe(rs.getString("X_SWIFI_SVC_SE"))
                .cmcwr(rs.getString("X_SWIFI_CMCWR"))
                .cnstcYear(rs.getInt("X_SWIFI_CNSTC_YEAR"))
                .inoutDoor(rs.getString("X_SWIFI_INOUT_DOOR"))
                .remars3(rs.getString("X_SWIFI_REMARS3"))
                .lat(rs.getDouble("LAT"))
                .lnt(rs.getDouble("LNT"))
                .workDttm(rs.getString("WORK_DTTM"))
                .distance(rs.getDouble("DISTANCE")).build();}
    public String getDistanceText() {
        return String.format("%.4f", distance);}}