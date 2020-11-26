package com.chinguyen.blogdemo.repository;

import com.chinguyen.blogdemo.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CateRepo extends PagingAndSortingRepository<Category, Long> {
}
