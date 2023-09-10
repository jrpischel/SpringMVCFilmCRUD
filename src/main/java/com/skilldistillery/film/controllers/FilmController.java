package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDao;

	@RequestMapping(path = "getFilm.do", method = RequestMethod.GET, params = "filmId")
	public ModelAndView getFilmById(@RequestParam Integer filmId) {
		ModelAndView mv = new ModelAndView();
		Film film = filmDao.findFilmById(filmId);
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/film.jsp");
		return mv;
	}

	@RequestMapping(path = "getFilm.do", method = RequestMethod.GET, params = "keyword")
	public ModelAndView getFilmByKeyWord(@RequestParam String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = filmDao.findFilmByKeyWord(keyword);
		mv.addObject("film", films);
		mv.setViewName("WEB-INF/keyword.jsp");
		return mv;
	}
	
	@RequestMapping(path = "delete.do", method = RequestMethod.GET, params = "delete")
	public ModelAndView deleteFilm(@RequestParam Film film) {
		ModelAndView mv = new ModelAndView();
		filmDao.deleteFilm(film);
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/deleted.jsp");
		return mv;
	}

}
