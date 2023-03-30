package com.dt042g.laboration_2;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @Author: Honorine Lima
 * username:holi1900
 * Course: DT042G
 * Date: 2023-02-19
 * This class contains test methods for the a Lab2 class
 * This class contains test for all methods in Lab2 class as well as integrity
 * test for class design which test that all class fields and methods exist
 */
public class Lab2Test {
    /**
     * test the class design integrity by testing that the Lab2
     * class package name is "com.dt042g.laboration_2"
     */
    @Test
    void testLab2ClassPackageName() {
        assertEquals("com.dt042g.laboration_2", Lab2.class.getPackageName());
    }
    /**
     * Test the class design integrity by testing that the Lab2
     * class simple name is "Lab2"
     */
    @Test
    void testLab2ClassSimpleName() {

        assertEquals("Lab2", Lab2.class.getSimpleName());
    }

    /**
     * test the class design integrity by testing that the Lab2
     * class name is "com.dt042g.laboration_2.Lab2"
     */
    @Test
    void testLab2ClassName() {
        assertEquals("com.dt042g.laboration_2.Lab2", Lab2.class.getName());
    }
    /**
     * Test the class design integrity by testing that the Lab2 class is public
     */
    @Test
    void testLab2ClassIsPublic() throws ClassNotFoundException {
        assertTrue(Modifier.isPublic(Class.forName("com.dt042g.laboration_2.Lab2").getModifiers()));
    }
}
