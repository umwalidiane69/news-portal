import org.sql2o.Sql2o;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class App {
    public static void main(String[] args){
//        staticFileLocation("/public");
        get("/", (request, response) -> {
            Map<String, Object>  model= new HashMap();
            return new ModelAndView (model,"news.hbs");
        }, new HandlebarsTemplateEngine());

        post("/hope/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String departmentn = request.queryParams("departmentn");
            String general = request.queryParams("general");

            news animal= new news(departmentn,general);
            animal.save();
            model.put("animal", animal);
            model.put("departmentn", departmentn);
            model.put("general", general);


            return new ModelAndView(model, "Allnews.hbs");
        }, new HandlebarsTemplateEngine());
        get("/users/new", (request, response) -> {
            Map<String, Object> model = new HashMap();
            return new ModelAndView(model, "user.hbs");
        }, new HandlebarsTemplateEngine());
        post("/users/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String user = request.queryParams("user_name");
            String position = request.queryParams("position");
            String role = request.queryParams("role");
            String department = request.queryParams("department");

            Users anima = new Users(user, position, role, department);
            anima.save();
            model.put("anima", anima);
            model.put("user", user);
            model.put("position", position);
            model.put("role", role);
            model.put("department", department);

            return new ModelAndView(model, "Allnews.hbs");
        }, new HandlebarsTemplateEngine());

        get("/depart/new", (request, response) -> {

            Map<String, Object> model = new HashMap();
            return new ModelAndView(model, "department.hbs");
        }, new HandlebarsTemplateEngine());
        post("/depart/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            String emplonbr = request.queryParams("emplonbr");
            Department nina= new Department(name,description,emplonbr);
            nina.save();
            model.put("nina", nina);
            model.put("name", name);
            model.put("description", description);

            model.put("emplonbr", emplonbr);
            return new ModelAndView(model, "Allnews.hbs");
        }, new HandlebarsTemplateEngine());


//        get("/users/new", (request, response) -> {
//            List<Users> user = Users.all();
//            Map<String, Object> model = new HashMap();
//            return new ModelAndView(model, "user.hbs");
//        }, new HandlebarsTemplateEngine());

    }
}
