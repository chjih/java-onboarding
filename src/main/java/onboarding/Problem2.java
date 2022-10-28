package onboarding;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem2 {
    private static List<Integer> checkOverload(StringBuffer cryptogram) {
        return IntStream.range(0, cryptogram.length() - 1)
                .filter(x -> cryptogram.charAt(x) == cryptogram.charAt(x + 1))
                .boxed()
                .collect(Collectors.toList());
    }

    private static void deleteOverload(List<Integer> overloadList, StringBuffer cryptogram) {
        overloadList.stream().sorted(Comparator.reverseOrder())
                .forEach(x -> cryptogram.delete(x, x + 2));
    }

    private static String decrypt(StringBuffer cryptogram) {
        List<Integer> overloadList;
        while ((overloadList = checkOverload(cryptogram)).size() != 0) {
            deleteOverload(overloadList, cryptogram);
        }

        return cryptogram.toString();
    }

    private static boolean checkRules(String cryptogram){
        if(cryptogram.length()>1000||cryptogram.length()<1)
            return false;
        return cryptogram.toLowerCase().equals(cryptogram);
    }

    public static String solution(String cryptogram) {
        if(!checkRules(cryptogram))
            throw new IllegalArgumentException("올바르지 않은 입력값입니다.");

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(cryptogram);

        return decrypt(stringBuffer);
    }
}