package addr.mv.model;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
	
public class AddrDAO {
	private DataSource ds;
	public AddrDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestDB");
			System.out.println("AddrDAO ds: " + ds);
		}catch(NamingException ne){
			System.out.println("DBCP객체(jdbc/TestDB)를 못찾음");
		}
	}
	public ArrayList<AddrDTO> list() {
		ArrayList<AddrDTO> list = new ArrayList<>();
		String sql = "select * from ADDRESS order by SEQ desc";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				long seq = rs.getLong(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				Date rdate = rs.getDate(4);
				
				list.add(new AddrDTO(seq, name, addr, rdate));
			}
			return list;
		}catch(SQLException se){
			System.out.println("list se: "+ se);
			return null;
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException se){}
		}
	}
	public boolean insert(AddrDTO dto) {
		Connection con = null;
		String sql = "insert into ADDRESS(NAME, ADDR, RDATE) values(?,?,now())";
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			int i = pstmt.executeUpdate();
			if(i>0) return true;
			else return false;
		}catch(SQLException se){
			return false;
		}finally{
			try{
				pstmt.close();
				con.close();
			}catch(SQLException se){}
		}
	}
	public boolean delete(long seq) {
		Connection con = null;
		String sql = "delete from ADDRESS where SEQ=?";
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, seq);
			int i = pstmt.executeUpdate();
			if(i>0) return true;
			else return false;
		}catch(SQLException se){
			return false;
		}finally{
			try{
				pstmt.close();
				con.close();
			}catch(SQLException se){}
		}
	}
}