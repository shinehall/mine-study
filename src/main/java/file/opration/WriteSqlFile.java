package file.opration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class WriteSqlFile {

  public static void main(String[] args) throws Exception {
    File file = new File("deleteTable.sql");
    OutputStream out = new FileOutputStream(file);
    String sql = deleteACTUserDetailTable();
    out.write(sql.getBytes());
    out.close();
  }
  
  private static String createACTUserDetailTable(){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < 32; i++){
      sb.append("CREATE TABLE \"public\".\"data_stat_4active_user_detail" + i +"\"("
          + "\"org_id\" int8 NOT NULL,"
          + "\"date\" int4 NOT NULL,"
          + "\"user_id\" int8,"
          + "\"create_time\" int8,"
          + "\"user_type\" int4,"
          + "\"dept_id\" int8 NOT NULL,"
          + "\"id\" int8 DEFAULT nextval(\'data_stat_4active_user_detail_id_seq\'::regclass) "
          + "NOT NULL,PRIMARY KEY (\"id\"), "
          + "UNIQUE (\"org_id\", \"date\", \"user_id\")"
          + ")WITH (OIDS=FALSE);"
          + "ALTER TABLE \"public\".\"data_stat_4active_user_detail" + i + "\" OWNER TO \"gzq_analyse\";"
          + "COMMENT ON COLUMN \"public\".\"data_stat_4active_user_detail" + i + "\".\"org_id\" IS \'企业id\';"
          + "COMMENT ON COLUMN \"public\".\"data_stat_4active_user_detail" + i + "\".\"date\" IS \'统计日期\';"
              + "COMMENT ON COLUMN \"public\".\"data_stat_4active_user_detail" + i + "\".\"user_type\" IS \'1、不活跃，2：活跃\';"
              + "COMMENT ON COLUMN \"public\".\"data_stat_4active_user_detail" + i + "\".\"dept_id\" IS \'部门id\';"
              + "CREATE INDEX \"org_date_active_users_index_detail" + i + "\" ON \"public\".\"data_stat_4active_user_detail" + i + "\" USING btree (\"org_id\" \"pg_catalog\".\"int8_ops\", \"date\" \"pg_catalog\".\"int4_ops\");\n");
      
    }
    return sb.toString();
  }
  
  private static String deleteACTUserDetailTable(){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < 32; i++){
      sb.append("delete from data_stat_4active_user_detail" + i + " where date=20160406;\n");
    }
    return sb.toString();
  }

}
