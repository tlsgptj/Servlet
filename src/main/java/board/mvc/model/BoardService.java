package board.mvc.model;

import java.util.ArrayList;

import board.domain.Board;

public class BoardService {
	
	private BoardDAO dao;
	
	
	//SingleTon Object Model
	private static final BoardService instance = new BoardService();
	private BoardService() {
		dao = new BoardDAO();
	}
	
	public static BoardService getInstance()	{
		return instance;
	}
	
	//커다란 여러개의 데이터 베이스를 핸들링 
	public ArrayList<Board> listS() {
		//구체적인 데이터베이스 하나를 핸들링
		return dao.list();
	}
	
	public boolean insertS(Board board) {
		return dao.insert(board);
	}
	
	public boolean deleteS(long seq) {
		return dao.delete(seq);
	}
	
	public boolean updateS(Board board) {
		return dao.update(board);
	}
	
	// seq를 기반으로 Board 객체를 조회
    public Board editS(long seq) {
        return dao.get(seq);
    }
}
