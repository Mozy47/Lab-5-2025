import functions.*;
import functions.basic.*;
import java.io.*;

public class Main {
    public static void main(String[] args){
        testAllMethods();
    }

    public static void testAllMethods() {
        System.out.println("=== ТЕСТИРОВАНИЕ ВСЕХ МЕТОДОВ ===");
        
        // Создаем тестовые данные
        FunctionPoint[] points1 = {
            new FunctionPoint(0.0, 1.0),
            new FunctionPoint(1.0, 3.0),
            new FunctionPoint(2.0, 5.0)
        };
        
        FunctionPoint[] points2 = {
            new FunctionPoint(0.0, 1.0),
            new FunctionPoint(1.0, 3.0),
            new FunctionPoint(2.0, 5.0)
        };
        
        FunctionPoint[] points3 = {
            new FunctionPoint(0.0, 1.0),
            new FunctionPoint(2.0, 5.0)  // Меньше точек
        };
        
        FunctionPoint[] points4 = {
            new FunctionPoint(0.0, 1.0),
            new FunctionPoint(1.0, 3.5), // Измененное значение Y
            new FunctionPoint(2.0, 5.0)
        };

        // Создаем объекты для тестирования
        ArrayTabulatedFunction arrayFunc1 = new ArrayTabulatedFunction(points1);
        ArrayTabulatedFunction arrayFunc2 = new ArrayTabulatedFunction(points2);
        ArrayTabulatedFunction arrayFunc3 = new ArrayTabulatedFunction(points3);
        ArrayTabulatedFunction arrayFunc4 = new ArrayTabulatedFunction(points4);
        
        LinkedListTabulatedFunction linkedFunc1 = new LinkedListTabulatedFunction(points1);
        LinkedListTabulatedFunction linkedFunc2 = new LinkedListTabulatedFunction(points2);
        LinkedListTabulatedFunction linkedFunc3 = new LinkedListTabulatedFunction(points3);

        System.out.println("\n--- ТЕСТ toString() ---");
        System.out.println("ArrayTabulatedFunction: " + arrayFunc1.toString());
        System.out.println("LinkedListTabulatedFunction: " + linkedFunc1.toString());

        System.out.println("\n--- ТЕСТ equals() ---");
        System.out.println("arrayFunc1.equals(arrayFunc2): " + arrayFunc1.equals(arrayFunc2)); // true
        System.out.println("arrayFunc1.equals(arrayFunc3): " + arrayFunc1.equals(arrayFunc3)); // false
        System.out.println("arrayFunc1.equals(arrayFunc4): " + arrayFunc1.equals(arrayFunc4)); // false
        System.out.println("linkedFunc1.equals(linkedFunc2): " + linkedFunc1.equals(linkedFunc2)); // true
        System.out.println("arrayFunc1.equals(linkedFunc1): " + arrayFunc1.equals(linkedFunc1)); // true
        System.out.println("arrayFunc1.equals(\"строка\"): " + arrayFunc1.equals("строка")); // false
        System.out.println("arrayFunc1.equals(null): " + arrayFunc1.equals(null)); // false

        System.out.println("\n--- ТЕСТ hashCode() ---");
        System.out.println("arrayFunc1.hashCode(): " + arrayFunc1.hashCode());
        System.out.println("arrayFunc2.hashCode(): " + arrayFunc2.hashCode());
        System.out.println("arrayFunc3.hashCode(): " + arrayFunc3.hashCode());
        System.out.println("arrayFunc4.hashCode(): " + arrayFunc4.hashCode());
        System.out.println("linkedFunc1.hashCode(): " + linkedFunc1.hashCode());
        System.out.println("linkedFunc2.hashCode(): " + linkedFunc2.hashCode());
        
        // Проверка согласованности equals() и hashCode()
        System.out.println("\n--- СОГЛАСОВАННОСТЬ equals() и hashCode() ---");
        System.out.println("arrayFunc1.equals(arrayFunc2) && arrayFunc1.hashCode() == arrayFunc2.hashCode(): " + 
                        (arrayFunc1.equals(arrayFunc2) && arrayFunc1.hashCode() == arrayFunc2.hashCode()));
        System.out.println("arrayFunc1.equals(arrayFunc3) || arrayFunc1.hashCode() != arrayFunc3.hashCode(): " + 
                        (!arrayFunc1.equals(arrayFunc3) || arrayFunc1.hashCode() != arrayFunc3.hashCode()));

        System.out.println("\n--- ТЕСТ ИЗМЕНЕНИЯ ХЭШ-КОДА ---");
        int originalHash = arrayFunc1.hashCode();
        System.out.println("Исходный hashCode: " + originalHash);
        
        // Незначительно изменяем объект
        arrayFunc1.setPointY(1, 3.001); // Изменяем на несколько тысячных
        int modifiedHash = arrayFunc1.hashCode();
        System.out.println("После изменения Y[1] с 3.0 на 3.001: " + modifiedHash);
        System.out.println("Хэш-код изменился: " + (originalHash != modifiedHash));

        // Восстанавливаем исходное значение
        arrayFunc1.setPointY(1, 3.0);

        System.out.println("\n--- ТЕСТ clone() ---");
        
        // Тест ArrayTabulatedFunction
        ArrayTabulatedFunction arrayClone = (ArrayTabulatedFunction) arrayFunc1.clone();
        System.out.println("arrayFunc1.equals(arrayClone): " + arrayFunc1.equals(arrayClone));
        System.out.println("arrayFunc1 == arrayClone: " + (arrayFunc1 == arrayClone));
        
        // Тест LinkedListTabulatedFunction
        LinkedListTabulatedFunction linkedClone = (LinkedListTabulatedFunction) linkedFunc1.clone();
        System.out.println("linkedFunc1.equals(linkedClone): " + linkedFunc1.equals(linkedClone));
        System.out.println("linkedFunc1 == linkedClone: " + (linkedFunc1 == linkedClone));

        System.out.println("\n--- ТЕСТ ГЛУБОКОГО КЛОНИРОВАНИЯ ---");
        
        // Сохраняем исходные значения
        double originalArrayY = arrayFunc1.getPointY(0);
        double originalLinkedY = linkedFunc1.getPointY(0);
        
        // Изменяем клоны
        arrayClone.setPointY(0, 999.0);
        linkedClone.setPointY(0, 888.0);
        
        System.out.println("После изменения клонов:");
        System.out.println("arrayFunc1.getPointY(0): " + arrayFunc1.getPointY(0) + " (оригинал не изменился)");
        System.out.println("arrayClone.getPointY(0): " + arrayClone.getPointY(0) + " (клон изменился)");
        System.out.println("linkedFunc1.getPointY(0): " + linkedFunc1.getPointY(0) + " (оригинал не изменился)");
        System.out.println("linkedClone.getPointY(0): " + linkedClone.getPointY(0) + " (клон изменился)");
        
        // Проверка глубокого копирования точек
        System.out.println("arrayFunc1.getPoint(0) == arrayClone.getPoint(0): " + 
                        (arrayFunc1.getPoint(0) == arrayClone.getPoint(0)) + " (разные объекты точек)");
        System.out.println("linkedFunc1.getPoint(0) == linkedClone.getPoint(0): " + 
                        (linkedFunc1.getPoint(0) == linkedClone.getPoint(0)) + " (разные объекты точек)");

        System.out.println("\n--- ТЕСТ РАБОТЫ С TabulatedFunction ИНТЕРФЕЙСОМ ---");
        TabulatedFunction tabulatedFunc = arrayFunc1;
        TabulatedFunction tabulatedClone = (TabulatedFunction) tabulatedFunc.clone();
        System.out.println("TabulatedFunction интерфейс работает: " + tabulatedFunc.equals(tabulatedClone));
    }
}

    
