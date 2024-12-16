/* JDBC 테스트 코드 = JDBC 드라이버 이상 유무 테스트 => 오류 발생할수도 있음 */
package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","spring_ex","spring_ex")) {
			log.info(con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
