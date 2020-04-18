package academy.learnprogramming.controller;

import academy.learnprogramming.model.TodoData;
import academy.learnprogramming.model.TodoItem;
import academy.learnprogramming.service.TodoItemService;
import academy.learnprogramming.util.AttributeNames;
import academy.learnprogramming.util.Mappings;
import academy.learnprogramming.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
// http://localhost:8080/todo-list/items
// http://localhost:8080/todo-list/add_item

@Controller
@Slf4j
public class TodoController {

    private TodoItemService service;

    @Autowired
    public TodoController(TodoItemService service) {
        this.service = service;
    }

    //== Handling requests ==
    @ModelAttribute("todoData")
    public TodoData todoData() {
        return service.getData();
    }


    @GetMapping(Mappings.ITEMS)
    public String items() {
        return ViewNames.ITEMS_LIST;
    }

    @GetMapping(Mappings.ADD_ITEM)
    public String addEditItem(@RequestParam(required = false, defaultValue = "-1") int id, Model model) {

        log.info("editing id={}", id);
        TodoItem todoItem = service.getItem(id);

        if(todoItem == null) {
            todoItem = new TodoItem("", "", LocalDate.now());
        }
        //binding this attribute to spring form inside add_item.jsp
        model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
        return ViewNames.ADD_ITEM;
    }


    /**
     * post-get pattern
     * after completing request will map to items_list.jsp
     *
     * @ModelAttribute is binding data from add_item.jsp form to create TodoItem object
     **/

    @PostMapping(Mappings.ADD_ITEM)
    public String processItem(@ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoItem) {
        log.info("todoItem from form = {}", todoItem);

        if (todoItem.getId() == 0) {
            service.addItem(todoItem);
        } else {
            service.updateItem(todoItem);
        }
        return "redirect:/" + Mappings.ITEMS;
    }

    /**
     * Extracting parameter from link to proceed deletion
     * Redirect back to the item list
     */
    @GetMapping(Mappings.DELETE_ITEM)
    public String deleteItem(@RequestParam int id) {
        log.info("Deleting item with id={}", id);
        service.removeItem(id);
        return "redirect:/" + Mappings.ITEMS;
    }

    @GetMapping(Mappings.VIEW_ITEM)
    public String viewItem(@RequestParam int id, Model model) {
        log.info("Viewing item with id={}", id);
        model.addAttribute(AttributeNames.TODO_ITEM, service.getItem(id));
        return ViewNames.VIEW_ITEM;
    }


}
