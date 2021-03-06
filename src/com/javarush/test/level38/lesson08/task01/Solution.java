package com.javarush.test.level38.lesson08.task01;

/* Предопределенные типы аннотаций (Predefined Annotation Types)
Расставьте в этом классе, везде где только можно, все возможные предопределенные в Java аннотации.
Не должно быть избыточности.
*/
@Deprecated
public class Solution {
    @Deprecated
    private String[] arguments;

    @Deprecated
    @SafeVarargs
    public Solution(@Deprecated String... arguments) {
        this.arguments = arguments;
    }
    @Deprecated
    public void voidMethod() throws Exception {
    }
    @SuppressWarnings("all")
    @Deprecated
    public static void main(@Deprecated String[] args) throws Exception {
        new Solution().new SubSolution().voidMethod();
    }
    @Deprecated
    class SubSolution extends Solution {
        @Deprecated
        @Override
        public void voidMethod() throws Exception {
            super.voidMethod();
        }
    }
}
