package model;

import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestTagPhase3 {

    private Tag tag1;
    private Tag tag;
    private Tag tag2;
    private Task taskOne;
    private Task taskTwo;

    @BeforeEach
    void runBefore() {
        tag = new Tag("tag1");
        tag1 = new Tag("tag1");
        tag2 = new Tag("tag2");
        taskOne = new Task("Task One");
        taskTwo = new Task("Task Two");
    }

    @Test
    void testSameNameCaseOne() {
        assertTrue(tag.equals(tag1));
    }

    @Test
    void testSameNameCaseTwo() {
        assertTrue(tag1.equals(tag));
    }

    @Test
    void testDifferentNames() {
        assertFalse(tag.equals(tag2));
    }

    @Test
    void testAddTaskThrowNullExc() {
        try {
            tag.addTask(null);
            fail("NullArgumentException should have been thrown");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException are expected to be threw");
        }
    }

    @Test
    void testRemoveTaskThrowNullExc() {
        try {
            tag.removeTask(null);
            fail("NullArgumentException should have been thrown");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException are expected to be threw");
        }
    }

    @Test
    void testAddSingleTask() {
        tag.addTask(taskOne);
        assertEquals(1,tag.getTasks().size());
    }

    @Test
    void testAddSingleTaskDuplicate() {
        tag.addTask(taskOne);
        tag.addTask(taskOne);
        tag.addTask(taskOne);
        assertEquals(1,tag.getTasks().size());
    }

    @Test
    void testAddDifferentTasks() {
        tag.addTask(taskOne);
        tag.addTask(taskTwo);
        assertEquals(2,tag.getTasks().size());
    }

    @Test
    void testAddTwoDifferentTasksButDuplicate() {
        tag.addTask(taskOne);
        tag.addTask(taskTwo);
        tag.addTask(taskOne);
        tag.addTask(taskTwo);
        tag.addTask(taskOne);
        assertEquals(2,tag.getTasks().size());
    }

    @Test
    void testRemoveTaskNormalCase() {
        tag.addTask(taskOne);
        tag.addTask(taskTwo);
        assertEquals(2,tag.getTasks().size());
        tag.removeTask(taskOne);
        assertEquals(1,tag.getTasks().size());
    }

    @Test
    void testRemoveTaskNotInTheList() {
        tag.addTask(taskOne);
        assertEquals(1,tag.getTasks().size());
        tag.removeTask(taskTwo);
        assertEquals(1,tag.getTasks().size());
    }

    @Test
    void testRemoveAll() {
        tag.addTask(taskTwo);
        tag.addTask(taskOne);
        assertEquals(2,tag.getTasks().size());
        tag.removeTask(taskOne);
        tag.removeTask(taskTwo);
        assertEquals(0,tag.getTasks().size());
    }
}
