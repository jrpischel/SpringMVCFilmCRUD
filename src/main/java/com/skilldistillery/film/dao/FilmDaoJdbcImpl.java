package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public class FilmDaoJdbcImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String user = "student";
	private static final String pass = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Database not found");
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int id) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "select * from film f join language l on f.language_id = l.id where f.id =?";
//			String sql = "select * from film f join language l on f.language_id = l.id join film_category fc on fc.film_id = f.id join category c on c.id = fc.category_id where f.id =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			System.out.println();
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int releaseYear = rs.getShort("release_year");
				String language = rs.getString("l.name");
				int languageID = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rentalRate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
//				String category = rs.getString("c.name");
				film = new Film(filmId, releaseYear, language, languageID, rentalDuration, length, title, description,
						rating, specialFeatures, rentalRate, replacementCost, null);
				film.setActors(findActorsByFilmId(filmId));
				film.setCategories(findCategoryByFilmId(filmId));
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;

		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT * FROM actor WHERE id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		System.out.println(stmt); // for debugging

		ResultSet actorResult = stmt.executeQuery();

		if (actorResult.next()) {
			actor = new Actor(); // Create the object

			// Here is our mapping of query columns to our object fields:
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));
			actor.setFilms(findFilmsByActorId(actorId));
		}
		actorResult.close();
		stmt.close();
		conn.close();
		return actor;

	}

	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int releaseYear = rs.getShort("release_year");
				int languageID = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rentalRate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				Film film = new Film(filmId, releaseYear, languageID, rentalDuration, length, title, description,
						rating, specialFeatures, rentalRate, replacementCost);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor_id "
					+ " WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Actor actor = new Actor(firstName, lastName);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}
	
	@Override
	public List<String> findCategoryByFilmId(int filmId) {
		List<String> categories = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "select name from category c join film_category fc on c.id = fc.category_id join film f on f.id = fc.film_id where f.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String category = rs.getString("name");
				categories.add(category);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	public List<Film> findFilmByKeyWord(String keyWord) {
		List<Film> films = new ArrayList<>();
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "select * from film f join language l on f.language_id = l.id join film_category fc on fc.film_id = f.id join category c on c.id = fc.category_id where title like ? or description like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyWord + "%");
			stmt.setString(2, "%" + keyWord + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int releaseYear = rs.getShort("release_year");
				int languageID = rs.getInt("language_id");
				String language = rs.getString("name");
				int rentalDuration = rs.getInt("rental_duration");
				double rentalRate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				String category = rs.getString("name");
				film = new Film(filmId, releaseYear, language, languageID, rentalDuration, length, title, description,
						rating, specialFeatures, rentalRate, replacementCost, category);
				film.setActors(findActorsByFilmId(filmId));
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;

	}
	
	public List<Film> listAll() {
		List<Film> films = new ArrayList<>();
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "select * from film f join language l on f.language_id = l.id join film_category fc on fc.film_id = f.id join category c on c.id = fc.category_id";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int releaseYear = rs.getShort("release_year");
				int languageID = rs.getInt("language_id");
				String language = rs.getString("name");
				int rentalDuration = rs.getInt("rental_duration");
				double rentalRate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				String category = rs.getString("name");
				film = new Film(filmId, releaseYear, language, languageID, rentalDuration, length, title, description,
						rating, specialFeatures, rentalRate, replacementCost, category);
				film.setActors(findActorsByFilmId(filmId));
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;

	}


//		NEW CODE

	public Actor createActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO actor (first_name, last_name) " + " VALUES (?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newActorId = keys.getInt(1);
					actor.setId(newActorId);
					if (actor.getFilms() != null && actor.getFilms().size() > 0) {
						sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
						stmt = conn.prepareStatement(sql);
						for (Film film : actor.getFilms()) {
							stmt.setInt(1, film.getId());
							stmt.setInt(2, newActorId);
							updateCount = stmt.executeUpdate();
						}
					}
				}
			} else {
				actor = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting actor " + actor);
		}
		return actor;
	}

	public boolean saveActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "UPDATE actor SET first_name=?, last_name=? " + " WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			stmt.setInt(3, actor.getId());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				// Replace actor's film list
				sql = "DELETE FROM film_actor WHERE actor_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, actor.getId());
				updateCount = stmt.executeUpdate();
				sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
				stmt = conn.prepareStatement(sql);
				for (Film film : actor.getFilms()) {
					stmt.setInt(1, film.getId());
					stmt.setInt(2, actor.getId());
					updateCount = stmt.executeUpdate();
				}
				conn.commit(); // COMMIT TRANSACTION
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	public boolean deleteActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "DELETE FROM film_actor WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actor.getId());
			int updateCount = stmt.executeUpdate();
			sql = "DELETE FROM actor WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actor.getId());
			updateCount = stmt.executeUpdate();
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}
	
	@Override
	public Film createFilm(Film film) {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);

			String sql = "INSERT INTO film ( title, description,release_year, language_id, rental_duration, "
					+ " rental_rate, length, replacement_cost, rating, special_features) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement prepStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prepStmt.setString(1, film.getTitle());
			prepStmt.setString(2, film.getDescription());
			prepStmt.setInt(3, film.getReleaseYear());
			prepStmt.setInt(4, film.getLanguageID());
			prepStmt.setInt(5, film.getRentalDuration());
			prepStmt.setDouble(6, film.getRentalRate());
			prepStmt.setInt(7, film.getLength());
			prepStmt.setDouble(8, film.getReplacementCost());
			prepStmt.setString(9, film.getRating());
			prepStmt.setString(10, film.getSpecialFeatures());

			int updateCount = prepStmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = prepStmt.getGeneratedKeys();
				if (keys.next()) {
					int newActorId = keys.getInt(1);
					film.setId(newActorId);
				}
				 
				keys.close();
			}
			conn.commit();
			System.out.println("Commit******************");

			prepStmt.close();
			conn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			film = null;
			if (conn != null) {
				try {
					conn.rollback();
					System.out.println("Rollback**********************");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return film;
	}
	
	

	public Film deleteFilm(Film film) {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "DELETE FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, film.getId());
			int updateCount = stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			while (keys.next()) {
				System.out.println("Delete Film ID " + keys.getInt(1));
			}
			
			conn.commit(); // COMMIT TRANSACTION
			
			stmt.close();
			conn.close();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			film = null;
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return null;
		}
		return film;
	}

	public boolean editFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "UPDATE film SET title=?, description=?, release_year=?, language_id=?, rental_duration=?, rental_rate=?, length=?, replacement_cost=?, rating=?, special_features=? WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageID());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());
			stmt.setInt(11, film.getId());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				// Replace actor's film list
//				sql = "DELETE FROM film_actor WHERE film_id = ?";
//				stmt = conn.prepareStatement(sql);
//				stmt.setInt(1, film.getId());
//				updateCount = stmt.executeUpdate();
//				sql = "INSERT INTO film_actor (actor_id, film_id) VALUES (?,?)";
//				stmt = conn.prepareStatement(sql);
//				for (Actor actor : film.getActors()) {
//					stmt.setInt(1, actor.getId());
//					stmt.setInt(2, film.getId());
//					updateCount = stmt.executeUpdate();
//				}
				conn.commit(); // COMMIT TRANSACTION
			} else {
				film = null;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}


}
