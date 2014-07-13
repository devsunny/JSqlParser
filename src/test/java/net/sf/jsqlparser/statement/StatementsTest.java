package net.sf.jsqlparser.statement;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.Select;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author toben
 */
public class StatementsTest {
    
    public StatementsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Statements.
     */
    @Test
    public void testStatements() throws JSQLParserException {
        String sqls = "select t1.$1, t1.$2 from mytable t1 join another_table t2 on t1.$1=t2.$3;";
        Statements parseStatements = CCJSqlParserUtil.parseStatements(sqls);
        System.out.println(parseStatements.toString());
        Statement stmt = parseStatements.getStatements().get(0);
        System.out.println(stmt.toString());
        
        
    }
    
}
