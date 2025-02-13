package board.mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import board.domain.Board;

import java.io.IOException;
import java.util.ArrayList;

import board.mvc.model.BoardService;

@WebServlet("/board/board.do")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String m = request.getParameter("m");
        if (m != null) {
            m = m.trim();
            switch (m) {
                case "input": input(request, response); break;
                case "insert": insert(request, response); break;
                case "del": del(request, response); break;
                case "get": get(request, response); break;  // 상세 글 보기 및 수정 메소드
                case "update" : update(request, response); break;
            }
        } else {
            list(request, response);
        }
    }
    
    private void list(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        BoardService service = BoardService.getInstance();
        ArrayList<Board> list = service.listS();
        request.setAttribute("list", list);
        
        String view = "list.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }
    
    private void input(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect("input.jsp");
    }
    
    private void insert(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String writer = request.getParameter("writer");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        Board board = new Board(-1L, writer, email, subject, content, null);
        
        BoardService service = BoardService.getInstance();
        boolean flag = service.insertS(board);
        if (flag) {
            response.sendRedirect("board.do");
        } else {
            response.sendRedirect("input.jsp");
        }
    }
    
    private void del(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String strSeq = request.getParameter("seq");
        long seq = Long.parseLong(strSeq);
        
        BoardService service = BoardService.getInstance();
        service.deleteS(seq);
        
        response.sendRedirect("board.do");
    }
    
    private void get(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String strSeq = request.getParameter("seq");
        long seq = Long.parseLong(strSeq);
        
        BoardService service = BoardService.getInstance();
        Board board = service.editS(seq);
        request.setAttribute("board", board);
        
        String view = "detail.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String strSeq = request.getParameter("seq");
        long seq = Long.parseLong(strSeq);
        String writer = request.getParameter("writer");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        Board board = new Board(seq, writer, email, subject, content, null);
        
        BoardService service = BoardService.getInstance();
        boolean flag = service.updateS(board);
        if (flag) {
            response.sendRedirect("board.do");
        } else {
            response.sendRedirect("edit.jsp?seq=" + seq);
        }
    }
}
