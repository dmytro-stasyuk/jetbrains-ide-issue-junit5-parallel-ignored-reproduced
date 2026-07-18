package repro.toolbar;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@DisplayName("AddRight1")
public class AddRight1Test {

    @Nested @Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Rule: behaviour rule number 1")
    public class Rule_1 {

        @Test @Order(1)
        @DisplayName("Scenario: passing scenario 1")
        void scenario_1() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(2)
        @DisplayName("Scenario: passing scenario 2")
        void scenario_2() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(3)
        @DisplayName("Scenario: passing scenario 3")
        void scenario_3() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(4)
        @DisplayName("Scenario: passing scenario 4")
        void scenario_4() throws Exception { Thread.sleep(2); /* always passes */ }

    }

    @Nested @Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Rule: behaviour rule number 2")
    public class Rule_2 {

        @Test @Order(1)
        @DisplayName("Scenario: passing scenario 1")
        void scenario_1() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(2)
        @DisplayName("Scenario: passing scenario 2")
        void scenario_2() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(3)
        @DisplayName("Scenario: passing scenario 3")
        void scenario_3() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(4)
        @DisplayName("Scenario: passing scenario 4")
        void scenario_4() throws Exception { Thread.sleep(2); /* always passes */ }

    }

    @Nested @Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Rule: behaviour rule number 3")
    public class Rule_3 {

        @Test @Order(1)
        @DisplayName("Scenario: passing scenario 1")
        void scenario_1() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(2)
        @DisplayName("Scenario: passing scenario 2")
        void scenario_2() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(3)
        @DisplayName("Scenario: passing scenario 3")
        void scenario_3() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(4)
        @DisplayName("Scenario: passing scenario 4")
        void scenario_4() throws Exception { Thread.sleep(2); /* always passes */ }

    }

    @Nested @Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Rule: behaviour rule number 4")
    public class Rule_4 {

        @Test @Order(1)
        @DisplayName("Scenario: passing scenario 1")
        void scenario_1() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(2)
        @DisplayName("Scenario: passing scenario 2")
        void scenario_2() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(3)
        @DisplayName("Scenario: passing scenario 3")
        void scenario_3() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(4)
        @DisplayName("Scenario: passing scenario 4")
        void scenario_4() throws Exception { Thread.sleep(2); /* always passes */ }

    }

    @Nested @Order(5)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Rule: behaviour rule number 5")
    public class Rule_5 {

        @Test @Order(1)
        @DisplayName("Scenario: passing scenario 1")
        void scenario_1() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(2)
        @DisplayName("Scenario: passing scenario 2")
        void scenario_2() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(3)
        @DisplayName("Scenario: passing scenario 3")
        void scenario_3() throws Exception { Thread.sleep(2); /* always passes */ }

        @Test @Order(4)
        @DisplayName("Scenario: passing scenario 4")
        void scenario_4() throws Exception { Thread.sleep(2); /* always passes */ }

    }

}
