package com.example.buoi18;

import com.example.buoi18.entity.Movie;
import com.example.buoi18.model.enums.MovieType;
import com.example.buoi18.repository.MovieRepository;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootTest
class Buoi18ApplicationTests {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    void save_movie() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        Random rd = new Random();

        for (int i = 0; i < 150; i++) {
            String name = faker.name().fullName();
            Boolean status = faker.bool().bool();
            Movie movie = Movie.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .description(faker.lorem().paragraph())
                    .poster("https://placehold.co/600x400?text=hien" + name.substring(0,1).toUpperCase())
                    .releaseYear(faker.number().numberBetween(2020,2024))
                    .rating(faker.number().randomDouble(1,6,10))
                    .trailerUrl("https://www.youtube.com/embed/gCUg6Td5fgQ?si=OCtNkpFF03gq03ny")
                    .type(MovieType.values()[rd.nextInt(MovieType.values().length)])
                    .status(status)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .publishedAt(status ? LocalDateTime.now() : null)
                    .build();
            movieRepository.save(movie);
        }
    }

    @Test
    void test_method() {
        List<Movie> movies = movieRepository.findAll();
        System.out.println("Movies size : " + movies.size());

        Movie movie1 = movieRepository.findById(1).orElse(null);
        System.out.println("Movie 1 : " + movie1);

        List<Movie> moviesByIds = movieRepository.findAllById(List.of(1,2,3));
        System.out.println("moviesByIds: " + moviesByIds);

        // update
        movie1.setName("Chua te nhung chiec nhan");
        movieRepository.save(movie1);

        // delete
        movieRepository.delete(movie1);
        movieRepository.deleteById(2);
        movieRepository.deleteAllById(List.of(3,4,5));
    }

    @Test
    public void testMethodQuery(){
        long countPhimBo = movieRepository.countByType(MovieType.PHIM_BO);
        System.out.println("So luong Phim bo : " + countPhimBo);
    }
}
