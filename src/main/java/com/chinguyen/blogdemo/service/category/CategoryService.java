package com.chinguyen.blogdemo.service.category;

import com.chinguyen.blogdemo.model.Category;
import com.chinguyen.blogdemo.repository.CateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CategoryService implements ICategoryService {

    @Autowired
    private CateRepo cateRepo;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return cateRepo.findAll(pageable);
    }

    @Override
    public Category findById(Long id) {
        return cateRepo.findOne(id);
    }

    @Override
    public void save(Category category) {
        cateRepo.save(category);
    }

    @Override
    public void remove(Long id) {
        cateRepo.delete(id);
    }
}
