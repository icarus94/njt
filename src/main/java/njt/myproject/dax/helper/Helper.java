package njt.myproject.dax.helper;

import njt.myproject.dax.models.Task;

public class Helper {
    public String getPanelColor(Task task) {
        if (task == null)
            return null;
        if (task.getDone() != 1) {
            if (task.getPriority() == 1) {
                return "panel-default";
            } else if (task.getPriority() == 3) {
                return "panel-danger";
            } else {
                return "panel-warning";
            }
        } else {
            return "panel-success";
        }
    }

    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }
}
