package com.jb.board.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.jb.board.model.vo.Board;
import com.jb.board.model.vo.BoardComment;

public class BoardDao {
	
private Properties prop = new Properties();
	
	public BoardDao() {
		String path = BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}

	public int selectCountBoard(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectCountBoard");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt("cnt");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	//게시글 검색
	public List<Board> selectBoardFinder(Connection conn ,int cPage,int numPerPage, String keyword) {
		Statement stmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList();
		int start = (cPage-1)*numPerPage+1;
		int end = cPage*numPerPage;
		String sql = "select * from("
				+"select rownum as rnum, a.* from("
				+"select * from community where title"
				+" like '%"+keyword+"%' order by ent_date desc)a) "
				+"where rnum between "+start+" and "+end;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Board b = new Board();
				b.setCmmNo(rs.getInt("cmm_no"));
				b.setcId(rs.getString("c_id"));
				b.setTitle(rs.getString("title"));
				b.setEntDate(rs.getDate("ent_date"));
				b.setContent(rs.getString("content"));
				b.setCategory(rs.getString("category"));
				b.setOriginalFilename(rs.getString("original_filename"));
				b.setRenameFilename(rs.getString("rename_filename"));
				b.setCommuCnt(rs.getInt("commu_cnt"));
				b.setViewCnt(rs.getInt("view_cnt"));
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}return list;
	}

	public List<Board> selectBoard(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectBoard");
		List<Board> list = new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board b=new Board();
				b.setCmmNo(rs.getInt("cmm_no"));
				b.setTitle(rs.getString("title"));
				b.setEntDate(rs.getDate("ent_date"));
				b.setContent(rs.getString("content"));
				b.setCategory(rs.getString("category"));
				b.setOriginalFilename(rs.getString("original_filename"));
				b.setRenameFilename(rs.getString("rename_filename"));
				b.setViewCnt(rs.getInt("view_cnt"));
				b.setCommuCnt(rs.getInt("commu_cnt"));
				b.setcId(rs.getString("c_id"));
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getcId());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getContent());
			pstmt.setString(4, b.getOriginalFilename());
			pstmt.setString(5, b.getRenameFilename());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Board selectBoardOne(Connection conn, int cmmNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board b=null;
		String sql=prop.getProperty("selectBoardOne");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cmmNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b=new Board();
				b.setCmmNo(rs.getInt("cmm_no"));
				b.setTitle(rs.getString("title"));
				b.setcId(rs.getString("c_id"));
				b.setContent(rs.getString("content"));
				b.setEntDate(rs.getDate("ent_date"));
				b.setRenameFilename(rs.getString("rename_filename"));
				b.setOriginalFilename(rs.getString("original_filename"));
				b.setViewCnt(rs.getInt("view_cnt"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}

	public int deleteBoard(Connection conn, int cmmNo) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cmmNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
		
	}

	public int updateBoard(Connection conn, Board b) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, b.getOriginalFilename());
			pstmt.setString(4, b.getRenameFilename());
			pstmt.setInt(5, b.getCmmNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}

	public int insertComment(Connection conn, BoardComment bc) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertComment");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bc.getComment());
			pstmt.setString(2, bc.getcId());
			pstmt.setInt(3, bc.getCommentLevel());
			pstmt.setString(4, bc.getCommentRef()==0?null:String.valueOf(bc.getCommentRef()));
			pstmt.setInt(5, bc.getCmmNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteComment(Connection conn, int cmmNo, int coNo) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteComment");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cmmNo);
			pstmt.setInt(2, coNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<BoardComment> selectBoardComment(Connection conn, int cmmNo) {
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		List<BoardComment> list=new ArrayList();
		String sql=prop.getProperty("selectBoardComment");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cmmNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardComment bc=new BoardComment();
				bc.setCoNo(rs.getInt("co_no"));
				bc.setCmmNo(rs.getInt("cmm_no"));
				bc.setComment(rs.getString("comment"));
				bc.setCommentDate(rs.getDate("comment_date"));
				bc.setcId(rs.getString("c_id"));
				bc.setCommentLevel(rs.getInt("comment_level"));
				bc.setCommentRef(rs.getInt("comment_ref"));
				list.add(bc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int updateCount(Connection conn, int cmmNo) {
		Statement stmt=null;
		int result=0;
		String sql="update community set view_cnt=view_cnt+1 where cmm_no="+cmmNo;
		try {
			stmt=conn.createStatement();
			result=stmt.executeUpdate(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}return result;
	}

	public Board findBoardWriter(Connection conn, int cmmNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board b=null;
		String sql=prop.getProperty("findBoardWriter");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cmmNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b=new Board();
				b.setcId(rs.getString("c_id"));
				b.setTitle(rs.getString("title"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}
	
}
