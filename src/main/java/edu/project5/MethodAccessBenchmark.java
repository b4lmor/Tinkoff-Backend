package edu.project5;

import edu.project5.entity.Student;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Benchmark)
public class MethodAccessBenchmark {

    private static final String METHOD_NAME = "name";

    private Student student;
    private Method directMethod;
    private MethodHandle methodHandle;
    private Function<Student, String> lambdaFunction;

    @Setup
    public void setup() throws Throwable {
        student = new Student("That's my name");

        directMethod = Student.class.getMethod(METHOD_NAME);

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, METHOD_NAME, MethodType.methodType(String.class));

        var lambdaMethodHandle = LambdaMetafactory.metafactory(
            MethodHandles.lookup(),
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            methodHandle,
            MethodType.methodType(String.class, Student.class)
        ).getTarget();

        lambdaFunction = (Function<Student, String>) lambdaMethodHandle.invokeExact();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void directAccess(Blackhole blackhole) {
        blackhole.consume(student.name());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void reflectionAccess(Blackhole blackhole) throws Exception {
        blackhole.consume(directMethod.invoke(student));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void methodHandleAccess(Blackhole blackhole) throws Throwable {
        blackhole.consume(methodHandle.invoke(student));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void lambdaMethodHandleAccess(Blackhole blackhole) {
        blackhole.consume(lambdaFunction.apply(student));
    }

    @SuppressWarnings({"UncommentedMain", "MagicNumber"})
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(MethodAccessBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();

        new Runner(options).run();
    }

    interface StudentNameGetter {
        String getName(Student student);
    }
}
