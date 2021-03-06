package com.jb.pension.model.service;

import static common.template.JDBCTemplate.close;
import static common.template.JDBCTemplate.commit;
import static common.template.JDBCTemplate.getConnection;
import static common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.jb.pension.model.dao.RoomDao;
import com.jb.pension.model.vo.Room;

public class RoomService {
   
   private RoomDao dao = new RoomDao();
   
	//객실 한개 정보 받아오기
	public Room selectRoom(String rNo) {
		Connection conn = getConnection();
		Room room = dao.selectRoom(conn,rNo);
		close(conn);
		return room;
	}
	
	//선택된 펜션의 전체 객실 수
	public int selectCountRoom(String pCode) {
		Connection conn = getConnection();
		int count = dao.selectCountRoom(conn, pCode);
		close(conn);
		return count;
	}
	
	public List<Room> selectListPage(int cPage, int numPerPage, String pCode){
		Connection conn = getConnection();
		List<Room> list = dao.selectListPage(conn,cPage,numPerPage,pCode);
		close(conn);
		return list;
	}
	
	//업주->승인된펜션->디테일->선택된펜션의 객실들 불러오기
	public List<Room> selectRoomList(String pCode) {
		Connection conn = getConnection();
		List<Room> list = dao.selectRoomList(conn,pCode);
		close(conn);
		return list;
	}
	
	//객실추가
	public int addRoom(String pCode, String rName, int rNop, int rMaxNop, int rPrice, int rAddPrice,
			String rSize, String rStruc, String rInfo) {
		Connection conn = getConnection();
		int result = dao.addRoom(conn,pCode,rName,rNop,rMaxNop,rPrice,rAddPrice,rSize,rStruc,rInfo);
		if(result>0) {
			commit(conn);
			result = dao.getCurrval(conn);
			if(result>0) {
				commit(conn);
			}
			else {
				rollback(conn);
			}
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	//객실 수정
	public int modifyRoom(String rNo, String rName, int rNop, int rMaxNop, int rPrice, int rAddPrice, String  rSize, String rStruc, String rInfo) {
		Connection conn = getConnection();
		int result = dao.modifyRoom(conn,rNo,rName,rNop,rMaxNop,rPrice,rAddPrice,rSize,rStruc,rInfo);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	//객실 한개 삭제
	public int deleteOneRoom(String rNo) {
		Connection conn = getConnection();
		int result = dao.deleteOneRoom(conn,rNo);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		} close(conn);
		return result;
	}
  
     //룸조회
   public Room roomInFo(String rno) {
	   Connection conn = getConnection();
	   Room r = dao.roomInFo(conn,rno);
	   close(conn);
	   return r;
   }


}


