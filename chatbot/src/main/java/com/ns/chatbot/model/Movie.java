package com.ns.chatbot.model;

import lombok.Builder;

import java.io.Serializable;

/**
 * Represents a movie with its details.
 * <p>
 * This record captures movie information including:
 * <ul>
 *   <li>Basic details like title, genre, and director</li>
 *   <li>Release year and ratings</li>
 *   <li>IMDB-specific information</li>
 * </ul>
 * <p>
 * It implements Serializable to support caching and data transfer.
 *
 * @param id         Unique identifier for the movie
 * @param title      The title of the movie
 * @param genre      The primary genre of the movie
 * @param director   The director of the movie
 * @param year       The release year
 * @param rating     The general rating (e.g., PG-13)
 * @param imdb       The IMDB identifier
 * @param imdbRating The rating on IMDB (e.g., 8.5)
 * @param imdbGenre  The genre classification on IMDB
 */
@Builder
public record Movie(Integer id, String title, String genre, String director,
                    Integer year, String rating, String imdb, String imdbRating,
                    String imdbGenre) implements Serializable {
}
