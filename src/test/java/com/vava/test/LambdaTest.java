package com.vava.test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;

import com.vava.bean.Person;
import com.vava.lambda.CheckPerson;

/**
 * @author steve
 * <p>
 * # 参考： https://juejin.im/post/5a5573e06fb9a01cbe653365
 * # {@link CheckPerson} 是一个非常简单的接口, 只有一个抽象方法， （函数式接口的定义）
 * ## 这个函数有一个参数和一个返回值。他太简单以至于不需要你在应用中定义它
 * ## JDK定义了一些标准的函数式接口，比如可以用{@link java.util.function.Predicate} 取代他
 * <p>
 * # 方法7：在应用中全部使用Lambda表达式
 * ## 你可以指定一个不同的动作来执行打印满足搜索条件的person，你可以指定这个动作是一个Lambda表达式
 * 打印的话你需要一个函数式接口，参数是Person类型，返回void。
 * 正好咱们有 {@link java.util.function.Consumer}
 * 对于每个person，如果符合predict.test, 那就用consumer消费他
 * 注意： 在调用之前，predict.test的条件是啥，consumer要怎么消费，都通过调用函数 {@link LambdaTest#printPerson3(List, Predicate, Consumer)} 动态地指定
 * <p>
 * # 如果想对用户信息进行更多处理，而不只是打印，那怎么办？
 * 你需要 一个有返回值的抽象函数的函数式接口 {@link java.util.function.Function#apply(Object)} 有一个参数和一个返回值
 * <p>
 * # 还能更加通用么。。 使用泛型
 * # 再升级：使用聚合操作
 * ## filter、map 和 forEach 是聚合操作。局和操作是从stream中处理各个元素的，而不是直接从集合中（这就是为什么第一个调用的函数是stream）
 * stream是对各个元素进行序列化操作。和集合不同，他不是一个存储数据的数据结构。相反，stream加载了源中的值，比如集合通过pipeline将数据加载到stream中
 * pipeline是stream的一种反序列化操作
 */
public class LambdaTest {

    @Test
    public void test01() {
        System.out.println("person younger than 30");
        printPersonByCond(Person.getPersons(), (person) -> {return (person.getAge() < 30);});
        System.out.println("person older than 30");
        printPersonByPredict(Person.getPersons(), (person) -> {return (person.getAge() > 30);});
        System.out.println("person older than 10 use lambda");
        printPerson3(
                Person.getPersons(),
                (person) -> (person.getAge() > 10),
                (person) -> {System.out.println(person);}
        );
        System.out.println("process person");
        processPersons(Person.getPersons(),
                person -> person.getAge() < 32,
                person -> {return person.getName();},
                name -> System.out.println(name)
        );
        System.out.println("stream");
        List<Integer> res = Person.getPersons()
                .stream()
                .map(Person::getAge)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(res);
    }

    private void printPersonByCond(List<Person> personList, CheckPerson checkPerson) {
        for (Person person : personList) {
            if (checkPerson.check(person)) {
                System.out.println(person);
            }
        }
    }

    private void printPersonByPredict(List<Person> personList, Predicate<Person> personPredicate) {
        for (Person person : personList) {
            if (personPredicate.test(person)) {
                System.out.println(person);
            }
        }
    }

    private void printPerson3(List<Person> personList, Predicate<Person> personPredicate,
            Consumer<Person> personConsumer) {
        for (Person p : personList) {
            if (personPredicate.test(p)) {
                personConsumer.accept(p);
            }
        }
    }

    private void processPersons(
            List<Person> personList,
            Predicate<Person> personPredicate,
            Function<Person, String> function,
            Consumer<String> stringConsumer) {
        for (Person p : personList) {
            if (personPredicate.test(p)) {
                String ret = function.apply(p);
                stringConsumer.accept(ret);
            }
        }
    }

}
