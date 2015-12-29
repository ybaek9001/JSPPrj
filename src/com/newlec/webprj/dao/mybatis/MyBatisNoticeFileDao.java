package com.newlec.webprj.dao.mybatis;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.lf5.util.ResourceUtils;

import com.newlec.webprj.dao.MemberDao;
import com.newlec.webprj.dao.NoticeDao;
import com.newlec.webprj.dao.NoticeFileDao;
import com.newlec.webprj.vo.Member;
import com.newlec.webprj.vo.Notice;
import com.newlec.webprj.vo.NoticeFile;

public class MyBatisNoticeFileDao implements NoticeFileDao {

	SqlSessionFactory ssf = NewlecSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public List<NoticeFile> getNoticeFiles(String noticeCode) {
		SqlSession session = ssf.openSession();
		NoticeFileDao dao = session.getMapper(NoticeFileDao.class); // mapper按眉 积己
		
		List<NoticeFile> list = dao.getNoticeFiles(noticeCode);
		
		session.close(); // 技记 辆丰.

		return list;
	}

	@Override
	public int insert(NoticeFile file) throws SQLException {
		SqlSession session = ssf.openSession();
	      NoticeFileDao dao = session.getMapper(NoticeFileDao.class);
	      int result=0;
	      
	      try{
	    	  result = dao.insert(file);
	      }
	      catch(Exception e)
	      {
	         System.out.println(e.getMessage());
	         session.rollback();
	      }
	      
	      session.commit();      
	      session.close();
	      
	      return result;
	}

}

