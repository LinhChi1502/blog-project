package com.chinguyen.blogdemo.repository;

import com.chinguyen.blogdemo.model.Blog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends PagingAndSortingRepository<Blog, Long> {
}
