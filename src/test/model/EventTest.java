package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
	private Event e;
    private Event e2;
    private Event e3;
	private Date d;
	
	//NOTE: these tests might fail if time at which line (2) and (3) below is executed
	//is different from time that line (1) is executed.  Lines (1), (2), and (3) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
		e = new Event("Listing added");   // (1)
		d = Calendar.getInstance().getTime();   // (2)
        e2 = new Event("Listing added"); // (3)
        e3 = new Event("Dog Pooped"); // (3)
	}
	
	@Test
	public void testEvent() {
		assertEquals("Listing added", e.getDescription());
		assertEquals(d, e.getDate());
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "Listing added", e.toString());
	}

    @Test
    void testEqualsFalseNull() {
        assertFalse(e.equals(null));
    }

    @Test
    void testEqualsFalseDifferentClass() {
        assertFalse(e.equals(new Consumer()));
    }


    @Test
    void testEqualsTrueSameObject() {
        assertTrue(e.equals(e));
    }

    @Test
    void testEqualsTrueDifferentObjectSameDescriptionSameTime() {
        assertTrue(e.equals(e2));
        assertEquals(e.getDate(), e2.getDate());
        assertEquals(e.getDescription(), e2.getDescription());

    }

    @Test
    void testEqualsFalseDifferentObjectDifferentDescription() {
        assertFalse(e.equals(e3));
        assertEquals(e.getDate(), e3.getDate());
        assertFalse(e.getDescription().equals(e3.getDescription()));
    }

}
