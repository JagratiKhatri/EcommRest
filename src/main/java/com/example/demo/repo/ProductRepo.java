package com.example.demo.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	public Product findByPid(int id);
	
	public Product findByName(String name);

	public List<Product> findByCategory(Category cid);
	
//	 @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
//	            + " OR p.brand LIKE %?1%"
//	            + " OR p.madein LIKE %?1%"
//	            + " OR CONCAT(p.price, '') LIKE %?1%")
	@Query(value=" SELECT * from product p WHERE LOWER( p.name) LIKE CONCAT('%' ,:query ,'%') " + " OR LOWER ( p.description) LIKE CONCAT('%',:query , '%')"  ,nativeQuery = true )
	 @Transactional
	public List<Product> searchProducts(@Param("query") String query);


}
