package com.bnd.ecommerce.junit.document;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author BAO 6/24/2023
 */
@DisplayName("A special test case")
public class StandardTests {

  /**
   * @ParameterizedTest: Parameterized tests make it possible to run a test multiple times with
   * different arguments. They are declared just like regular @Test methods but use
   * the @ParameterizedTest annotation instead. In addition, you must declare at least one source
   * that will provide the arguments for each invocation and then consume the arguments in the test
   * method.
   *
   * <p>-@RepeatedTest: JUnit Jupiter provides the ability to repeat a test a specified number of
   * times simply aby annotating a method with @RepeatedTest and specifying the total number of
   * repetitions desired. Each invocation of a repeated test behaves like the execution of a
   * regular @Test method with full support for the same lifecycle callbacks and extensions.
   *
   * <p>-@BeforeEach: Denotes that the annotated method should be executed before
   * each @Test, @RepeatedTest, @ParameterizedTest, or @TestFactory method in the current class;
   *
   * <p>-@AfterEach: Denotes that the annotated method should be executed after
   * each @Test, @RepeatedTest, @ParameterizedTest, or @TestFactory method in the current class;
   *
   * <p>-@BeforeAll: Denotes that the annotated method should be executed before
   * all @Test, @RepeatedTest, @ParameterizedTest, and @TestFactory methods in the current class
   *
   * <p>-@AfterAll: Denotes that the annotated method should be executed after
   * all @Test, @RepeatedTest, @ParameterizedTest, and @TestFactory methods in the current class;
   *
   * <p>-@Nested: denotes that the annotated class is a nested, non-static test class. @BeforeAll
   * and @AfterAll methods cannot be used directly(trực tiếp) in a @Nested class unless the
   * "per-class" test instance lifecycle is used. chú thích như vậy không được thừa kế. Nested tests give the test writer more capabilities to express the relationship among several group of tests.
   */
  @BeforeEach
  void init() {
    System.out.println("@BeforeEach executed");
  }

  @ParameterizedTest
  @ValueSource(strings = {"racecar", "radar", "able was I"})
  void multipleTimes(String candidate) {
    Assertions.assertEquals(candidate, "radar");
  }

  @DisplayName("repeatedTime")
  @RepeatedTest(5)
  void repeatedTestWithRepetitionInfo(RepetitionInfo repetitionInfo) {
    System.out.println(repetitionInfo.getCurrentRepetition());
    Assertions.assertEquals(5, repetitionInfo.getTotalRepetitions());
  }

  @RepeatedTest(value = 10, name = "{displayName} {currentRepetition}/{totalRepetitions}")
  @DisplayName("Repeat!")
  void customerDisplayName(TestInfo testInfo) {
    System.out.println(testInfo.getDisplayName());
  }

  @BeforeAll
  static void initAll() {
    System.out.println("@BeforeAll executed");
  }

  @Tag("DEV")
  @Test
  void testCalcOne() {
    System.out.println("============TEST ONE EXECUTED=========");
    Assertions.assertEquals(4, 2 + 2);
  }

  @Tag("PROD")
  @Disabled("for dev environment")
  @Test
  void testCalcTwo() {
    System.out.println("============TEST TWO EXECUTED=========");
    Assertions.assertEquals(6, 4 + 2);
  }

  @AfterEach
  void tearDown() {
    System.out.println("@AfterEach executed");
  }

  @AfterAll
  static void tearDownAll() {
    System.out.println("@AfterAll executed");
  }

  @Test
  @DisplayName("╯°□°）╯")
  void testWithDisplayNameContainingSpecialCharacters() {}

  @Test
  @DisplayName("😱")
  void testWithDisplayNameContainingEmoji() {}
}
