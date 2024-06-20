package org.app;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.lang.reflect.Method;
import java.util.Set;

public class MethodLoggingLogic {

    public static void logExecution(Method method) {
        if (method.isAnnotationPresent(MethodLogging.class)) {
            System.out.println("Method: " + method.getName() + " is annotated with @" + MethodLogging.class);
        }
        else {
            System.out.println("Method: " + method.getName() + " is not annotated with @" + MethodLogging.class);
        }
    }

}
