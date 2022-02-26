package com.axnd.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axnd.dscatalog.dto.CategoryDTO;
import com.axnd.dscatalog.entities.Category;
import com.axnd.dscatalog.repositories.CategoryRepository;
import com.axnd.dscatalog.services.exceptions.DatabaseExecption;
import com.axnd.dscatalog.services.exceptions.ResourceNotFoundExecption;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list =  repository.findAll();	
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
	
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundExecption("Entity not found"));
		
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
		Category entity = repository.getOne(id);
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
		
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExecption("Id not found" + id);
		}
		
	}

	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} 
		catch (EmptyResultDataAccessException e){
			
			throw new ResourceNotFoundExecption("Id not found" + id);
			
		} 
		catch (DataIntegrityViolationException e) {
			
			throw new DatabaseExecption("Integrity violation");
		}
		
	}
	
}
