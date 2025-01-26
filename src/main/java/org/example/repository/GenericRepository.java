package org.example.repository;

import org.example.domain.Entity;
import org.example.exceptions.IDNotUniqueException;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class GenericRepository <T extends Entity> implements RepositoryInterface<T> {

private Map<Integer, T> entities;

public GenericRepository() {
	this.entities = new HashMap<>();
}

@Override
public T save(T entity) throws IDNotUniqueException, FileNotFoundException {
	if (entities.containsKey(entity.getId())) {
		throw new IDNotUniqueException("ID " + entity.getId() + " already exists");
	}
	entities.put(entity.getId(), entity);
	return entity;
}


public T update(T entity) throws FileNotFoundException {
if (entities.containsKey(entity.getId())) {
	entities.put(entity.getId(), entity);
}
return entity;
}

public void delete(int entityId) throws FileNotFoundException {
	entities.remove(entityId);
}

public Iterable<T> findAll() {
	return entities.values();
}
public T findById(int entityId) {
	return entities.get(entityId);
}

}
