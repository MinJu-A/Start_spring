package inhatc.ac.kr.springstudy.configuration;

import java.io.IOException;
import java.net.URL;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//설정 파일로 쓸 수 있는 애
@Configuration
//DB와 관련된 정보를 담고 있는 애가 어디 있는지 알려줄 거야. 이 어노테이션으로 프로퍼티스에 접근할 수 있게 된다.
@PropertySource("classpath:/application.properties")
public class DataBaseConriguration {
	
	
//	마이바티스 설정에 필요한 변수
	@Autowired
	private ApplicationContext applicationContext;


	
//	hikariconfig를 메모리에서 갖다 쓸 거니까 
	@Bean
//	저 prefix 안에 있는 걸 그냥 기본으로 갖다 쓰겠다 하는 어노테이션
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
		
	}
	
	
//	마이바티스는 xml로 구성하는데 xml 파일을 추가해서 여기에 연동시켜 줄 겁니다.
	
	@Bean
	public DataSource dataSource() throws Exception{
//		새로운 datasource를 만들 때 hikariconfig에 있는 정보들을 이용해서 소스를 만든다. spring.datasource.hikari 이거이거
		DataSource dataSource = new HikariDataSource(hikariConfig());
		
//		제대로 찍히는지 확인
		System.out.println("========>" + dataSource.toString());
		return dataSource;
	}
	
	
//	마이바티스를 쓰기 위해선 아래 두 가지 동작을 이렇게 미리 해놔야 한다.
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
//		아마 읽어올 수 있는 경로 설정하는 것 같아요 
//		위에 만들어놓은 applicationContext에서 가지고 올 거예요
//		근데 여러 종류의 폴더가 존재할 수 있기 때문에 **로 설정
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/sql-*.xml"));
//		bean 중에서도 걔 담겨있는 object를 가져와야 한다.
//		근데 이것도 얘외처리를 해야합니다.
		return sqlSessionFactoryBean.getObject();
		
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	

}
