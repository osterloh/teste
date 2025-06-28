package br.com.osterloh.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListTest {

    @Test
    void testMockingListWhenSizeIsCalledShouldReturn10() {

        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10);

        assertEquals(10, list.size());
    }

    @Test
    void testMockingListWhenSizeIsCalledShouldReturnMultipleValues() {

        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20);

        assertEquals(10, list.size());
        assertEquals(20, list.size());
        assertEquals(20, list.size());
    }

    @Test
    void testMockingListWhenGetIsCalledShouldReturnJohn() {

        var list = mock(List.class);
        when(list.get(0)).thenReturn("John");

        assertEquals("John", list.get(0));
        assertNull(list.get(1));
    }

    @Test
    void testMockingListWhenGetIsCalledWithArgumentMatcherShouldReturnJohn() {

        var list = mock(List.class);
        when(list.get(anyInt())).thenReturn("John");

        assertEquals("John", list.get(anyInt()));
    }

    @Test
    void testMockingListWhenThrowsAnException() {

        var list = mock(List.class);
        when(list.get(anyInt())).thenThrow(new RuntimeException("Foo Bar!"));

        assertThrows(RuntimeException.class, () ->
                list.get(any()),
                "Should have throw an RuntimeException");
    }
}
