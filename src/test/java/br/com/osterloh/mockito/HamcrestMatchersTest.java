package br.com.osterloh.mockito;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;


class HamcrestMatchersTest {

    @Test
    void testHamcrestMatchers() {
        List<Integer> scores = Arrays.asList(99, 100, 101, 105);

        assertThat(scores, hasSize(4));
        assertThat(scores, hasItems(100, 101));
        assertThat(scores, everyItem(greaterThan(98)));
        assertThat(scores, everyItem(lessThan(106)));

        assertThat("", is(emptyString()));
        assertThat(null, is(emptyOrNullString()));

        Integer[] myArray = {1, 2, 3};
        assertThat(myArray, arrayWithSize(3));
        assertThat(myArray, arrayContaining(1, 2 ,3));
        assertThat(myArray, arrayContainingInAnyOrder(2 ,3, 1));
    }
}
