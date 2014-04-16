package hub;

import com.unboundid.ldap.sdk.LDAPException;
import org.jooq.*;
import org.jooq.impl.DSL;
import pan.auditdb.Tables;
import things.exceptions.ThingException;
import things.exceptions.ValueException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Project: hub
 * <p/>
 * Written by: Markus Binsteiner
 * Date: 12/03/14
 * Time: 10:56 AM
 */
public class JooqTest {
    

	public static void main(String[] args) throws ValueException, ThingException, LDAPException, InterruptedException {

//        AbstractApplicationContext context = new AnnotationConfigApplicationContext(HubConfig.class);

//        MongoOperations mo = (MongoOperations) context.getBean("mongoTemplate");
//        ThingControl tc = (ThingControl) context.getBean("thingControl");


        Connection conn = null;

        String userName = "markus";
        String password = "password";
        String url = "jdbc:mysql://localhost:3306/pandora_audit";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            //Result<Record> result = create.select().from(Tables.AUDIT_USER_PREV)
//                    .where(Tables.AUDIT_USER_PREV.USER.equal("mbin029")).fetch();


            Result<Record3<String, Integer, Integer>> result = create.select(Tables.AUDIT.USER, Tables.AUDIT.START, Tables.AUDIT.DONE).from(Tables.AUDIT)
                    .where(Tables.AUDIT.USER.equal("sgui011")).fetch();
//                    .where(Tables.AUDIT.USER.equal("mbin029")).fetch();

            long time = 0;
            long jobs = 0;

            for (Record r : result) {
                //Long id = r.getValue(Tables.AUDIT.field("id"));
                //String firstName = r.getValue();
                //String lastName = r.getValue(AUTHOR.LAST_NAME);
                String user = r.getValue(Tables.AUDIT.USER);
//                Long walltime = r.getValue(Tables.AUDIT.WALLTIME);
                Integer start = r.getValue(Tables.AUDIT.START);
                Integer done = r.getValue(Tables.AUDIT.DONE);

                int delta = done-start;
                time = time + (delta);
                jobs = jobs + 1;
                System.out.println(user+": "+delta);
                //System.out.println("ID: " + id + " first name: " + firstName + " last name: " + lastName);
            }
            System.out.println("Total: "+jobs+" / "+time);
        } catch (Exception e) {
            // For the sake of this tutorial, let's keep exception handling simple
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignore) {
                }
            }
        }

	}




}
