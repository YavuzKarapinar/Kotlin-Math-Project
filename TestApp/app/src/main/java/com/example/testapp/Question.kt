package com.example.testapp

class Question {

    companion object {
        fun topla(sayi1: Int, sayi2: Int): Int {
            return sayi1 + sayi2
        }

        fun cikar(sayi1: Int, sayi2: Int): Int {
            return sayi1 - sayi2
        }

        fun carp(sayi1: Int, sayi2: Int): Int {
            return sayi1 * sayi2
        }

        fun bol(sayi1: Int, sayi2: Int): Float {
            return sayi1.toFloat() / sayi2.toFloat()
        }
    }
}