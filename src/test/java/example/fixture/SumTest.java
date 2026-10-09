/*
 * SPDX-License-Identifier: Apache-2.0
 */
package example.fixture;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SumTest {
    @Test
    void addsPositiveNumbers() {
        assertThat(Sum.add(2, 3)).isEqualTo(5);
    }

    @Test
    void addsNegativeNumbers() {
        assertThat(Sum.add(-2, -3)).isEqualTo(-5);
    }
}
