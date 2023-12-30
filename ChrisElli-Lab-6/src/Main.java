public class Main {
    public static void main(String[] args) {
        // : Создаётся экземпляр стека с максимальным размером 10
        Stack<Integer> stack = new Stack<>(10);
        // .push для добавления чисел 1, 2 и 3 в стек.
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());  // Удаляет и выводит верхний элемент стека (3).
        System.out.println(stack.peek()); // Возвращает и выводит верхний элемент стека (2), не удаляя его.
        // Добавляется число 4 и сразу извлекается с помощью pop(), выводится 4.
        stack.push(4);
        System.out.println(stack.pop());
    }
}
