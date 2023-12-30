import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Определение пользовательской аннотации DataProcessor
@Retention(RetentionPolicy.RUNTIME) // Определяет, что аннотация будет сохраняться во время выполнения
@Target(ElementType.METHOD) // Указывает, что аннотация может быть применена к методам
@interface DataProcessor {
    String description() default ""; // Опциональный элемент аннотации для описания
}

class DataManager {
    private List<Object> processors = new ArrayList<>(); // Список для хранения объектов-обработчиков данных
    ExecutorService executorService; // Пул потоков для асинхронной обработки данных

    public void registerDataProcessor(Object processor) {
        processors.add(processor); // Добавление обработчика данных в список
    }

    // Метод для загрузки данных из файла
    public List<String> loadData(String source) {
        List<String> data = new ArrayList<>(); // Список для хранения загруженных данных
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            // Используем BufferedReader для эффективного чтения из файла
            String line;
            while ((line = reader.readLine()) != null) {
                // Читаем каждую строку из файла
                data.add(line); // Добавление прочитанной строки в список
            }
        } catch (IOException e) {
            // Обработка исключений ввода/вывода
            e.printStackTrace();
        }
        return data; // Возврат списка прочитанных строк
    }

    // Метод для асинхронной обработки данных
    public List<String> processData(List<String> data) {
        executorService = Executors.newFixedThreadPool(5); // Создание пула из 5 потоков
        List<String> processedData = new ArrayList<>(); // Список для хранения обработанных данных

        data.forEach(item -> processors.forEach(processor -> {
            // Перебор данных и обработчиков
            for (Method method : processor.getClass().getMethods()) {
                // Перебор всех методов обработчика
                if (method.isAnnotationPresent(DataProcessor.class)) {
                    // Проверка на наличие аннотации DataProcessor
                    executorService.submit(() -> {
                        // Отправка задачи на выполнение в пул потоков
                        try {
                            String result = (String) method.invoke(processor, item);
                            // Вызов метода обработчика и получение результата
                            if (result != null) {
                                // Проверка на null (в случае если обработчик возвращает данные)
                                synchronized (processedData) {
                                    // Синхронизированный блок для безопасной работы с общим ресурсом
                                    processedData.add(result); // Добавление результата в список обработанных данных
                                }
                            }
                        } catch (Exception e) {
                            // Обработка исключений, возникающих во время выполнения метода обработчика
                            e.printStackTrace();
                        }
                    });
                }
            }
        }));

        executorService.shutdown(); // Завершение добавления задач в пул потоков
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // Ожидание завершения всех задач
        } catch (InterruptedException e) {
            // Обработка прерывания ожидания
            e.printStackTrace();
        }

        return processedData; // Возврат списка обработанных данных
    }

    // Метод для сохранения данных в файл
    public void saveData(String destination, List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
            // Создание BufferedWriter для эффективной записи в файл
            for (String item : data) {
                // Перебор обработанных данных
                writer.write(item); // Запись строки в файл
                writer.newLine(); // Добавление перевода строки после каждой записи
            }
        } catch (IOException e) {
            // Обработка исключений ввода/вывода
            e.printStackTrace();
        }
    }
}

// Классы обработчиков данных с аннотациями DataProcessor
class DataFilter {
    @DataProcessor(description = "Filter data")
    public String filter(String data) {
        // Пример фильтрации: пропускаем строки длиннее 3 символов
        return data.length() > 3 ? data : null;
    }
}

class DataTransformer {
    @DataProcessor(description = "Transform data")
    public String transform(String data) {
        // Пример трансформации: преобразование строки в верхний регистр
        return data.toUpperCase();
    }
}

class DataAggregator {
    @DataProcessor(description = "Aggregate data")
    public String aggregate(String data) {
        // Пример агрегации: добавление некоторой строки к исходной
        return data + " Processed";
    }
}

public class Main {
    public static void main(String[] args) {
        DataManager manager = new DataManager();
        // Регистрация различных обработчиков данных
        manager.registerDataProcessor(new DataFilter());
        manager.registerDataProcessor(new DataTransformer());
        manager.registerDataProcessor(new DataAggregator());

        // Загрузка данных из файла
        List<String> data = manager.loadData("source.txt");
        // Обработка данных
        List<String> processedData = manager.processData(data);
        // Сохранение обработанных данных в файл
        manager.saveData("destination.txt", processedData);
    }
}
