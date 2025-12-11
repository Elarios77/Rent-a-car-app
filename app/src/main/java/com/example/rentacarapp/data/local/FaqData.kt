package com.example.rentacarapp.data.local


import com.example.rentacarapp.R
import com.example.rentacarapp.domain.model.FAQItem

object FaqData {

    val Questions = listOf<FAQItem>(
        FAQItem(id = 1, question = R.string.faq_q1_title, answer = R.string.faq_q1_answer),
        FAQItem(id = 2, question = R.string.faq_q2_title, answer = R.string.faq_q2_answer),
        FAQItem(id = 3, question = R.string.faq_q3_title, answer = R.string.faq_q3_answer),
        FAQItem(id = 4, question = R.string.faq_q4_title, answer = R.string.faq_q4_answer),
        FAQItem(id = 5, question = R.string.faq_q5_title, answer = R.string.faq_q5_answer),
        FAQItem(id = 6, question = R.string.faq_q6_title, answer = R.string.faq_q6_answer)

    )
}