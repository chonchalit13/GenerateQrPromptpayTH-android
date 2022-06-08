package com.toei.promptpay

import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.Test

class PromptPayGenerateUnitTest {

    @Test
    fun testGenerateDatePromptPayNoData() {
        val result = PromptPayGenerate.generate("", "")

        assertNull(result)
    }

    @Test
    fun testGenerateDatePromptPayNoAccount() {
        val result = PromptPayGenerate.generate("", "1000.00")

        assertNull(result)
    }

    @Test
    fun testGenerateDatePromptPayNoAmountIsEmpty() {
        val result = PromptPayGenerate.generate("0991237283", "")

        assertNotNull(result)
    }

    @Test
    fun testGenerateDatePromptPayAmount() {
        val result = PromptPayGenerate.generate("0991237283", "1000.00")

        assertNotNull(result)
    }

    @Test
    fun testGenerateDatePromptPayNoAmount() {
        val result = PromptPayGenerate.generate("0991237283")

        assertNotNull(result)
    }

    @Test
    fun testGenerateDatePromptPayIdCard() {
        val result = PromptPayGenerate.generate("1234567890123", "1000.00")

        assertNotNull(result)
    }

    @Test
    fun testGenerateDatePromptPayLengthOverAccount() {
        val result = PromptPayGenerate.generate("123456789012311", "1000.00")

        assertNotNull(result)
    }
}