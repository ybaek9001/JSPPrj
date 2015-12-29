package com.newlec.webprj.dao.mybatis;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlec.webprj.dao.MemberDao;
import com.newlec.webprj.vo.Member;

public class MyBatisMemberDao implements MemberDao {

	//SqlSessionFactory ssf = NewlecSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Autowired //
	private SqlSession session;
	
	@Override
	public List<Member> getMembers() throws SQLException {

		return getMembers(1, "MID", "");
	}

	@Override
	public List<Member> getMembers(int page) throws SQLException {

		return getMembers(page, "MID", "");
	}

	@Override
	public List<Member> getMembers(int page, String field, String query) throws SQLException {

		//SqlSession session = ssf.openSession();
		MemberDao dao = session.getMapper(MemberDao.class); // mapper按眉 积己
		List<Member> list = dao.getMembers(page, field, query);
		session.close(); // 技记 辆丰.

		return list;
	}

	@Override
	public int update(Member member) throws SQLException {
		//SqlSession session = ssf.openSession();
		MemberDao dao = session.getMapper(MemberDao.class);
		int aft = dao.update(member);

		session.commit();
		session.close();
		return aft;
	}

	@Override
	public int delete(String mid) {
		//SqlSession session = ssf.openSession();
		MemberDao dao = session.getMapper(MemberDao.class);
		int aft = dao.delete(mid);

		session.commit();
		session.close();
		return aft;
	}

	@Override
	public int insert(Member member) throws SQLException {
		//SqlSession session = ssf.openSession();
			MemberDao dao = session.getMapper(MemberDao.class);
			int aft = dao.insert(member);

			session.commit();
			session.close();
			return aft;
		
	}

	@Override
	public Member getMember(String mid) throws SQLException {
		//SqlSession session = ssf.openSession();
		MemberDao dao = session.getMapper(MemberDao.class);
		Member member = dao.getMember(mid);
		
		return member;
	}

}

