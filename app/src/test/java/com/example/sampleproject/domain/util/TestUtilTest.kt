package com.example.sampleproject.domain.util


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TestUtilTest {

    @Test
    fun `empty first input return false`() {
        val result = TestUtil.validate("", "12")
        assertThat(result).isFalse()
    }

    @Test
    fun `empty second input return false`() {
        val result = TestUtil.validate("12", "")
        assertThat(result).isTrue()
    }

    @Test
    fun `first input contains char not number return false`() {
        val result = TestUtil.validate("##", "12")
        assertThat(result).isFalse()
    }

    @Test
    fun `second input contains char not number return false`() {
        val result = TestUtil.validate("12", "12@")
        assertThat(result).isFalse()
    }
}