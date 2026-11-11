package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Лаб 9, C шат: gpa() доторх for давталтын 0 / 1 / 2+ тестүүд. */
class ScholarshipCalculatorLoopTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    // 0 удаа: давталтад хүрэхээсээ өмнө шидэгдэнэ
    @Test void gpa_loop_zero_empty_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[]{}));
    }
    @Test void gpa_loop_zero_null_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(null));
    }

    // 1 удаа
    @Test void gpa_loop_oneIteration() {
        assertEquals(4.0, calc.gpa(new double[]{95}), 1e-9);
    }

    // 2 удаа
    @Test void gpa_loop_twoIterations() {
        assertEquals(3.5, calc.gpa(new double[]{95, 85}), 1e-9);
    }

    // 2+ (олон) удаа: (4 + 3 + 2 + 1) / 4 = 2.5
    @Test void gpa_loop_manyIterations() {
        assertEquals(2.5, calc.gpa(new double[]{95, 85, 75, 65}), 1e-9);
    }
}
