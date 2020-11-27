package com.chinguyen.blogdemo.controller;

import com.chinguyen.blogdemo.model.Blog;
import com.chinguyen.blogdemo.model.Category;
import com.chinguyen.blogdemo.service.blog.IBlogService;
import com.chinguyen.blogdemo.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import java.util.Optional;

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
    public ModelAndView create(@ModelAttribute Blog blog) {
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
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("redirect:/blogs/");
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView list(@PageableDefault(value = 5) Pageable pageable) {
        Page<Blog> blogs = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable(name = "id") long id) {
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("blog/detail");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") long id) {
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@ModelAttribute Blog blog) {
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
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("redirect:/blogs/");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable(name = "id") long id) {
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("blog/delete");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteBlog(@PathVariable(name = "id") long id) {
        blogService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/blogs/");
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam(name = "s") Optional<String> s, Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("blog/list");
        if (s.isPresent()) {
            modelAndView.addObject("blogs", blogService.findAllByTitleContaining(s.get(), pageable));
        } else {
            modelAndView.addObject("blogs", blogService.findAll(pageable));
        }
        return modelAndView;
    }

    @GetMapping("/sort")
    public ModelAndView sort(@PageableDefault(sort = "date", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Blog> blogs = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }


}
