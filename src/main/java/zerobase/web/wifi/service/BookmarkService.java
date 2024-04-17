package zerobase.web.wifi.service;


import zerobase.web.wifi.dto.BaseDto;
import zerobase.web.wifi.dto.BookmarkDto;
import zerobase.web.wifi.dto.BookmarkGroupDto;
import zerobase.web.wifi.model.BookmarkGroupModel;
import zerobase.web.wifi.model.BookmarkModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkService extends SqliteConnection {


    public boolean addGroup(BookmarkGroupModel model) {

        boolean result = false;

        Connection connection = getConnect();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            String sql = " insert into BOOK_MARK_GROUP " +
                    "(" +
                    "NAME, " +
                    "SORT_VALUE, " +
                    "REG_DT " +
                    ") " + "values (?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setInt(2, model.getSortValue());
            preparedStatement.setString(3, BaseDto.getNowDateString());
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

    public List<BookmarkGroupDto> getsGroup() {

        Connection connection = getConnect();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        List<BookmarkGroupDto> result = new ArrayList<>();

        String COLUMNS = " ID, NAME, SORT_VALUE, REG_DT, UDT_DT ";

        try {
            String sql = "select " + COLUMNS +
                    " from BOOK_MARK_GROUP " +
                    " order by SORT_VALUE, NAME ";

            preparedStatement = connection.prepareStatement(sql);

            System.out.println("########## - SQL START");
            System.out.println(sql);
            System.out.println("########## - SQL END");

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                result.add(BookmarkGroupDto.of(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(rs, preparedStatement, connection);
        }

        return result;
    }


    public BookmarkGroupDto getGroup(int id) {

        Connection connection = getConnect();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        BookmarkGroupDto result = null;

        String COLUMNS = " ID, NAME, SORT_VALUE, REG_DT, UDT_DT  ";

        try {
            String sql = "select " + COLUMNS +
                    " from BOOK_MARK_GROUP " +
                    " where id = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            System.out.println("########## - SQL START");
            System.out.println(sql);
            System.out.println(id);
            System.out.println("########## - SQL END");

            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                result = BookmarkGroupDto.of(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(rs, preparedStatement, connection);
        }

        return result;
    }

    public boolean editGroup(BookmarkGroupModel model) {

        boolean result = false;

        Connection connection = getConnect();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            String sql = " update BOOK_MARK_GROUP " +
                    " set " +
                    " NAME = ?, SORT_VALUE = ?, UDT_DT = ? " +
                    " where ID = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setInt(2, model.getSortValue());
            preparedStatement.setString(3, BaseDto.getNowDateString());
            preparedStatement.setInt(4, model.getId());
            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println(" 수정 성공 ");
            } else {
                System.out.println(" 수정 실패 ");
            }

            result = true;

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(rs, preparedStatement, connection);
        }

        return result;

    }

    public boolean deleteGroup(BookmarkGroupModel model) {

        boolean result = false;

        Connection connection = getConnect();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            String sql = " delete from BOOK_MARK_GROUP " +
                    " where ID = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, model.getId());
            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println(" 삭제 성공 ");
            } else {
                System.out.println(" 삭제 실패 ");
            }

            result = true;

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(rs, preparedStatement, connection);
        }

        return result;


    }

    public boolean add(BookmarkModel model) {

        boolean result = false;

        Connection connection = getConnect();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            String sql = " insert into BOOK_MARK " +
                    "(" +
                    "X_SWIFI_MGR_NO, " +
                    "BOOK_MARK_GROUP_ID, " +
                    "REG_DT " +
                    ") " + "values (?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getMgrNo());
            preparedStatement.setInt(2, model.getBookMarkGroupId());
            preparedStatement.setString(3, BaseDto.getNowDateString());
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

    public List<BookmarkDto> gets() {

        Connection connection = getConnect();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        List<BookmarkDto> result = new ArrayList<>();

        try {
            String sql = "" +
                    " select bm.ID " +
                    "   , bmg.NAME as BOOKMARK_GROUP_NAME " +
                    "   , bm.X_SWIFI_MGR_NO " +
                    "   , wi.X_SWIFI_MAIN_NM " +
                    "   , bm.REG_DT " +
                    " from BOOK_MARK bm " +
                    "   join BOOK_MARK_GROUP bmg on bmg.ID = bm.BOOK_MARK_GROUP_ID " +
                    "   join WIFI_INFO wi on wi.X_SWIFI_MGR_NO = bm.X_SWIFI_MGR_NO " +
                    "";

            preparedStatement = connection.prepareStatement(sql);

            System.out.println("########## - SQL START");
            System.out.println(sql);
            System.out.println("########## - SQL END");

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                result.add(BookmarkDto.of(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(rs, preparedStatement, connection);
        }

        return result;
    }

    public BookmarkDto get(int id) {

        Connection connection = getConnect();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        BookmarkDto result = null;

        try {
            String sql = "" +
                    " select bm.ID " +
                    "   , bmg.NAME as BOOKMARK_GROUP_NAME " +
                    "   , bm.X_SWIFI_MGR_NO " +
                    "   , wi.X_SWIFI_MAIN_NM " +
                    "   , bm.REG_DT " +
                    " from BOOK_MARK bm " +
                    "   join BOOK_MARK_GROUP bmg on bmg.ID = bm.BOOK_MARK_GROUP_ID " +
                    "   join WIFI_INFO wi on wi.X_SWIFI_MGR_NO = bm.X_SWIFI_MGR_NO " +
                    " where bm.ID = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            System.out.println("########## - SQL START");
            System.out.println(sql);
            System.out.println("########## - SQL END");

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                result = BookmarkDto.of(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(rs, preparedStatement, connection);
        }

        return result;

    }

    public boolean delete(BookmarkModel model) {


        boolean result = false;

        Connection connection = getConnect();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            String sql = " delete from BOOK_MARK where ID = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, model.getId());
            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println(" 삭제 성공 ");
            } else {
                System.out.println(" 삭제 실패 ");
            }

            result = true;

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(rs, preparedStatement, connection);
        }

        return result;


    }
}
