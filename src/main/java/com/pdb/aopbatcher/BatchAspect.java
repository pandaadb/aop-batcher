package com.pdb.aopbatcher;

import com.google.common.collect.Iterables;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BatchAspect {

  private static final int batch_size = 4;

  @Around("@annotation(com.pdb.aopbatcher.Batch)")
  public Object batchProcess(final ProceedingJoinPoint pointcut) throws Throwable {
    var inputIdx = getInputCollectionIdx(pointcut);
    
    final var partitions =
        Iterables.partition((Collection<?>) pointcut.getArgs()[inputIdx], batch_size);

    ResultAggregator agg = getAggregator(pointcut);

    for (final var partition : partitions) {
      agg.add(pointcut.proceed(createArgs(pointcut, partition, inputIdx)));
    }

    return agg.getResult();
  }

  private ResultAggregator getAggregator(final ProceedingJoinPoint pointcut) {
	  MethodSignature ms = (MethodSignature) pointcut.getSignature();
	  final var returnType = ms.getReturnType();
	  
	  if(returnType.isAssignableFrom(List.class)) {
		  return new ListResultAggregator();
	  }
	  
	  if(returnType.isAssignableFrom(Map.class)) {
		  return new MapResultAggregator();
	  }
	  
	  throw new IllegalArgumentException();
  }

  private Object[] createArgs(
      final ProceedingJoinPoint pointcut, final List<?> toReplace, final int idx) {
    Object[] o = new Object[pointcut.getArgs().length];

    for (int i = 0; i < pointcut.getArgs().length; i++) {
      if (i == idx) {
        o[i] = toReplace;
      } else {
        o[i] = pointcut.getArgs()[i];
      }
    }

    return o;
  }

  private int getInputCollectionIdx(final ProceedingJoinPoint pointcut) {
    if (pointcut.getArgs().length == 1) {
      return 0;
    }
    MethodSignature ms = (MethodSignature) pointcut.getSignature();
    Parameter[] parameters = ms.getMethod().getParameters();

    for (int i = 0; i < parameters.length; i++) {
      BatchInput input = parameters[i].getAnnotation(BatchInput.class);
      if (input != null) {
        return i;
      }
    }

    throw new IllegalArgumentException("No input in args found");
  }
}
