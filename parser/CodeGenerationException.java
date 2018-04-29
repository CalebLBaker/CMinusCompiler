/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author Faith
 */
public class CodeGenerationException extends Exception {
    public CodeGenerationException(String err) {
        System.out.println(err);
    }
}
