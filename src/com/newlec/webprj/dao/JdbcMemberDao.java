package com.newlec.webprj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newlec.webprj.vo.Member;

public class JdbcMemberDao implements MemberDao {

	public List<Member> getMembers() throws SQLException{
		return getMembers(1,"MID","");		
	}
	public List<Member> getMembers(int page) throws SQLException{
			return getMembers(page,"MID","");
		}
		
	public List<Member> getMembers(int page, String field, String query) throws SQLException{

		//String sql = "SELECT * FROM MEMBERS WHERE";
		
		int start = 1+(page-1)*10;
		int end = page*10;
		
		/*String sql = "SELECT * FROM("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY REGDATE DESC) NUM, MEMBERS. * FROM MEMBERS) A "
				+ "WHERE NUM BETWEEN " +start+"AND "+end;*/
		String sql = "SELECT * FROM("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY REGDATE DESC) NUM, MEMBERS. * FROM MEMBERS "
				+ "WHERE "+field+" LIKE ? "
				+ ") A "
				+ "WHERE NUM BETWEEN ? AND ?";
		
		//String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		String url = "jdbc:sqlserver://211.238.142.251:1433;databaseName=edudb;";
		
		Connection con = DriverManager.getConnection(url, "edu", "class2d");
		
		//Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql);		
		st.setString(1, "%"+query+"%");
		st.setInt(1,  start);
		st.setInt(2,  end);
		
		ResultSet rs = st.executeQuery(sql);
		
		List<Member> list = new ArrayList<Member>();
		Member member = null;
				
		
		while (rs.next()){
			member = new Member();
			
			member.setMid(rs.getString("mid"));
			member.setName(rs.getString("name"));
			member.setPwd(rs.getString("pwd"));
			
			list.add(member);
			
		}

		rs.close();
		st.close();
		con.close();
		
		return list;
		
	}
	@Override
	public int update(Member member) throws SQLException {
		
		String sql = "UPDATE Member SET Name=?, Pwd=? WHERE Mid=?";
		
		String url = "jdbc:sqlserver://211.238.142.251:1433;databaseName=edudb;";
		Connection con = DriverManager.getConnection(sql);
		
		PreparedStatement st = con.prepareStatement(sql);		
		st.setString(1, member.getName());
		st.setString(2, member.getPwd());
		st.setString(3, member.getMid());
		
		int aft = st.executeUpdate();
		st.close();
		con.close();
		
		return aft;
	}
	@Override
	public int delete(String mid) {
		
		
		return 0;
	}
	@Override
	public int insert(Member member) throws SQLException {
		
		return 0;

	}
	@Override
	public Member getMember(String mid) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}

