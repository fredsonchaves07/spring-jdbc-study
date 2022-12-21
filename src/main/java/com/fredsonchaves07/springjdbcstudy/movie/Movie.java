package com.fredsonchaves07.springjdbcstudy.movie;

import com.fredsonchaves07.springjdbcstudy.actor.Actor;

import java.time.LocalDate;
import java.util.List;

public record Movie(Integer id,
                    String name,
                    List<Actor> actors,
                    LocalDate releaseDate) {
}
