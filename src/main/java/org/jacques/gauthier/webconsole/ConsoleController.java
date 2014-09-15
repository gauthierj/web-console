package org.jacques.gauthier.webconsole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/console")
public class ConsoleController {

    private ExecutionService executionService;
    private String command;
    private String result;

    @RequestMapping()
    public String view() {
        return "console";
    }

    @RequestMapping("/execute")
    public String execute(String command) {
        this.command = command;
        this.result = executionService.execute(command);
        return "redirect:/console/";
    }

    public String getCommand() {
        return command;
    }

    public String getResult() {
        return result;
    }

    @ModelAttribute("controller")
    public ConsoleController getController() {
        return this;
    }

    @Autowired
    public void setExecutionService(ExecutionService executionService) {
        this.executionService = executionService;
    }
}