package edu.hw2.task4;

public record CallingInfo(String className, String methodName) {

    private static final int CALLER_INDEX = 2;

    private CallingInfo() {
        this(null, null);
    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        if (stackTraceElements.length < CALLER_INDEX + 1) {
            return new CallingInfo();
        }

        var stackLastElement = stackTraceElements[CALLER_INDEX];

        return new CallingInfo(
            stackLastElement.getClassName(),
            stackLastElement.getMethodName()
        );
    }

}
