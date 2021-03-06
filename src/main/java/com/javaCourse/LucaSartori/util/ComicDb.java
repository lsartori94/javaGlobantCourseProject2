package com.javaCourse.LucaSartori.util;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;

import com.javaCourse.LucaSartori.models.Comic;


public class ComicDb {
	private static ComicDb instance = null;
	private Map<String, Comic> comics = new HashMap<String, Comic>();
	
	private ComicDb() {
		// not used, singleton class
	}
	
	public static ComicDb getInstance() {
		if (instance == null) {
			instance = new ComicDb();
		}
		return instance;
	}
	
	public boolean addComic(Comic toAdd) {
		if (comics.containsKey(toAdd.getName())) {
			System.out.println("Comic Already Exists");
			System.out.println("Do you want to add the copies? y/n");
			boolean prompt = true;
			@SuppressWarnings("resource")
			Scanner confirm = new Scanner(System.in);
			while (prompt) {
				try {
					String c = confirm.next().toUpperCase();
					if (c.compareTo("Y") == 0) {
						// Add to existing comic new copies
						int copies = comics.get(toAdd.getName()).getCantCopies();
						int copiesToAdd = toAdd.getCantCopies()+copies;
						comics.get(toAdd.getName()).setCantCopies(copiesToAdd);
						int toRender = comics.get(toAdd.getName()).getToRender();
						comics.get(toAdd.getName()).setToRender(toRender+toAdd.getCantCopies());
						System.out.println("Copy added");
						prompt = false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Invalid Input");
				}
			}
			return false;
		}
		else {
			System.out.println("Comic added");
			comics.put(toAdd.getName(), toAdd);
			return true;
		}
	}
	
	public boolean removeComic(String toRemove) {
		boolean ret = false;
		if (comics.get(toRemove) != null) {
			if (comics.get(toRemove).getCantCopies() > 1) {
				System.out.println("Comic has more than 1 copy. Remove All? y/n");
				boolean prompt = true;
				@SuppressWarnings("resource")
				Scanner confirm = new Scanner(System.in);
				while (prompt) {
					try {
						String c = confirm.next().toUpperCase();
						if (c.compareTo("Y") == 0) {
							comics.remove(toRemove);
							System.out.println("Comic and copies removed");
							prompt = false;
							ret = true;
						}
						else {
							System.out.println("Removal Aborted");
							ret = false;
						}
					}
					catch (Exception e) {
						System.out.println("Invalid Input");
					}
				}
			}
			else {
				comics.remove(toRemove);
				System.out.println("Comic removed");
				ret = true;
			}
		}
		else {
			System.out.println("Comic does not exists");
			ret = false;
		}
		return ret;
	}

	public Comic getComic(String name) {
		return comics.get(name);
	}
	
	public Set<Comic> getGenre(String genre) {
		Set<Comic> comicsOut = new TreeSet<Comic>();
		for (Map.Entry<String, Comic> com : comics.entrySet()) {
			if (com.getValue().getGenre().compareTo(genre) == 0) {
				comicsOut.add(com.getValue());
			}
		}
		return comicsOut;
	}
	
	public boolean printComics() {
		if (!comics.isEmpty()) {
			for (Map.Entry<String, Comic> comic : comics.entrySet()) {
				comic.getValue().print();
				System.out.println(" ");
			}
			return true;
		}
		else {
			System.out.println("There are not any Comics");
			return false;
		}
	}
	
}
