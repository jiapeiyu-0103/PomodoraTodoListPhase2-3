package model;

import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class TestTaskPhase3 {

    private Task task;
    private Task task2;
    private Calendar rightNow;
    private DueDate dueDate;
    private Tag tagOne;
    private Tag tagTwo;

    @BeforeEach
    void runBefore() {
      task = new Task("Task One");
      rightNow = Calendar.getInstance();
      dueDate = new DueDate(rightNow.getTime());
      task.setDueDate(dueDate);
      Priority p1 = new Priority(1);
      task.setPriority(p1);
      task.setStatus(Status.TODO);
      task2 = new Task("Task One");
      task2.setDueDate(dueDate);
      task2.setStatus(Status.TODO);
      task2.setPriority(p1);
      tagOne = new Tag("cpsc 110");
      tagTwo = new Tag("cpsc 210");
    }

    @Test
    void testExactlySameTaskCaseOne() {
        assertTrue(task.equals(task2));
    }

    @Test
    void testExactlySameTaskCaseTwo() {
        assertTrue(task2.equals(task));
    }

    @Test
    void testDifferentDescription() {
        task2.setDescription("Task Two");
        assertFalse(task.equals(task2));
    }

    @Test
    void testDifferentPriority() {
        Priority p2 = new Priority(2);
        task2.setPriority(p2);
        assertFalse(task.equals(task2));
    }

    @Test
    void testDifferentStatus() {
        task2.setStatus(Status.IN_PROGRESS);
        assertFalse(task.equals(task2));
    }

    @Test
    void testDifferentDueDate() {
        Calendar nextDay = Calendar.getInstance();
        DueDate dueDate2 = new DueDate(nextDay.getTime());
        dueDate2.postponeOneDay();
        task2.setDueDate(dueDate2);
        assertFalse(task.equals(task2));
    }

    @Test
    void testDifferentStatusAndDecr() {
        task2.setStatus(Status.IN_PROGRESS);
        task2.setDescription("Task Two");
        assertFalse(task.equals(task2));
    }

    @Test
    void testDifferentPriorityAndDecAndStatus() {
        Priority p2 = new Priority(2);
        task2.setPriority(p2);
        task2.setStatus(Status.IN_PROGRESS);
        task2.setDescription("Task Two");
        assertFalse(task.equals(task2));
    }

    @Test
    void testAllDifferent() {
        Priority p2 = new Priority(2);
        Calendar nextDay = Calendar.getInstance();
        DueDate dueDate2 = new DueDate(nextDay.getTime());
        dueDate2.postponeOneDay();
        task2.setDueDate(dueDate2);
        task2.setPriority(p2);
        task2.setStatus(Status.IN_PROGRESS);
        task2.setDescription("Task Two");
        assertFalse(task.equals(task2));
    }

    @Test
    void testAddTagThrowExc(){
        try {
            Tag tagNull = null;
            task.addTag(tagNull);
            fail("NullArgumentException should have been thrown");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException are expected to be threw");
        }
    }

    @Test
    void testRemoveTagThrowExc(){
        try {
            Tag tagNull = null;
            task.removeTag(tagNull);
            fail("NullArgumentException should have been thrown");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException are expected to be threw");
        }
    }

    @Test
    void testAddSingleTag() {
        task.addTag(tagOne);
        assertEquals(1,task.getTags().size());
    }

    @Test
    void testAddSingleTagDuplicate() {
        task.addTag(tagOne);
        task.addTag(tagOne);
        assertEquals(1,task.getTags().size());
    }

    @Test
    void testAddDifferentTags() {
        task.addTag(tagOne);
        task.addTag(tagTwo);
        assertEquals(2,task.getTags().size());
    }

    @Test
    void testAddDifferentTagsDuplicate() {
        task.addTag(tagTwo);
        task.addTag(tagOne);
        task.addTag(tagTwo);
        task.addTag(tagTwo);
        task.addTag(tagOne);
        assertEquals(2,task.getTags().size());
    }

    @Test
    void testRemoveSingleTag() {
        task.addTag(tagOne);
        assertEquals(1,task.getTags().size());
        task.removeTag(tagOne);
        assertEquals(0,task.getTags().size());
    }

    @Test
    void testRemoveTagOutSideTheList() {
        task.addTag(tagOne);
        assertEquals(1,task.getTags().size());
        task.removeTag(tagTwo);
        assertEquals(1,task.getTags().size());
    }

    @Test
    void testRemoveMultipleTags() {
        task.addTag(tagTwo);
        task.addTag(tagOne);
        assertEquals(2,task.getTags().size());
        task.removeTag(tagOne);
        task.removeTag(tagTwo);
        assertEquals(0,task.getTags().size());
    }


}