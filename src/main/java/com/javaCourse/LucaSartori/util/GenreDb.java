package com.javaCourse.LucaSartori.util;

import java.util.HashSet;
import java.util.Set;

import com.javaCourse.LucaSartori.models.Genre;

public class GenreDb {

	private static GenreDb instance = null;
	private Set<Genre> genres = new HashSet<Genre>();
	
	private GenreDb() {
		// empty, singleton class
	}
	
	public static GenreDb getInstance() {
		if (instance == null) {
			instance = new GenreDb();
		}
		return instance;
	}
	
	public boolean addGenre(Genre toAdd) {
		if (genres.contains(toAdd)) {
			System.out.println("Genre Already Exists");
			return false;
		}
		else {
			System.out.println("Genre added");
			genres.add(toAdd);
			return true;
		}
	}
	
	public boolean removeGenre(String toRemove) {
		for (Genre gen : genres) {
			if (gen.getName().compareTo(toRemove) == 0) {
				genres.remove(gen);
				System.out.println("Genre Removed");
				return true;
			}
		}
			System.out.println("Genre does not exists");
			return false;
	}
	
	public Genre getGenre(String name) {
		for (Genre gen : genres) {
			if (gen.getName().compareTo(name) == 0) {
				return gen;
			}
		}
		return null;
	}
	
	public boolean print() {
		if (!genres.isEmpty()) {
			for (Genre genre : genres) {
				genre.print();
				System.out.println(" ");
			}
			return true;
		}
		else {
			System.out.println("There are not any Genres");
			return false;
		}
	}
	
	public boolean genreHasComics() {
		for (Genre gen : genres) {
			if (gen.getCantComics() > 0) {
				return true;
			}
		}
		return false;
	}
	
}
