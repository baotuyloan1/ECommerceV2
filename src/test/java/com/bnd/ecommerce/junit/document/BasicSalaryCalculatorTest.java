package com.bnd.ecommerce.junit.document;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bnd.ecommerce.junit.BasicSalaryCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author BAO 6/24/2023
 */
//@SpringBootTest
public class BasicSalaryCalculatorTest {

  private BasicSalaryCalculator basicSalaryCalculator;

  @BeforeEach
  void init() {
    System.out.println("Before each init");
    basicSalaryCalculator = new BasicSalaryCalculator();
  }

  @DisplayName("Test basic salary")
  @Test
  void testBasicSalaryWithInvalidSalary() {
    double basicSalary = -100;
    System.out.println("Test invalid salary");
    assertThrows(
            IllegalArgumentException.class,
            () -> {
              basicSalaryCalculator.setBasicSalary(basicSalary);
            });
  }

  @Test
  void testBasicSalaryWIthValidSalary() {
    System.out.println("Test valid salary");
    double basicSalary = 4000;
    basicSalaryCalculator.setBasicSalary(basicSalary);

    double expectedSocialInsurance = basicSalary * 0.25;
    assertEquals(expectedSocialInsurance, basicSalaryCalculator.getSocialInsurance());

    double expectedAdditionalBonus = basicSalary * 0.1;
    assertEquals(expectedAdditionalBonus, basicSalaryCalculator.getAdditionalBonus());

    double expectedAdditionalBonus1 = basicSalary * 0.1;
    assertEquals(expectedAdditionalBonus1, basicSalaryCalculator.getAdditionalBonus());

    // ...........
  }

  @BeforeEach
  void beforeEach(){
    System.out.println("Before each");
  }



  @DisplayName("Test basic salary")
  @Test
  void testBasicSalaryWithInvalidSalary2() {

    System.out.println("Test invalid salary2");
    double basicSalary = -100;
    assertThrows(
            IllegalArgumentException.class,
            () -> {
              basicSalaryCalculator.setBasicSalary(basicSalary);
            });
  }


  @AfterEach
  void tearDown() {
    System.out.println("after each");
    basicSalaryCalculator = null;
  }
}
