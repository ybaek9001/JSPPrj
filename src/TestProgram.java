
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.newlec.webprj.dao.MemberDao;
import com.newlec.webprj.dao.NoticeDao;
import com.newlec.webprj.dao.mybatis.MyBatisNoticeDao;
import com.newlec.webprj.dao.mybatis.NewlecSqlSessionFactoryBuilder;
import com.newlec.webprj.vo.Member;
import com.newlec.webprj.vo.Notice;

public class TestProgram {

	public static void main(String[] args) throws SQLException {
		
		int x=3;
		int y=0;
		
		int z;
		
		//������ �߻��ϸ�
		try{
			z = Calc.div(x, y);
		}
		//��ġ�� ���ϰڴ�
		catch(����ó�� e)
		{
			System.out.println("�̾� :D");
		}
		catch(Exception e) {
			
		}
		finally {
			//��������
			System.out.println("���������Դϴ�.");
		}
		
		System.out.println("��°�����?");
		
		
		//JdbcMemberDao dao = new JdbcMemberDao();
		/*SqlSessionFactory ssf = NewlecSqlSessionFactoryBuilder.getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		
		MemberDao dao = session.getMapper(MemberDao.class);
		
		Member a = new Member();
		a.setMid("newlec");
		a.setPwd("1234");
		a.setName("����");
		
		int update = dao.update(a);
		List<Member> list = dao.getMembers(1, "MID", "");
			
		System.out.println("�˻����: " + list.size());
	
		
		for(Member m : list)
		{
			System.out.printf("mid : %s, name: %s\n", m.getMid(), m.getName());			
		}
	
		System.out.printf("%d\n", update);
		
		session.close();*/
		
		NoticeDao dao = new MyBatisNoticeDao();
		Notice notice = dao.getNotice("1");
		System.out.print(notice.getTitle());
		
        /*List<Notice>list = dao.getNotices(1, "Title","");
        for(Notice n: list)
        {
        System.out.printf("title : %s, code: %s, writer: %s, hit : %d\n", 
              n.getTitle(),n.getCode(),n.getWriter(),n.getHit());
        }*/
        
        
	}

}

