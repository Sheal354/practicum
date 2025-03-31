# practicum
SDET practicum from Simbirsoft

Тестирование UI  
3 группы тестов:
1. AddCustomerTest - добавление клиента
2. FirstNameSortingTest - сортировка клиентов по имени
3. DeleteCustomerTest - удаление клиента

Классы PageObject:
1. MainPage - пустая страница с кнопками меню
2. AddCustomerForm - форма создания клиента
3. CustomersList - таблица клиентов
4. MenuButtons - вынесен в PageObject Elements, так как присутствуют на всех страницах

Классы-хелперы:
1. AlertHandler - операции с уведомлениями
2. AssertionsHelper - операции с асертами
3. CustomerDataUtils - операции с данными клиентов
4. DataProviderHelper - предоставление тестовых данных
5. TestDataGenerator - генерация тестовых данных  

Реализовано формирование отчетов Allure:  
Один функциональный блок, три группы тестов по назначению  

Реализован параллельный запуск тестов на уровне методов.  

Реализован запуск в системе CI/CD при помощи GitHub Actions.
