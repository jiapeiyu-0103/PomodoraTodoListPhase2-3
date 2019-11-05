package parsers;



import model.DueDate;
import model.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import persistence.Jsonifier;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class TestTaskParser {

    private TaskParser parser = new TaskParser();

    @Test
    void testFewTaskForChecking() {
//        Task task = new Task("a");
//        List<Task> taskList = new ArrayList<>();
//        taskList.add(task);
//        Jsonifier.taskListToJson(taskList);
        System.out.println(parser.parse("[\n" +
                "{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "}]"));
    }

    @Test
    void testUrgentAndNotImportant() {
        System.out.println(parser.parse("[\n" +
                "{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":false},\n" +
                "  \"status\":\"DONE\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "}]"));
    }

    @Test
    void testNotUrgentAndImportant() {
        System.out.println(parser.parse("[\n" +
                "{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":false,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "}]"));
    }

    @Test
    void testNotUrgentAndNotImportant() {
        System.out.println(parser.parse("[\n" +
                "{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "}]"));
    }

    @Test
    void testTaskParser() {
        System.out.println(parser.parse("[\n" +
                "{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Read the syllabus! \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"DONE\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Make note of assignments deadlines. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"},{\"name\":\"assigns\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":17,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":false,\"urgent\":true},\n" +
                "  \"status\":\"TODO\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Make note of quizzes and midterm dates. \",\n" +
                "  \"tags\":[{\"name\":\"exams\"},{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":22,\"hour\":17,\"minute\":10},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":false},\n" +
                "  \"status\":\"TODO\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Update my calendar with those dates. \",\n" +
                "  \"tags\":[{\"name\":\"planning\"},{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":20,\"hour\":17,\"minute\":10},\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"TODO\"\n" +
                "}\n" +
                "]"));
    }

    @Test
    void testDescriptionSpellWrong() {
        parser.parse("[\n" +
                "{\n" +
                "  description:\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "}]");
    }
    @Test
    void testDescriptionIsNotContained() {
        parser.parse("[\n" +
                "{\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "}]");
    }

    @Test
    void testTagIsNotContained() {
        parser.parse("[\n" +
                "{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "}]");
    }


    @Test
    void testPriorityIsNotContained() {
        parser.parse("[\n" +
                "{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "}]");
    }

    @Test
    void testImportantIsNotBoolean() {
        parser.parse("[\n" +
                "{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":good,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "}]");
    }

    @Test
    void testUrgentIsNotBoolean() {
        parser.parse("[\n" +
                "{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":false,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":good},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "}]");
    }

    @Test
    void testImportantAndUrgentAreNotBoolean() {
        parser.parse("[\n" +
                "{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":good,\"urgent\":chill},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "}]");
    }

    @Test
    void testStatusIsNotContained() {
        parser.parse("[\n" +
                "{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":false,\"urgent\":true},\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "}]");
    }


    @Test
    void testYearIsNotInt() {
        System.out.println(parser.parse("[\n" +
                "{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":\"2019\",\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "}]"));
    }

    @Test
    void testDueDateIsNotInSide() {
        System.out.println(parser.parse("[\n" +
                "{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":happy,\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},\n" +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "}]"));
    }

    @Test
    void testTaskSize () {
        Task task = new Task("test");
        Task task1 = new Task("no");
        JSONObject jsonObject = Jsonifier.taskToJson(task);
        JSONObject jsonObject2 = Jsonifier.taskToJson(task1);
        jsonObject.put("priority","happy");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        jsonArray.put(jsonObject2);
        assertEquals(1,parser.parse(jsonArray.toString()).size());
    }

    @Test
    void testYearIsNotInteger () {
        Task task = new Task("test");
        Task task1 = new Task("no");
        JSONObject jsonObject = Jsonifier.taskToJson(task);
        JSONObject jsonObject2 = Jsonifier.taskToJson(task1);
        Calendar dueTime = new GregorianCalendar();
        DueDate dueDate = new DueDate(dueTime.getTime());
        JSONObject dueDateJson = Jsonifier.dueDateToJson(dueDate);
        dueDateJson.put("year","true");
        jsonObject.put("due-date",dueDateJson);
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        jsonArray.put(jsonObject2);
        assertEquals(1,parser.parse(jsonArray.toString()).size());
    }
}
