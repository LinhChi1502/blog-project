package com.chinguyen.blogdemo.controller;

import com.chinguyen.blogdemo.model.Blog;
import com.chinguyen.blogdemo.model.Category;
import com.chinguyen.blogdemo.service.blog.IBlogService;
import com.chinguyen.blogdemo.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Page<Category> category(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Blog blog, @RequestParam("date1") Date date1) {
        MultipartFile image = blog.getImage();
        File file = new File("E:\\LINHCHI\\CODEGYM\\images\\" + image.getOriginalFilename());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(image.getBytes());
            fileOutputStream.close();
            String imageURL = image.getOriginalFilename();
            blog.setImageURL(imageURL);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Date date = blog.getDate();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String dateStr = sdf.format(date);
//        java.sql.Date sqlDate = java.sql.Date.valueOf(dateStr);
        blog.setDate(date1);
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("redirect:/blogs");
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView list(@PageableDefault(value = 5, page = 0) Pageable pageable) {
        Page<Blog> blogs = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }
}
