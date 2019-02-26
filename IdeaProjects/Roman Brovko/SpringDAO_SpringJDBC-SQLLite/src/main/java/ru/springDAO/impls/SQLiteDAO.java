package ru.springDAO.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import ru.springDAO.interfaces.MP3Dao;
import ru.springDAO.objects.Author;
import ru.springDAO.objects.MP3;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component("sqliteDAO_1")
public class SQLiteDAO implements MP3Dao {

    private static final String mp3Table = "mp3";
    private static final String mp3View = "mp3_view";

    private NamedParameterJdbcTemplate namedJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert MP3Insert;
    private SimpleJdbcInsert authorInsert;

    @Autowired
    public void setDataSource(DataSource ds) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(ds);
        this.jdbcTemplate = new JdbcTemplate(ds);
//        this.MP3Insert = new SimpleJdbcInsert(ds).withTableName("mp3").usingColumns("name", "author");
//        this.authorInsert = new SimpleJdbcInsert(ds).withTableName("author").usingColumns("name");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int insertMP3(MP3 mp3) {

        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());

        int author_id = insertAuthor(mp3.getAuthor());
//
        String sql_mp3 = "INSERT INTO mp3 (author_id, name) VALUES(:author_id, :mp3Name)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author_id", author_id);
        params.addValue("mp3Name", mp3.getName());

        return namedJdbcTemplate.update(sql_mp3, params);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertAuthor(Author author) {

        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());

        String sql_author = "INSERT INTO author (name) VALUES(:authorName)";

        KeyHolder authorKeyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorName", author.getName());

        namedJdbcTemplate.update(sql_author, params, authorKeyHolder);

        int author_id = authorKeyHolder.getKey().intValue();

        return author_id;
    }

    @Override
    public int insertList(List<MP3> listMP3) {
//        String sql = "INSERT INTO "+ mp3Table +" (name, author) VALUES (:name, :author)";
//        SqlParameterSource[] paramsBatch = SqlParameterSourceUtils.createBatch(listMP3.toArray());
//        int[] updateCount = namedJdbcTemplate.batchUpdate(sql, paramsBatch);
//        return  updateCount.length;

        int i = 0;
        for (MP3 mp3 : listMP3) {
            insertMP3(mp3);
            i++;
        }

        return i;
    }

    public void insetWithStandartJDBC(MP3 mp3) {

//        Connection conn = null;
//
//        try {
//            Class.forName("org.sqlite.JDBC");
//            String url = "jdbc:sqlite:db/MyDB.db";
//            conn = DriverManager.getConnection(url, "", "");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        String sql = "INSERT INTO mp3 (name, author) VALUES (?, ?)";
//
//        try {
//            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setString(1, mp3.getName());
//            pst.setString(1, mp3.getAuthor());
//            pst.executeUpdate();
//            pst.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            if(conn != null){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

    }

    public void insertListSongs(List<MP3> allMP3) {
        for (MP3 mp3 : allMP3) {
            this.insertMP3(mp3);
        }
    }

    @Override
    public void deleteMP3(int id) {
        String sql = "DELETE FROM " + mp3Table + " WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        namedJdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteAuthor(int id) {
        String sql = "DELETE FROM author WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        namedJdbcTemplate.update(sql, params);
    }

    @Override
    public MP3 getMP3ById(int id) {
        String sql = "SELECT * FROM " + mp3Table + " WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return namedJdbcTemplate.queryForObject(sql, params, new MP3RowMapper());
    }

    @Override
    public List<MP3> getMP3ListByName(String name) {
        String sql = "SELECT * FROM " + mp3Table + " WHERE UPPER(name) like :name";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", "%" + name.toUpperCase() + "%");

//        String param = "%"+ name.toUpperCase() +"%";

        return namedJdbcTemplate.query(sql, params, new MP3RowMapper());
    }

    @Override
    public List<MP3> getMP3ListByAuthor(String author) {
        String sql = "SELECT * FROM " + mp3Table + " WHERE UPPER(author) like :author";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author", "%" + author.toUpperCase() + "%");

//        String param = "%" + author.toUpperCase() + "%";

        return namedJdbcTemplate.query(sql, params, new MP3RowMapper());
    }

    @Override
    public int getCountMP3() {
        String sql = "SELECT COUNT(*) FROM " + mp3Table;

        return namedJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
    }

    @Override
    public void deleteByName(String name) {
        String sql = "DELETE FROM " + mp3Table + " WHERE UPPER(name) like :name";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", "%" + name.toUpperCase() + "%");

        String param = "%" + name.toUpperCase() + "%";

        namedJdbcTemplate.update(sql, params);
    }

    @Override
    public Map<String, Integer> getStat() {
        String sql = "SELECT author_name, COUNT(mp3_name) as count FROM " + mp3View + " GROUP BY author_name";

        return namedJdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Map<String, Integer> map = new TreeMap<>();
                while (resultSet.next()) {
                    String author = resultSet.getString("author_name");
                    Integer countMP3 = resultSet.getInt("count");
                    map.put(author, countMP3);
                }
                return map;
            }
        });
    }

    @Override
    public void updateMP3ByNameSong(String oldName, String newName, String newAuthor) {
//        String sql = "UPDATE mp3 SET name = :newName, author = :newAuthor WHERE name = :oldName";
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("newName", newName);
//        parameterSource.addValue("newAuthor", newAuthor);
//        parameterSource.addValue("oldName", "%"+ oldName +"%");
//        jdbcTemplate.update(sql, parameterSource);
        String sql = "UPDATE " + mp3Table + " SET name = ?, author = ? WHERE name = ?";
        jdbcTemplate.update(sql, new Object[]{newName, newAuthor, oldName});
    }

    private static final class MP3RowMapper implements RowMapper<MP3> {
        @Override
        public MP3 mapRow(ResultSet resultSet, int i) throws SQLException {
            Author author = new Author();
            author.setId(resultSet.getInt("author_id"));
            author.setName(resultSet.getString("author_name"));

            MP3 mp3 = new MP3();
            mp3.setId(resultSet.getInt("mp3_id"));
            mp3.setName(resultSet.getString("mp3_name"));
            mp3.setAuthor(author);
            return mp3;
        }
    }
}
