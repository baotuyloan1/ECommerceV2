package com.bnd.ecommerce.junit.document;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ECommerceApplicationTests {

  /**
   * JUnit5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
   *
   * <p>The JUnit Platform serves as a foundation for launching testing frameworks on the JVM. It
   * also defines the TestEngine API for developing a testing framework that runs on the platform.
   *
   * <p>JUnit Jupiter is the combination of the new programming model and extension model for
   * writing tests and extensions in JUnit5. The Jupiter sub-project provides a TestEngine for
   * running Jupiter-based tests on the platform
   *
   * <p>JUnit Vintage: provides a TestEngine for running JUnit3 and JUnit4 based tests on the
   * platform
   *
   * <p>JUnit 5 requires Java 8 (or higher) at run-time. However, you can still test code that has
   * been compiled with previous versions of the JDK.
   *
   * <p>Annotation available in JUnit framework
   *
   * <p>-@Test: Declares a test method
   *
   * <p>-@TestFactory Denotes(Biểu diễn) a method is a test factory for dynamic tests in JUnit 5.
   *
   * <p>-@DisplayName: Define custom display name for a test class or a test method
   *
   * <p>-@BeforEach: Denotes that the annotated method will be executed before each test method
   * (annotated with @Test) in the current class
   *
   * <p>-@AfterEach: Denotes that the annotated method will executed after each test method
   * (annotated with @Test) in the current class.
   *
   * <p>-@BeforeAll: Denotes that the annotated method will be executed before all test methods in
   * the current class.
   *
   * <p>-@AfterAll: Denotes that the annotated method will be executed after all test methods in the current class.
   *
   * -@Nested: Denotes that the annotated class is a nested, non-static test class
   *
   * -@Tag: Declare tags for filtering tests.
   *
   * -@Disable is used to disable a test class or method
   *
   * -@ExtendWith is used to register customer extensions in JUnit5
   *
   * -@RepeatedTest is used to repeat tests.
   */
  @Test
  void contextLoads() {}
}
