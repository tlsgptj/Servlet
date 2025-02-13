package addr.mv.model;

import javax.naming.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
//자바 빈
public class BoardDAO {
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

    // ✅ 게시글 목록 조회
    public ArrayList<BoardDTO> list() {
        ArrayList<BoardDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM BOARD ORDER BY SEQ DESC";

        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                long seq = rs.getLong("SEQ");
                String writer = rs.getString("WRITER");
                String email = rs.getString("EMAIL");
                String subject = rs.getString("SUBJECT");
                String content = rs.getString("CONTENT");
                Date rdate = rs.getDate("RDATE");

                list.add(new BoardDTO(seq, writer, email, subject, content, rdate));
            }
        } catch (SQLException se) {
            System.out.println("list se: " + se);
        }
        return list;
    }

    // ✅ 특정 게시글 조회 (게시글 수정 시 사용)
    public BoardDTO get(long seq) {
        BoardDTO dto = null;
        String sql = "SELECT * FROM BOARD WHERE SEQ=?";

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

                    dto = new BoardDTO(seq, writer, email, subject, content, rdate);
                }
            }
        } catch (SQLException se) {
            System.out.println("get se: " + se);
        }
        return dto;
    }

    // ✅ 게시글 삽입
    public boolean insert(BoardDTO dto) {
        String sql = "INSERT INTO BOARD(WRITER, EMAIL, SUBJECT, CONTENT, RDATE) VALUES(?,?,?,?,now())";
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

    // ✅ 게시글 수정 (UPDATE)
    public boolean update(BoardDTO dto) {
        String sql = "UPDATE BOARD SET WRITER=?, EMAIL=?, SUBJECT=?, CONTENT=? WHERE SEQ=?";
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

    // ✅ 게시글 삭제 (DELETE)
    public boolean delete(long seq) {
        String sql = "DELETE FROM BOARD WHERE SEQ=?";
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setLong(1, seq);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException se) {
            System.out.println("delete se: " + se);
            return false;
        }
    }
}
