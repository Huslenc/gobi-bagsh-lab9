package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    // ===== Hesseg A: heviin urgalj =====

    @Test
    void letterGradeA() {
        assertEquals("A", calc.letterGrade(95));
    }

    @Test
    void letterGradeB() {
        assertEquals("B", calc.letterGrade(85));
    }

    @Test
    void gpaTwoGrades() {
        // 95 -> A (4), 85 -> B (3), golch = 3.5
        assertEquals(3.5, calc.gpa(new double[] {95, 85}), 1e-9);
    }

    @Test
    void scholarshipFull() {
        assertEquals(100, calc.scholarship(3.9, 16, false));
    }

    @Test
    void scholarshipHalf() {
        assertEquals(50, calc.scholarship(3.1, 16, false));
    }

    // ===== Hesseg B: branch 100% hurtel nemsen testuud =====

    @Test
    void letterGradeC() {
        assertEquals("C", calc.letterGrade(75));
    }

    @Test
    void letterGradeD() {
        assertEquals("D", calc.letterGrade(65));
    }

    @Test
    void letterGradeF() {
        assertEquals("F", calc.letterGrade(30));
    }

    @Test
    void letterGradeNegativeThrows() {
        // score < 0 nohtsol
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
    }

    @Test
    void letterGradeAbove100Throws() {
        // score > 100 nohtsol
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    @Test
    void gpaNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(null));
    }

    @Test
    void gpaEmptyThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[0]));
    }

    @Test
    void gpaWithFGradeHitsDefaultBranch() {
        // A, B, C, D, F -> (4+3+2+1+0)/5 = 2.0 ; switch-iin default (F) salaa
        assertEquals(2.0, calc.gpa(new double[] {95, 85, 75, 65, 30}), 1e-9);
    }

    @Test
    void scholarshipViolationGivesZero() {
        assertEquals(0, calc.scholarship(3.9, 16, true));
    }

    @Test
    void scholarship70() {
        assertEquals(70, calc.scholarship(3.5, 13, false));
    }

    @Test
    void scholarshipHighGpaLowCreditsFallsTo70() {
        // gpa >= 3.8 vnen, credits >= 15 hudal -> 70 zam
        assertEquals(70, calc.scholarship(3.9, 14, false));
    }

    @Test
    void scholarship50GoodGpaLowCredits() {
        // gpa >= 3.4 vnen, credits >= 12 hudal -> 50 zam
        assertEquals(50, calc.scholarship(3.5, 10, false));
    }

    @Test
    void scholarshipLowGpaGivesZero() {
        assertEquals(0, calc.scholarship(2.5, 16, false));
    }

    @Test
    void scholarshipNegativeGpaThrows() {
        // gpa < 0
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(-0.1, 15, false));
    }

    @Test
    void scholarshipGpaAbove4Throws() {
        // gpa > 4
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(4.1, 15, false));
    }

    @Test
    void scholarshipNegativeCreditsThrows() {
        // credits < 0
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(3.5, -1, false));
    }

    // ===== Hesseg C: davtaltiin zaagiin testuud (0 / 1 / 2+) =====

    @Test
    void gpaLoopZeroIterationsEmptyAndNull() {
        // 0 udaa: hooson massiv ba null ali ali ni exception shidne
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[0])),
            () -> assertThrows(IllegalArgumentException.class, () -> calc.gpa(null))
        );
    }

    @Test
    void gpaLoopOneIteration() {
        // 1 udaa: neg dunteig
        assertEquals(4.0, calc.gpa(new double[] {95}), 1e-9);
    }

    @Test
    void gpaLoopManyIterations() {
        // 2+ udaa: (4+3+2+1)/4 = 2.5
        assertEquals(2.5, calc.gpa(new double[] {95, 85, 75, 65}), 1e-9);
    }

    // ===== Hesseg D: zaag utgiin testuud (spec: 60-69.99 = D) =====

    @Test
    void boundary60IsD() {
        assertEquals("D", calc.letterGrade(60));
    }

    @Test
    void boundary59_99IsF() {
        assertEquals("F", calc.letterGrade(59.99));
    }

    @Test
    void boundary69_99IsD() {
        assertEquals("D", calc.letterGrade(69.99));
    }

    @Test
    void boundary0IsF() {
        assertEquals("F", calc.letterGrade(0));
    }

    @Test
    void boundary100IsA() {
        assertEquals("A", calc.letterGrade(100));
    }

    @Test
    void boundary89_99IsB() {
        assertEquals("B", calc.letterGrade(89.99));
    }

    @Test
    void boundary90IsA() {
        assertEquals("A", calc.letterGrade(90));
    }
}
