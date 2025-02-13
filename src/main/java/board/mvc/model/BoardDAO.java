package board.mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import board.domain.Board;
import static board.mvc.model.BoardSQL.*;

class BoardDAO {
    private DataSource ds;

    public BoardDAO() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/TestDB");
            System.out.println("BoardDAO ds: " + ds);
        } catch (NamingException ne) {
            System.out.println("DBCP 객체(jdbc/TestDB)를 못찾음");
        }
    }

    public Board get(long seq) {
        Board dto = null;
        String sql = GETBOARD;

        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setLong(1, seq);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String writer = rs.getString("WRITER");
                    String email = rs.getString("EMAIL");
                    String subject = rs.getString("SUBJECT");
                    String content = rs.getString("CONTENT");
                    Date rdate = rs.getDate("RDATE");

                    dto = new Board(seq, writer, email, subject, content, rdate);
                }
            }
        } catch (SQLException se) {
            System.out.println("get se: " + se);
        }
        return dto;
    }

    // ✅ 게시글 삽입 (seq 필드 제외)
    public boolean insert(Board dto) {
        String sql = INSERT;
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, dto.getWriter());
            pstmt.setString(2, dto.getEmail());
            pstmt.setString(3, dto.getSubject());
            pstmt.setString(4, dto.getContent());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException se) {
            System.out.println("insert se: " + se);
            return false;
        }
    }

    public boolean update(Board dto) {
        String sql = UPDATE;
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, dto.getWriter());
            pstmt.setString(2, dto.getEmail());
            pstmt.setString(3, dto.getSubject());
            pstmt.setString(4, dto.getContent());
            pstmt.setLong(5, dto.getSeq());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException se) {
            System.out.println("update se: " + se);
            return false;
        }
    }

    public boolean delete(long seq) {
        String sql = DELETE;
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setLong(1, seq);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException se) {
            System.out.println("delete se: " + se);
            return false;
        }
    }

    public ArrayList<Board> list() {
        ArrayList<Board> list = new ArrayList<>();
        String sql = LIST;

        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                long seq = rs.getLong("SEQ");
                String writer = rs.getString("WRITER");
                String email = rs.getString("EMAIL");
                String subject = rs.getString("SUBJECT");
                String content = rs.getString("CONTENT");
                Date rdate = rs.getDate("RDATE");

                list.add(new Board(seq, writer, email, subject, content, rdate));
            }
        } catch (SQLException se) {
            System.out.println("list se: " + se);
        }
        return list;
    }
}
