package zerobase.web.wifi.service;


import zerobase.web.wifi.model.WifiInfoDetailModel;
import zerobase.web.wifi.model.WifiInfoModel;
import zerobase.web.wifi.utils.WifiUtils;
import zerobase.web.wifi.dto.WifiInfoDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WifiService extends SqliteConnection {

	public boolean add(WifiInfoDto model) {

		boolean result = false;

		Connection connection = getConnect();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			String sql = "delete from WIFI_INFO where X_SWIFI_MGR_NO = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, model.getMgrNo());

			int affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println(" 삭제 성공 ");
			} else {
				System.out.println(" 삭제 실패 ");
			}


			sql = " insert into WIFI_INFO " +
				"(" +
				"X_SWIFI_MGR_NO, " +
				"X_SWIFI_WRDOFC, " +
				"X_SWIFI_MAIN_NM, " +
				"X_SWIFI_ADRES1, " +
				"X_SWIFI_ADRES2, " +

				"X_SWIFI_INSTL_FLOOR, " +
				"X_SWIFI_INSTL_TY, " +
				"X_SWIFI_INSTL_MBY, " +
				"X_SWIFI_SVC_SE, " +
				"X_SWIFI_CMCWR, " +

				"X_SWIFI_CNSTC_YEAR, " +
				"X_SWIFI_INOUT_DOOR, " +
				"X_SWIFI_REMARS3, " +
				"LAT, " +
				"LNT, " +

				"WORK_DTTM " +

				") " + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, model.getMgrNo());
			preparedStatement.setString(2, model.getWrdofc());
			preparedStatement.setString(3, model.getMainNm());
			preparedStatement.setString(4, model.getAdres1());
			preparedStatement.setString(5, model.getAdres2());

			preparedStatement.setString(6, model.getInstlFloor());
			preparedStatement.setString(7, model.getInstlTy());
			preparedStatement.setString(8, model.getInstlMby());
			preparedStatement.setString(9, model.getSvcSe());
			preparedStatement.setString(10, model.getCmcwr());

			preparedStatement.setInt(11, model.getCnstcYear());
			preparedStatement.setString(12, model.getInoutDoor());
			preparedStatement.setString(13, model.getRemars3());

			preparedStatement.setDouble(15, model.getLat());
			preparedStatement.setDouble(14, model.getLnt());

			preparedStatement.setString(16, model.getWorkDttm());

			affected = preparedStatement.executeUpdate();

			if (affected > 0) {
				System.out.println(" 저장 성공 ");
			} else {
				System.out.println(" 저장 실패 ");
			}

			result = true;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rs, preparedStatement, connection);
		}

		return result;
	}

	public List<WifiInfoDto> getList(WifiInfoModel parameter) {

		List<WifiInfoDto> result = new ArrayList<>();

		String COLUMNS = " X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2 " +
			", X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR " +
			", X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT " +
			", WORK_DTTM ";

		String DISTANCE_COLUM = " (6371 * acos( cos( radians(LAT) ) * cos( radians(CUR_LAT) ) " +
			" * cos( radians(CUR_LNT) - radians(LNT) ) " +
			" + sin( radians(LAT) ) * sin( radians( CUR_LAT ) ) ) ) " +
			" AS DISTANCE ";

		Connection connection = getConnect();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			String sql = "select TBL1.*, " + DISTANCE_COLUM +
				" from (select " + COLUMNS + ", ? as CUR_LAT, ? as CUR_LNT from WIFI_INFO) TBL1 " +
				" order by DISTANCE " +
				" limit 20 ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, parameter.getLat());
			preparedStatement.setDouble(2, parameter.getLnt());

			System.out.println("########## - SQL START");
			System.out.println(parameter);
			System.out.println(sql);
			System.out.println("########## - SQL END");

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				WifiInfoDto x = WifiInfoDto.of(rs);
				result.add(x);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rs, preparedStatement, connection);
		}

		return result;
	}

	public int saveWifiInfo() {

		int resultCount = 0;

		WifiApiComponent wifiApiComponent = new WifiApiComponent();

		int LAST_COUNT = 15;

		for (int i = 0; i < LAST_COUNT; i++) {

			String result = wifiApiComponent.getWipiInfoList(i);
			List<WifiInfoDto> wifiInfoDtoList = WifiUtils.stringToDto(result);

			for (WifiInfoDto x : wifiInfoDtoList) {
				boolean addResult = add(x);
				if (addResult) {
					resultCount++;
				}
			}
		}

		return resultCount;
	}


	public WifiInfoDto getDetail(WifiInfoDetailModel parameter) {

		WifiInfoDto result = new WifiInfoDto();

		String COLUMNS = " X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2 " +
				", X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR " +
				", X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT " +
				", WORK_DTTM ";

		Connection connection = getConnect();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "select TBL1.*, 0 as DISTANCE " +
					" from WIFI_INFO TBL1 " +
					" where X_SWIFI_MGR_NO = ? ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, parameter.getMgrNo());

			System.out.println("########## - SQL START");
			System.out.println(parameter);
			System.out.println(sql);
			System.out.println("########## - SQL END");

			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				result = WifiInfoDto.of(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rs, preparedStatement, connection);
		}

		return result;
	}
}
