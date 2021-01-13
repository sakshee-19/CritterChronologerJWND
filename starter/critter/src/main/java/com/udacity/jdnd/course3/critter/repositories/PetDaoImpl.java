package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entites.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetDaoImpl implements PetDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    private static final String PETS_BY_OWNER_ID = "select * from pet where owner=:id";

    private static final String PETS_ALL = "select * from pet";

    private static final String PETS_BY_ID = "select * from pet where id=:id";

    private static final String INSERT_PET = "insert into pet (type, owner, name, birthDate, notes) values (:type, :owner, :name, :birthDate, :notes)";

    @Override
    public List<Pet> findAllPetOwnerHas(Long ownerId) {
        return jdbcTemplate.query(PETS_BY_OWNER_ID,
                new MapSqlParameterSource().addValue("id", ownerId),
                new BeanPropertyRowMapper<>(Pet.class));
    }

    @Override
    public List<Pet> findAllPets() {
        return jdbcTemplate.query(PETS_ALL,
                new BeanPropertyRowMapper<>(Pet.class));
    }

    @Override
    public Pet findPetById(Long id) {
        return jdbcTemplate.queryForObject(PETS_BY_ID,
                new MapSqlParameterSource().addValue("id", id),
                new BeanPropertyRowMapper<>(Pet.class));
    }

    @Override
    public Long savePet(Pet pet) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(
                INSERT_PET,
                new BeanPropertySqlParameterSource(pet),
                key
        );
        return key.getKey().longValue();
    }


}
