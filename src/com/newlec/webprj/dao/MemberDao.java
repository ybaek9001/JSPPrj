package com.newlec.webprj.dao;

import java.sql.SQLException;
import java.util.List;

import com.newlec.webprj.vo.Member;

public interface MemberDao {
	public Member getMember(String mid) throws SQLException;
	public List<Member> getMembers() throws SQLException;
	public List<Member> getMembers(int page) throws SQLException;
	public List<Member> getMembers(int page, String field, String query) throws SQLException;
	public int update(Member member) throws SQLException;
	public int delete(String mid);
	public int insert(Member member) throws SQLException;
	
	
}
