package hr.tvz.miholic.hardwareapp.Hardware.Repository;


import hr.tvz.miholic.hardwareapp.Hardware.Classes.Hardware;
import hr.tvz.miholic.hardwareapp.Hardware.Enums.HardwareTypeEnum;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
        try {
            hardware.setId(saveHardwareDetail(hardware));
            return Optional.of(hardware);
        } catch (DuplicateKeyException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Hardware> findAll() {

        return jdbc.query(SELECT_ALL, this::mapRowToHardware);
    }

    @Override
    public List<Hardware> findByCode(String code) {
        code = code.toUpperCase();
        return jdbc.query(SELECT_ALL + " WHERE UPPER(name) LIKE '" + code + "%'", this::mapRowToHardware);

        //return jdbc.query(SELECT_ALL + " WHERE code LIKE '?%%'", this::mapRowToHardware, code);
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
    public void delete(String code) {
        jdbc.update("DELETE FROM hardware WHERE code = ?", code);
    }



    @Override
    public Optional<Hardware> update(String code, Hardware hardware) {
        return Optional.of(new Hardware());

    }

    @Override
    public Optional<Hardware> patchPrice(String code, Double price) {
        return Optional.empty();
    }

    private Hardware mapRowToHardware(ResultSet rs, int nRow) throws SQLException{
        return new Hardware(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("code"),
                rs.getDouble("price"),
                HardwareTypeEnum.valueOf(rs.getString("type")),
                rs.getInt("amount")
        );
    }
    private Long saveHardwareDetail(Hardware hardware) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", hardware.getName());
        values.put("code", hardware.getCode());
        values.put("price", hardware.getPrice());
        values.put("type", String.valueOf(hardware.getType()));
        values.put("amount", hardware.getAmount());

        return inserter.executeAndReturnKey(values).longValue();
    }
}
