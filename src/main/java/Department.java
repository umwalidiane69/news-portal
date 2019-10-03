import java.util.List;
import org.sql2o.*;
public class Department extends Company {
    private String name;
    private String description;
    private String employeeNbr;


    public Department(String name, String description, String employeeNbr){
        this.name = name;
        this.description = description;
        this.employeeNbr = employeeNbr;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEmployeeNbr() {
        return employeeNbr;
    }
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO department (name,description,emplonbr) VALUES (:name,:description,:employeeNbr)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("description", this.description)
                    .addParameter("employeeNbr", this.employeeNbr)
//                    .addParameter("id", this.id)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Department> all() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM department;";

            return con.createQuery(sql)
                    .executeAndFetch(Department.class);
        }
    }
}