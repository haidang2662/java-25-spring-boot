package com.example.buoi18.controller;

import com.example.buoi18.entity.Movie;
import com.example.buoi18.model.enums.MovieType;
import com.example.buoi18.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class webController {

    private final MovieService movieService;
    //

    @GetMapping("/")
    public String getHomePage(Model model) {

        List<Movie> listPhimBo = movieService.getMoviesByType(MovieType.PHIM_BO,true,1,6).getContent();
        List<Movie> listPhimLe = movieService.getMoviesByType(MovieType.PHIM_BO,true,1,6).getContent();
        List<Movie> listPhimChieuRap = movieService.getMoviesByType(MovieType.PHIM_BO,true,1,6).getContent();
        model.addAttribute("listPhimBo" , listPhimBo);
        model.addAttribute("listPhimLe" , listPhimLe);
        model.addAttribute("listPhimChieuRap" , listPhimChieuRap);

        return "web/index";
    }


    @GetMapping("/phim-bo")
    public String getPhimBoPage(
            Model model,
            @RequestParam(required = false,defaultValue = "1") int page ,
            @RequestParam(required = false,defaultValue = "12") int pageSize) {

        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_BO,true,page,pageSize);
        model.addAttribute("pageData" , pageData);
        model.addAttribute("CurrentPage" , page);
        return "web/phim-bo";
    }

}
