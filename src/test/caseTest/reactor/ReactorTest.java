package caseTest.reactor;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@SpringBootTest
public class ReactorTest {
    @Test
    public void test1(){
//        Flux<String> flux = Flux.just("str1", "str2");
//        flux.subscribe(System.out::println);
//        System.out.println("=====================");
//        Mono<String> mono = Mono.just("str1");
//        mono.subscribe(System.out::println);
//        System.out.println("=====================");
//        Flux<String> fromIterable = Flux.fromIterable(Arrays.asList("str1", "str2"));
//        fromIterable.subscribe(System.out::println);
//        System.out.println("=====================");
//        Flux<Integer> fromStream = Flux.fromStream(Arrays.asList(1).stream());
//        fromStream.subscribe(System.out::println);
//        System.out.println("=====================");
//        Flux<Integer> fromArray = Flux.fromArray(new Integer[]{1, 2, 3});
//        fromArray.subscribe(System.out::println);
//        System.out.println("=====================");
//        Flux<Integer> range = Flux.range(2, 5);
//        range.subscribe(System.out::println);
//        System.out.println("=====================");
//        Flux<Object> empty = Flux.empty();
//        empty.subscribe(System.out::println);
//        Flux<Object> error = Flux.error(new Exception("error!"));
//        error.subscribe(System.out::println);
//        Flux<Object> never = Flux.never();
//        never.subscribe(System.out::println);


//        Object s = "[2,1]";
//        String s1 = s.toString();
//        System.out.println(s1.substring(1,s1.indexOf(",")));


//        Map<String, BigDecimal> map = new LinkedHashMap<>();
//
//        map.put("0时",new BigDecimal("0"));
//        map.put("1时",new BigDecimal("0"));
//        map.put("8时",new BigDecimal("0"));
//        map.put("23时",new BigDecimal("0"));
//
//        System.out.println(map);

        System.out.println(new BigDecimal("1.0000000").setScale(2, RoundingMode.HALF_UP));








//        System.out.println(((Integer[])s)[0]);



    }

}
