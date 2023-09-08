package com.skilldistillery.film.dao;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {

	public Film findFilmById(int filmId);
	  public Actor findActorById(int actorId)throws SQLException;
	  public List<Actor> findActorsByFilmId(int filmId);
	  public List<Film> findFilmByKeyWord(String keyWord);
	  public Actor createActor(Actor actor);
	  public boolean saveActor(Actor actor);
	  public boolean deleteActor(Actor actor);
	  public Film createFilm(Film film);
	  public boolean deleteFilm(Film film);
	
}
