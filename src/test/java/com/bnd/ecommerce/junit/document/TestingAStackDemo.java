package com.bnd.ecommerce.junit.document;

import java.util.EmptyStackException;
import java.util.Stack;
import org.junit.jupiter.api.*;

/**
 * @author BAO 6/24/2023
 */
@DisplayName("A stack")
public class TestingAStackDemo {

  Stack<Object> stack;

  @Test
  @DisplayName("is instantiated with new Stack()")
  void isInstanttiateWithNew() {
    new Stack<>();
  }

  @Nested
  @DisplayName("when new")
  class whenNew {
    @BeforeEach
    void createNewStack() {
      stack = new Stack<>();
    }

    @Test
    @DisplayName("is empty")
    void isEmpty() {
      Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("throws EmptyStackException when popped")
    void throwExceptionWhenPopped() {
      Assertions.assertThrows(EmptyStackException.class, () -> stack.pop());
    }

    @Test
    @DisplayName("throws EmptyStackException when peeked")
    void throwExceptionWhenPeeked() {
      Assertions.assertThrows(EmptyStackException.class, () -> stack.peek());
    }

    @Nested
    @DisplayName("after pushing an element")
    class AfterPushing {
      String anElement = "an element";

      @BeforeEach
      void pushAnElement() {
        stack.push(anElement);
      }

      @Test
      @DisplayName("it í no longer empty")
      void isNotEmpty() {
        Assertions.assertFalse(stack.isEmpty());
      }

      @Test
      @DisplayName("returns the element when popped and is empty")
      void returnElementWhenPopped() {
        Assertions.assertEquals(anElement, stack.pop());
        Assertions.assertTrue(stack.isEmpty());
      }

      @Test
      @DisplayName("returns the element when peeked but remains not empty")
      void returnElementWhenPeeked(){
        Assertions.assertEquals(anElement, stack.peek());
        Assertions.assertFalse(stack.isEmpty());
      }

    }
  }
}
