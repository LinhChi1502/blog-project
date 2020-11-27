package com.chinguyen.blogdemo.service.blog;

import com.chinguyen.blogdemo.model.Blog;
import com.chinguyen.blogdemo.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface IBlogService extends IService<Blog> {
    @Override
    Page<Blog> findAll(Pageable pageable);

    @Override
    Blog findById(Long id);

    @Override
    void save(Blog model);

    @Override
    void remove(Long id);

    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);

    Iterable<Blog> findAll(Sort sort);
}
