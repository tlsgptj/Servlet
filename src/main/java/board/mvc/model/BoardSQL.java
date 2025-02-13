package board.mvc.model;

class BoardSQL {
	final static String LIST = "select * from Board order by SEQ desc";
	final static String INSERT = "INSERT INTO BOARD(WRITER, EMAIL, SUBJECT, CONTENT, RDATE) VALUES(?,?,?,?,now())";
	final static String DELETE = "delete from Board where SEQ=?";
	final static String UPDATE = "UPDATE BOARD SET WRITER=?, EMAIL=?, SUBJECT=?, CONTENT=? WHERE SEQ=?";
	final static String GETBOARD = "SELECT * FROM BOARD WHERE SEQ=?";
}
