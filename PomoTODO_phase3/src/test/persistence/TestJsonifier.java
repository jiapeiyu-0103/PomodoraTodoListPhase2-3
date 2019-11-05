package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static persistence.Jsonifier.*;

public class TestJsonifier {


    @BeforeEach
    public void runBefore() {
        Jsonifier jsonifier = new Jsonifier();
    }

    @Test
    void testTaskJson() {
        Calendar time = new GregorianCalendar(2019,0,16,23,59);
        Task task = new Task("Register for courses. ");
        task.addTag("cpsc210");
        task.setDueDate(new DueDate(time.getTime()));
        task.setPriority(new Priority(1));
        task.setStatus(Status.IN_PROGRESS);
        System.out.println(taskToJson(task));
    }

    @Test
    void testTagJson() {
        Tag tag = new Tag("cpsc210");
        JSONObject tagToJson = Jsonifier.tagToJson(tag);
        assertEquals(tag.getName(),tagToJson.get("name"));
        System.out.println(tagToJson(tag));
    }

    @Test
    void testPriorityToJson() {
        Priority priority = new Priority(2);
        JSONObject priorityToJson = Jsonifier.priorityToJson(priority);
        assertEquals(priority.isImportant(),priorityToJson.get("important"));
        assertEquals(priority.isUrgent(),priorityToJson.get("urgent"));
        System.out.println(priorityToJson(priority));
    }

    @Test
    void testDueDateToJson() {
        Calendar time = new GregorianCalendar(2019,0,16,23,59);
        JSONObject dueDateToJson = Jsonifier.dueDateToJson(new DueDate(time.getTime()));
        assertEquals(2019,dueDateToJson.get("year"));
        assertEquals(0,dueDateToJson.get("month"));
        assertEquals(16,dueDateToJson.get("day"));
        assertEquals(23,dueDateToJson.get("hour"));
        assertEquals(59,dueDateToJson.get("minute"));
        System.out.println(dueDateToJson(new DueDate(time.getTime())));
    }


    @Test
    void testTaskListToJson() {
        Calendar time = new GregorianCalendar(2019,0,16,23,59);
        Task task1 = new Task("Register for courses. ");
        task1.addTag("cpsc210");
        task1.setDueDate(new DueDate(time.getTime()));
        task1.setPriority(new Priority(1));
        task1.setStatus(Status.IN_PROGRESS);

        Task task2 = new Task("Download the syllabus. ");
        task2.addTag("cpsc210");
        task2.setPriority(new Priority(4));
        task2.setStatus(Status.UP_NEXT);

        Calendar time3 = new GregorianCalendar(2019,0,17,23,59);
        Task task3 = new Task("Make note of assignments deadlines. ");
        task3.addTag("cpsc210");
        task3.addTag("assigns");
        task3.setDueDate(new DueDate(time3.getTime()));
        task3.setPriority(new Priority(3));
        task3.setStatus(Status.TODO);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        JSONArray tasksToJson = Jsonifier.taskListToJson(tasks);
        assertEquals(3,tasksToJson.length());
        System.out.println(taskListToJson(tasks));
    }
}
