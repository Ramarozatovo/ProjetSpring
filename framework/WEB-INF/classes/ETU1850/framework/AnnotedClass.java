/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ETU1850.framework;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author tovo
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotedClass {
    String methodName() default "1";
}
