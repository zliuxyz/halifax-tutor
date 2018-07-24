package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteActivationCodeByValueSQLOperation extends SQLOperationTemplate {
    public DeleteActivationCodeByValueSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "SELECT DeleteActivationCodeByValue(?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String activationCode = (String) getParameters().get(0);
        ps.setString(1, activationCode);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getBoolean(1);
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
