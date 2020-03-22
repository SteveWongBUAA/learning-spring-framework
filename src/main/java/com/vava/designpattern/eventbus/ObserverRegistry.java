package com.vava.designpattern.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Steve
 * <p>
 * Observer注册表
 * 核心逻辑
 * <p>
 * {@link CopyOnWriteArraySet}
 * -- 写入数据的时候，会创建一个新的set，并且将原始的数据clone到新的set中
 * -- 在新的set中写入数据完成之后，再用新的set替换老的set
 * -- 这样就能保证在写入数据的时候，不会影响数据读取操作，以此解决数据并发的问题
 * -- 除此之外，CopyOnWriteSet还通过加锁的方式避免并发写的冲突
 */
public class ObserverRegistry {
    // key是发布的event类型，value是ObserverAction列表，每个ObserverAction包含类和方法
    private ConcurrentMap<Class<?>, CopyOnWriteArraySet<ObserverAction>> registry = new ConcurrentHashMap<>();

    /**
     * 把观察者类注册
     */
    public void register(Object observer) {
        Map<Class<?>, Collection<ObserverAction>> observerActions = findAllObserverActions(observer);
        for (Map.Entry<Class<?>, Collection<ObserverAction>> entry : observerActions.entrySet()) {
            Class<?> eventType = entry.getKey();
            Collection<ObserverAction> eventActions = entry.getValue();
            // 看看当前是否已经注册了这个eventType
            CopyOnWriteArraySet<ObserverAction> registeredEventActions = registry.get(eventType);
            if (null == registeredEventActions) {
                // 没有注册，那就加一下
                registry.putIfAbsent(eventType, new CopyOnWriteArraySet<>());
                registeredEventActions = registry.get(eventType);
            }
            registeredEventActions.addAll(eventActions);
        }
    }

    /** 获取匹配的ObserverActions
     *
     * @param event
     * @return
     */
    public List<ObserverAction> getMatchedObserverActions(Object event) {
        List<ObserverAction> matchedObservers = new ArrayList<>();
        Class<?> postedEventType = event.getClass();
        for (Map.Entry<Class<?>, CopyOnWriteArraySet<ObserverAction>> entry : registry.entrySet()) {
            Class<?> eventType = entry.getKey();
            Collection<ObserverAction> eventActions = entry.getValue();
            // 如果注册的eventType是当前发的消息的子类，就加进去matchedObservers中
            if (eventType.isAssignableFrom(postedEventType)) {
                matchedObservers.addAll(eventActions);
            }
        }
        return matchedObservers;
    }

    /**
     * 查找Observer类里面有哪些要执行的action（方法）
     * @param observer
     * @return 返回的key是消息类型，value是ObserverAction列表，每个ObserverAction包含类和方法
     */
    private Map<Class<?>, Collection<ObserverAction>> findAllObserverActions(Object observer) {
        Map<Class<?>, Collection<ObserverAction>> observerActions = new HashMap<>();
        Class<?> clazz = observer.getClass();
        for (Method method : getAnnotatedMethods(clazz)) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> eventType = parameterTypes[0];
            if (!observerActions.containsKey(eventType)) {
                observerActions.put(eventType, new ArrayList<>());
            }
            observerActions.get(eventType).add(new ObserverAction(observer, method));
        }
        return observerActions;
    }

    private List<Method> getAnnotatedMethods(Class<?> clazz) {
        List<Method> annotatedMethods = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                // 被注解的方法只能有一个参数，就是event，函数的功能就是消费这个event
                assert parameterTypes.length == 1;
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }
}
