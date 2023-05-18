package com.asankulov.post.PersonDAO;
import com.asankulov.post.Model.People;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PersonRepository {
    private final JdbcTemplate jdbcTemplate;

    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<People> print(){
       return jdbcTemplate.query("select * from people",new BeanPropertyRowMapper<>(People.class));
    }
    public People index(int id){
        return jdbcTemplate.query("select * from people where id=?",new Object[]{id},new BeanPropertyRowMapper<>(People.class)).
                stream().findAny().orElse(null);
    }
    public void save(People people){
        jdbcTemplate.update("insert into people(name,age,email) values(?,?,?)",people.getName(),people.getAge(),people.getEmail());
    }
    public void update(int id,People people){
        jdbcTemplate.update("update people set name=?,age=?,email=? where id=?",people.getName(),people.getAge(),people.getEmail(),id);
    }
    public void delete(int id){
        jdbcTemplate.update("delete from people where id=?",id);
    }
}