package com.fredsonchaves07.springjdbcstudy.movie;

import com.fredsonchaves07.springjdbcstudy.exception.NotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieDataAccessService implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public MovieDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> selectMovies() {
        String sql = """
            SELECT id, name, release_date
            FROM movie
            LIMIT 100;
        """;
        return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    @Override
    public int insertMovie(Movie movie) {
        String sql = """
            INSERT INTO movie(id, name, release_date)
            VALUES (?, ?);
        """;
        return jdbcTemplate.update(sql, movie.name(), movie.releaseDate());
    }

    @Override
    public int deleteMovie(int id) {
        String sql = """
            DELETE FROM movie
            WHERE id = ?;   
        """;
        return jdbcTemplate.update(sql, id);
    }

    public int updateMovie(Movie movieUpdate, int id) {
        Movie movie = selectMovieById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Movie with id %s not found", id)));
        String sql = """
            UPDATE movie
            SET movie.name = name;   
        """;
        return 0;
    }

    @Override
    public Optional<Movie> selectMovieById(int id) {
        String sql = """
            SELECT id, name, release_date
            FROM movie
            WHERE id = ?;
        """;
        return jdbcTemplate.query(sql, new MovieRowMapper(), id).stream().findFirst();
    }
    
}
