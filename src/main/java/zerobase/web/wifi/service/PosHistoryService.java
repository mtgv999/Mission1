package zerobase.web.wifi.service;


import zerobase.web.wifi.dto.PosHistoryDto;
import zerobase.web.wifi.dto.WifiInfoDto;
import zerobase.web.wifi.model.PosHistoryModel;
import zerobase.web.wifi.model.WifiInfoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PosHistoryService extends SqliteConnection {

	public boolean add(PosHistoryModel model) {

		boolean result = false;

		Connection connection = getConnect();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			String sql = " insert into POS_HISTORY " +
				"(" +
				"LAT, " +
				"LNT, " +
				"REG_DT " +

				") " + "values (?, ?, ?) ";

			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String regDt = LocalDateTime.now().format(dateTimeFormatter);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, model.getLat());
			preparedStatement.setDouble(2, model.getLnt());
			preparedStatement.setString(3, regDt);
			int affected = preparedStatement.executeUpdate();

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


	public List<PosHistoryDto> getList() {

		List<PosHistoryDto> result = new ArrayList<>();

		String COLUMNS = " ID, LAT, LNT, REG_DT ";

		Connection connection = getConnect();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			String sql = "select " + COLUMNS + " from POS_HISTORY " +
				" order by ID desc ";

			preparedStatement = connection.prepareStatement(sql);

			System.out.println("########## - SQL START");
			System.out.println(sql);
			System.out.println("########## - SQL END");

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PosHistoryDto x = new PosHistoryDto(rs);
				result.add(x);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rs, preparedStatement, connection);
		}

		return result;
	}

}
