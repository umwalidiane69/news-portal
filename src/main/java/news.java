import java.util.List;
import org.sql2o.*;
public class news extends Company{
    private  String departmentn;
    private  String general;

    public news(String general, String departmentn){
        this.general = general;
        this.departmentn = departmentn;
    }

    public String getGeneral() {
        return general;
    }

    public String getDepartmentn() {
        return departmentn;
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO new(departmentn,general) VALUES (:departmentn,:general)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("general", this.general)
                    .addParameter("departmentn", this.departmentn)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Company> all() {
        String sql = "SELECT * FROM new";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Company.class);
        }
    }
    }
