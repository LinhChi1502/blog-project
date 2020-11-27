package com.chinguyen.blogdemo.service.blog;

import com.chinguyen.blogdemo.model.Blog;
import com.chinguyen.blogdemo.repository.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogService implements IBlogService {

    @Autowired
    private BlogRepo blogRepo;

    @Override
    public Page<Blog> findAll(Pageable pageable)  {
        return blogRepo.findAll(pageable);
    }

    @Override
    public Blog findById(Long id) {
        return blogRepo.findOne(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepo.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepo.delete(id);
    }

    @Override
    public Page<Blog> findAllByTitleContaining(String title, Pageable pageable) {
        return blogRepo.findAllByTitleContaining(title, pageable);
    }

    @Override
    public Iterable<Blog> findAll(Sort sort) {
        return blogRepo.findAll(sort);
    }
}
