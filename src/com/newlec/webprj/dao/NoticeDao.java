package com.newlec.webprj.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.newlec.webprj.vo.Notice;

public interface NoticeDao {
	@Select("SELECT * FROM("
	+" SELECT" 
	+" ROW_NUMBER() OVER (ORDER BY REGDATE DESC) NUM," 
	+" NOTICES. * FROM NOTICES" 
	+" WHERE ${field} LIKE '%${query}%') A"
	+" WHERE NUM BETWEEN 1+( #{page}-1)*10 AND #{page}*10")
	public List<Notice> getNotices(@Param("page")int page, @Param("field")String field, @Param("query")String query);
	
	//@Select("SELECT * FROM NOTICES WHERE CODE = #{code}")
	public Notice getNotice(String code);
	public Notice getPrevNotice(String code);
	public Notice getNextNotice(String code);
	
	public int delete(String code);
	@SelectKey(before = true, keyProperty = "code", resultType = String.class, statement = { "SELECT MAX(CAST(CODE AS INT)) CODE FROM NOTICES" })
	@Insert("INSERT INTO NOTICES(code, title, content, writer) Values(#{code}+1,#{title},#{content},#{writer})")
	public int insert(Notice n) throws SQLException;
	public String getLastCode();
	
}
