package com.jb.master.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.jb.owner.model.vo.Owner;

public class MasterDao {

	private Properties prop = new Properties();
	
	public MasterDao() {
		String path = MasterDao.class.getResource("sql/master/master-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectCountOwner(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int result=0;
		String sql = prop.getProperty("selectCountOwner");
		try {
			pstmt=conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt("cnt");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public List<Owner> selectListPage(Connection conn, int cPage, int numPerPage){
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectListPage");
		List<Owner> list = new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Owner o = new Owner();
				o.setoId(rs.getString("o_id"));
				o.setoName(rs.getString("o_name"));
				o.setoBirth(rs.getDate("o_birth"));
				o.setoGender(rs.getString("o_gender"));
				o.setoEmail(rs.getString("o_email"));
				o.setoPhone(rs.getString("o_phone"));
				o.setoAddr(rs.getString("o_addr"));
				o.setoEd(rs.getDate("o_ed"));
				list.add(o);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	
}
