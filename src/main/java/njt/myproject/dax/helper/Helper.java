package njt.myproject.dax.helper;

import njt.myproject.dax.models.Task;

public class Helper {
    public String getPanelColor(Task task){
        if(task.getDone() != 1){
            if(task.getPriority() == 1){
                return "panel-default";
            }else if(task.getPriority() == 3){
                return "panel-danger";
            } else {
                return "panel-warning";
            }
        }else{
            return "panel-success";
        }
    }
}
