package org.example.juc.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForConcurementUnSafeTest {

    @Test
    void testRun() throws InterruptedException {
        // Given
        ForConcurementUnSafe fc = new ForConcurementUnSafe();

        // When
        for (int i = 0; i < 5; i++) {
            fc.setCount(0);
            fc.run();
            assertEquals(100, fc.getCount());
        }
        // Then

    }
}