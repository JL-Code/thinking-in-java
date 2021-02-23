package org.example.basic.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>创建时间: 2021/2/23 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class LocalDateStudy {

    public static void main(String[] args) {
        List<Date> arr = new ArrayList<>();
        List<String> list = Arrays.asList("2020-12", "2020-11", "2021-1", "2021-2");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");

        /**
         * 如果参数日期等于此日期，则值为0；
         * 如果此日期在日期参数之前，则值小于0；
         * 如果此日期在日期参数之后，则值大于0。
         */
        list.sort((a, b) -> {
            try {
                Date aDate = df.parse(a);
                Date bDate = df.parse(b);
                return aDate.compareTo(bDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        });

//            LocalDate aDate = LocalDate.parse(a);
//            LocalDate bDate = LocalDate.parse(b);
//            return aDate.isBefore(bDate) ? -1 : (aDate == bDate ? 0 : 1);

        list.forEach(item -> {
            try {
                arr.add(df.parse(item));
            } catch (ParseException e) {
                Date min = Date.from(LocalDate.MIN.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                arr.add(min);
            }
        });

        arr.sort(Date::compareTo);

        System.out.println(arr);

        System.out.println(list);

        List<Integer> list2 = Arrays.asList(1, 2, 6, 5, 9);
        list2.sort((a, b) -> a - b);

        System.out.println(list2);
    }
}
