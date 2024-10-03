package com.example.buoi18_lam_lai.controller;

import com.example.buoi18_lam_lai.entity.Blog;
import com.example.buoi18_lam_lai.entity.Movie;
import com.example.buoi18_lam_lai.model.enums.MovieType;
import com.example.buoi18_lam_lai.service.BlogService;
import com.example.buoi18_lam_lai.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final MovieService movieService;
    private final BlogService blogService;

    @GetMapping("/")
    public String getHomePage(Model model) {

        List<Movie> listPhimBo = movieService.getMoviesByType(MovieType.PHIM_BO, true, 1, 6).getContent();
        List<Movie> listPhimLe = movieService.getMoviesByType(MovieType.PHIM_BO, true, 1, 6).getContent();
        List<Movie> listPhimChieuRap = movieService.getMoviesByType(MovieType.PHIM_BO, true, 1, 6).getContent();
        model.addAttribute("listPhimBo", listPhimBo);
        model.addAttribute("listPhimLe", listPhimLe);
        model.addAttribute("listPhimChieuRap", listPhimChieuRap);

        return "web/index";
    }


    // /phim-bo?page=1&pageSize=12
    @GetMapping("/phim-bo")
    public String getPhimBoPage(
            Model model,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "12") int pageSize) {

        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/phim-bo";
    }

    @GetMapping("/phim-le")
    public String getPhimLePage(Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(Model model,
                                      @RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/phim-chieu-rap";
    }


//     /phim/1/chua-te-nhung-chiec-nhan
    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailsPage(Model model,
                                      @PathVariable Integer id,
                                      @PathVariable String slug) {
        Movie movie = movieService.getMovieDetails(id, slug);
        model.addAttribute("movie", movie);
        return "web/chi-tiet-phim";
    }

    @GetMapping("/tin-tuc")
    public String getBlogPage(Model model,
                              @RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false, defaultValue = "10") int pageSize) {
        Page<Blog> pageData = blogService.getBlogs(true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/tin-tuc";
    }

    @GetMapping("/tin-tuc/{id}/{slug}")
    public String getBlogDetailsPage(Model model,
                                     @PathVariable Integer id,
                                     @PathVariable String slug) {
        Blog blog = blogService.getBlogDetails(id, slug);
        model.addAttribute("blog", blog);
        return "web/chi-tiet-tin-tuc";
    }
}
