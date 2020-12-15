/**
*  @author(Liam Ryan)
**/
package com.team18.taxprogram.io;

public interface InputCheck<T> {
    T readInput(String s) throws Exception;
}
