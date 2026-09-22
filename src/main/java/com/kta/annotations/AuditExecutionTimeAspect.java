package com.kta.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

@Aspect
public class AuditExecutionTimeAspect {

    @Pointcut("execution(@com.kta.annotations.AuditExecutionTime * *(..)) && @annotation(auditAnnotation)")
    public void methodAnnotatedWithAudit(AuditExecutionTime auditAnnotation) {}

    @Around("methodAnnotatedWithAudit(auditAnnotation)")
    public Object auditExecutionTime(ProceedingJoinPoint joinPoint, AuditExecutionTime auditAnnotation) throws Throwable {
        long startNano = System.nanoTime();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getDeclaringType().getSimpleName() + "." + methodSignature.getName() + "()";
        String tag = (auditAnnotation.value() == null || auditAnnotation.value().isBlank())
                ? methodName
                : auditAnnotation.value() + " [" + methodName + "]";
        TimeUnit unit = auditAnnotation.unit();

        try {
            return joinPoint.proceed();
        } finally {
            long durationNano = System.nanoTime() - startNano;
            double duration;
            String unitLabel;

            switch (unit) {
                case NANOSECONDS -> {
                    duration = durationNano;
                    unitLabel = "ns";
                }
                case MICROSECONDS -> {
                    duration = durationNano / 1_000.0;
                    unitLabel = "µs";
                }
                case SECONDS -> {
                    duration = durationNano / 1_000_000_000.0;
                    unitLabel = "s";
                }
                case MILLISECONDS -> {
                    duration = durationNano / 1_000_000.0;
                    unitLabel = "ms";
                }
                default -> {
                    duration = unit.convert(durationNano, TimeUnit.NANOSECONDS);
                    unitLabel = unit.name().toLowerCase();
                }
            }

            System.out.printf("[AUDIT] %s executed in %.3f %s%n", tag, duration, unitLabel);
        }
    }
}
