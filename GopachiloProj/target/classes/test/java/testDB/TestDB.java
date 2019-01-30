package testDB;

import libs.Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDB {
    static Database dbMySgl;
    static Logger logger=Logger.getLogger(TestDB.class);

    @Before
    public  void setUp() throws SQLException, IOException, ClassNotFoundException {
        dbMySgl=new Database("MySQL_PADB_DB","MySQL");
        logger.info("MySql DB conected");

    }
    @After
    public void  tearDown() throws SQLException {
        dbMySgl.quit();
    }

  @Test
    public void testDB() throws SQLException {
      List<ArrayList> dataFromSeleniumTable=dbMySgl.selectTable("select * from seleniumTable where login= 'gapachilo'");
//      dbMySgl.changeTable("INSERT INTO seleniumTable VALUES (9, 'gapachilo','pass')");
//      dataFromSeleniumTable=dbMySgl.selectTable("select * from seleniumTable where login= 'gapachilo'");

      for (ArrayList line :
              dataFromSeleniumTable) {
          logger.info(line);
      }
//      logger.info(dataFromSeleniumTable);
  }


}
