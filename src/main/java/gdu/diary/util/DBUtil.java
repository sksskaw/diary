package gdu.diary.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtil {
	
	public SqlSession getSqlSession() {
		
		SqlSession sqlSession = null;
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("config.xml");
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
			sqlSession = ssf.openSession();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession;
	}
}
