package br.com.osterloh.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ListWithBDDTest {

    @Test
    void testMockingListWhenSizeIsCalledShouldReturn10() {

        List<?> list = mock(List.class);
        given(list.size()).willReturn(10);

        assertThat(list.size(), is(10));
    }

    @Test
    void testMockingListWhenSizeIsCalledShouldReturnMultipleValues() {

        List<?> list = mock(List.class);
        given(list.size()).willReturn(10).willReturn(20);

        assertThat(list.size(), is(10));
        assertThat(list.size(), is(20));
        assertThat(list.size(), is(20));
    }

    @Test
    void testMockingListWhenGetIsCalledShouldReturnJohn() {

        var list = mock(List.class);
        given(list.get(0)).willReturn("John");

        assertThat(list.get(0), equalTo("John"));
        assertThat(list.get(1), isNull());
    }

    @Test
    void testMockingListWhenGetIsCalledWithArgumentMatcherShouldReturnJohn() {

        var list = mock(List.class);
        given(list.get(anyInt())).willReturn("John");

        assertThat(list.get(anyInt()), equalTo("John"));
    }

    @Test
    void testMockingListWhenThrowsAnException() {

        var list = mock(List.class);
        given(list.get(anyInt())).willThrow(new RuntimeException("Foo Bar!"));

        assertThrows(RuntimeException.class, () ->
                list.get(any()),
                "Should have throw an RuntimeException");
    }
}
