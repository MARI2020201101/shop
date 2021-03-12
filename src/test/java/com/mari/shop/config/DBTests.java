package com.mari.shop.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.mari.shop.domain.Product;
import com.mari.shop.mapper.ProductMapper;
import com.mari.shop.mapper.UserMapper;
import com.mari.shop.model.NewProductModel;
import com.mari.shop.security.CustomUserDetailsService;

import lombok.extern.java.Log;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource("classpath:/application.properties")
public class DBTests {
	@Autowired
	private DataSource ds;
	
	//private final Logger log = (Logger) LoggerFactory.getLogger(Logger.class);
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	@Test
	public void testConnection() {
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521/xe",
				"toyshop",
				"toyshop"
				)){
			log.info("con: " + con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	public void testDataSource() throws Exception {
		System.out.println("DS=" + ds);

		try (Connection conn = ds.getConnection()) {
			System.out.println("Cooooooooonn=" + conn);
			assertThat(conn).isInstanceOf(Connection.class);

			assertTrue(0 < getLong(conn, "select count(*) from TBL_USER"));
			System.out.println("get Long Result : " + getLong(conn, "select count(*) from TBL_USER"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private long getLong(Connection conn, String sql) {
		long result = 0;
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getLong(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	ProductMapper productMapper;
	
	@Test
	public void countTest() throws Exception {
		int result = userMapper.count();
		System.out.println("UserMapper count()" + result);
	}
	
	@Autowired
	CustomUserDetailsService service;
	
	
	public void loadUserbyUsernameTest() {
		UserDetails user =service.loadUserByUsername("mari");
		log.info(user.toString());
	}

	
	public void selectAllTest() throws Exception {
		userMapper.selectAll();
		System.out.println("UserMapper selectAll");
	}
	
	public void deleteTest() throws Exception {
		//userMapper.delete(4);
		System.out.println("UserMapper Delete");
	}
	
	
	public void selectAllProductTest() throws Exception {
		productMapper.selectAll();
		System.out.println("==================productMapper selectAll================");
	}
	
	
	public void selectProductByCategoryTest() throws Exception {
		System.out.println("==================productMapper selectByCategory================");
		productMapper.selectByCategoryId(1L);
		
	}
	
	public void selectProductByIdTest() throws Exception {
		System.out.println("==================productMapper selectById================");
		productMapper.selectByProductId(1L);}
	
	
	public void deleteProductTest() throws Exception {
		System.out.println("==================productMapper deleteById================");
		int result= productMapper.delete(2L);
		log.info("=========delete result : "+ result);
		}
	
	public void insertProductTest() throws Exception {
		System.out.println("==================productMapper insert ================");
		NewProductModel npro = NewProductModel.builder()
												.categoryId(2L)
												.detail("detail insert test")
												.img("img~~")
												.price(13000)
												.productName("test 인형")
												.stock(29)
												.build();
		int result= productMapper.insert(npro);
		log.info("=========insert result : "+ result);
		}
	@Test
	public void updateProductTest() throws Exception {
		System.out.println("==================updateMapper insert ================");
		Product pro = Product.builder()
												.categoryId(2L)
												.detail("detail insert test")
												.img("img~~")
												.price(99000)
												.productName("test 인형")
												.stock(29)
												.productId(1L)
												.build();
		int result= productMapper.update(pro);
		log.info("=========insert result : "+ result);
		}
}
