package service.migration;

import entity.ExtraSection;
import org.apache.logging.log4j.Level;
import service.exception.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ExtraSectionMigrationService extends Migration<ExtraSection> {
    public static final ExtraSectionMigrationService INSTANCE = new ExtraSectionMigrationService();

    private final String TABLE_NAME = "extra_sections";

    private ExtraSectionMigrationService() {}

    @Override
    public int migrate(List<ExtraSection> extraSections) throws ServiceException {
        Connection connection = retrieveConnection();

        LOGGER.log(Level.INFO, String.format(START_MIGRATION_LOG_FORMAT, TABLE_NAME));

        int migratedEntitiesCount = 0;
        for (ExtraSection extraSection : extraSections) {
            migratedEntitiesCount += migrate(extraSection, connection) ? 1 : 0;
        }

        LOGGER.log(Level.INFO, String.format(COMPLETE_MIGRATION_LOG_FORMAT,
                TABLE_NAME, migratedEntitiesCount, extraSections.size()));

        return migratedEntitiesCount;
    }

    @Override
    protected boolean migrate(ExtraSection extraSection, Connection connection) {
        final String INSERT_QUERY = "insert into " + TABLE_NAME + "(id, name) values (?, ?)";
        boolean migrated = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, extraSection.getId());
            preparedStatement.setString(2, extraSection.getName());
            preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, String.format(SUCCESSFUL_MIGRATION_LOG_FORMAT, extraSection));
            migrated = true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, e.getMessage());
        }
        return migrated;
    }
}
