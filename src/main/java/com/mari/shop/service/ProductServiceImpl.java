package com.mari.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mari.shop.domain.Product;
import com.mari.shop.mapper.ProductMapper;
import com.mari.shop.model.NewProductModel;
import com.mari.shop.model.PageObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	private final ProductMapper productMapper;
	@Override
	public List<Product> selectAll() {
		return productMapper.selectAll();
	}
	@Override
	public List<Product> selectByCategoryId(Long categoryId) {
		return productMapper.selectByCategoryId(categoryId);
	}
	@Override
	public Product selectByProductId(Long productId) {
		return productMapper.selectByProductId(productId);
	}
	@Override
	public int delete(Long productId) {
		return productMapper.delete(productId);
	}
	@Override
	public int update(Product product) {
		return productMapper.update(product);
	}
	@Override
	public int insert(NewProductModel product) {
		return productMapper.insert(product);
	}
	@Override
	public List<Product> selectWithPage(PageObject pageObject) {
		return productMapper.selectWithPage(pageObject);
	}
	@Override
	public int countAll() {
		return productMapper.countAll();
	}

}
