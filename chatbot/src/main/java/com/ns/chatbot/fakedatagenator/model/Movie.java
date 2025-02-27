package com.ns.chatbot.fakedatagenator.model;

import lombok.Builder;

import java.io.Serializable;


@Builder
public record Movie(Integer id, String title, String genre, String director,
                    Integer year, String rating, String imdb, String imdbRating,
                    String imdbGenre) implements Serializable {
}
