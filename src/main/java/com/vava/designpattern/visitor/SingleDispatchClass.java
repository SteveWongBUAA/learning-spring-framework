package com.vava.designpattern.visitor;

/**
 * @author Steve
 * Created on 2020-04
 *
 * Single Dispatch，指的是执行哪个对象的方法，根据对象的运行时类型来决定；执行对象的哪个方法，根据方法参数的【编译时】类型来决定。
 * Double Dispatch，指的是执行哪个对象的方法，根据对象的运行时类型来决定；执行对象的哪个方法，根据方法参数的【运行时】类型来决定。
 * Java 是 Single Dispath
 *
 */
public class SingleDispatchClass {
    public static void main(String[] args) {
        SingleDispatchClass singleDispatchClass = new SingleDispatchClass();
        ParentClass p = new ChildClass();
        // 函数重载是按照编译时声明的类型，所以调用的是Parent
        // 执行对象（singleDispatchClass）的哪个方法（void overloadFunction(ParentClass p) 还是 void overloadFunction(ChildClass c)）
        // 由方法参数的编译时类型决定，也就是Parent
        singleDispatchClass.overloadFunction(p);
        // 这个是按照运行时的类型
        singleDispatchClass.polymorphismFunction(p);
    }

    public void polymorphismFunction(ParentClass p) {
        // 执行哪个对象的方法，运行时决定，所以是child
        p.f();
    }

    public void overloadFunction(ParentClass p) {
        System.out.println("singleDispatch.parent");
    }

    public void overloadFunction(ChildClass c) {
        System.out.println("singleDispatch.child");
    }


}
