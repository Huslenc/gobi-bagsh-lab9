package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    @Test
    void letterGradeHappyA() {
        assertEquals("A", calc.letterGrade(95));
    }

    @Test
    void gpaHappyTwoGrades() {
        assertEquals(3.5, calc.gpa(new double[] {95, 85}), 1e-9);
    }

    @Test
    void scholarshipHappyFull() {
        assertEquals(100, calc.scholarship(3.9, 16, false));
    }

    @Test
    void scholarshipHappySeventy() {
        assertEquals(70, calc.scholarship(3.5, 13, false));
    }

    @Test
    void scholarshipHappyFifty() {
        assertEquals(50, calc.scholarship(3.1, 10, false));
    }
}
