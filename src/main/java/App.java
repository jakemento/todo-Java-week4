import java.util.*;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String [] args){
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/tasks", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Task> tasks = Task.all();
      model.put("tasks", tasks);
      model.put("template", "templates/tasks.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/tasks", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String description = request.queryParams("description");
      Task newTask = new Task(description);
      newTask.save();
      response.redirect("/tasks");
      return null;
    });

    get("/categories", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Category> categories = Category.all();
      model.put("categories", categories);
      model.put("template", "templates/categories.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/categories", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Category newCategory = new Category(name);
      newCategory.save();
      response.redirect("/categories");
      return null;
    });

    get("/tasks/:id", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Task task = Task.find(id);
      model.put("task", task);
      model.put("allCategories", Category.all());
      model.put("template", "templates/task.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/categories/:id", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Category category = Category.find(id);
      model.put("category", category);
      model.put("allTasks", Task.all());
      model.put("template", "templates/category.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/add_tasks", (request, response) -> {
      int taskId = Integer.parseInt(request.queryParams("task_id"));
      int categoryId = Integer.parseInt(request.queryParams("category_id"));
      Category category = Category.find(categoryId);
      Task task = Task.find(taskId);
      category.addTask(task);
      response.redirect("/categories/" + categoryId);
      return null;
    });

    post("/add_categories", (request, response) -> {
      int taskId = Integer.parseInt(request.queryParams("task_id"));
      int categoryId = Integer.parseInt(request.queryParams("category_id"));
      Category category = Category.find(categoryId);
      Task task = Task.find(taskId);
      task.addCategory(category);
      response.redirect("/tasks/" + taskId);
      return null;
    });


    post("/tasks/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Task task = Task.find(id);
      task.delete();
      response.redirect("/tasks");
      return null;
    });

    post("/categories/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Category category = Category.find(id);
      category.delete();
      response.redirect("/categories");
      return null;
    });


    get("/categories/:id/edit", (request, response) -> {
     HashMap<String, Object> model = new HashMap<String, Object>();

     Category thisCategory = Category.find(Integer.parseInt(request.params("id")));

     model.put("category", thisCategory);
     model.put("allTasks", Task.all());
     model.put("template", "templates/updateCategory.vtl");
     return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

   post("/categories/:id/change-name", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Category thisCategory = Category.find(Integer.parseInt(request.params("id")));
    thisCategory.update(request.queryParams("newname"));
    response.redirect("/categories/" + thisCategory.getId());
    return null;
      });

    get("/tasks/:id/edit", (request, response) -> {
     HashMap<String, Object> model = new HashMap<String, Object>();

     Task thisTask = Task.find(Integer.parseInt(request.params("id")));
     model.put("task", thisTask);
     model.put("allCategories", Category.all());
     model.put("template", "templates/updateTask.vtl");
     return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

   post("/tasks/:id/change-description", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    Task thisTask = Task.find(Integer.parseInt(request.params("id")));
    thisTask.update(request.queryParams("newDescription"));
    response.redirect("/tasks/" + thisTask.getId());
    return null;
      });


  }
}
