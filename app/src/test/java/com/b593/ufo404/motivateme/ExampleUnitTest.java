package com.b593.ufo404.motivateme;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testGetAuthor() throws Exception {
        assertEquals("", MainActivity.getAuthor("").x);
        assertEquals("", MainActivity.getAuthor("").y);

        assertEquals("„Potrzeba czasu żeby dokonać rzeczy trudnych. Aby dokonać rzeczy niemożliwych potrzeba trochę więcej czasu.”", MainActivity.getAuthor("„Potrzeba czasu żeby dokonać rzeczy trudnych. Aby dokonać rzeczy niemożliwych potrzeba trochę więcej czasu.”").x);
        assertEquals("", MainActivity.getAuthor("„Potrzeba czasu żeby dokonać rzeczy trudnych. Aby dokonać rzeczy niemożliwych potrzeba trochę więcej czasu.”").y);

        assertEquals("„Przyszłość należy do tych, którzy wierzą w piękno swoich marzeń.”", MainActivity.getAuthor("„Przyszłość należy do tych, którzy wierzą w piękno swoich marzeń.” Eleanor Roosevelt" ).x);
        assertEquals("— Eleanor Roosevelt", MainActivity.getAuthor("„Przyszłość należy do tych, którzy wierzą w piękno swoich marzeń.” Eleanor Roosevelt" ).y);

        assertEquals("", MainActivity.getAuthor("” " ).x);
        assertEquals("", MainActivity.getAuthor("” " ).y);

        assertEquals("", MainActivity.getAuthor("to jest błędny string " ).x);
        assertEquals("", MainActivity.getAuthor("to jest błędny string " ).y);
    }
}