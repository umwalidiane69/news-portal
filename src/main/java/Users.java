
import java.util.List;
import org.sql2o.*;

public class Users{
    private String user_name;
    private String position;
    private String roles;
    private String department;


    public Users(String user_name, String roles, String position, String department){
        this.user_name = user_name;
        this.department = department;
        this.position = position;
        this.roles = roles;
    }

   public int id;

    public String getUser_name() {
        return user_name;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getRoles() {
        return roles;
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO users (user_name,roles,position,department) VALUES (:user_name,:roles,:position,:department";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("user_name", this.user_name)
                    .addParameter("department", this.department)
                    .addParameter("position", this.position)
                    .addParameter("roles", this.roles)
//                    .bind(sql)
//                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Users> all() {
        String sql = "SELECT * FROM users";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Users.class);
        }
        }

}
