package hr.tvz.miholic.hardwareapp.Repository;


import hr.tvz.miholic.hardwareapp.Classes.Hardware;
import hr.tvz.miholic.hardwareapp.Enums.HardwareTypeEnum;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Primary
@Repository
public class JdbcHardwareRepository implements HardwareRepository {
    private static final String SELECT_ALL = "SELECT id, name, code, price, type, amount FROM hardware";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public JdbcHardwareRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("hardware")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public Optional<Hardware> save(Hardware hardware) {
        return Optional.empty();
    }

    @Override
    public List<Hardware> findAll() {
        return List.copyOf(jdbc.query(SELECT_ALL, this::mapRowToHardware));
    }

    @Override
    public Optional<Hardware> getByCode(String code) {

        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE code = ?", this::mapRowToHardware, code)
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Hardware> findByType(String type) {

            return List.copyOf(jdbc.query(SELECT_ALL + " WHERE type = ?", this::mapRowToHardware, type)
            );

    }

    @Override
    public Optional<Hardware> delete(String code) {
        return Optional.empty();
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        return Optional.empty();
    }

    @Override
    public Optional<Hardware> update(String code, Hardware hardware) {
        return Optional.empty();
    }

    @Override
    public Optional<Hardware> patchPrice(String code, Double price) {
        return Optional.empty();
    }
    private Hardware mapRowToHardware(ResultSet rs, int nRow) throws SQLException{
        return new Hardware(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("code"),
                rs.getDouble("price"),
                HardwareTypeEnum.valueOf(rs.getString("type")),
                rs.getInt("amount")
        );
    }
}
